package com.skt.web.alpha.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skt.web.alpha.service.PushRegistrationService;
import com.skt.web.alpha.to.PushResponse;
import com.skt.web.alpha.to.Response;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class CalendarController {
	/**
	 * Log the error messages.
	 */
	private static final Logger LOG = Logger
			.getLogger(CalendarController.class);

	@Autowired
	private PushRegistrationService pushRegistrationService;

	private static final String IMAGE_PATH = File.separator + "images"
			+ File.separator + "topic" + File.separator;

	private static final String[] MONTHS = { "jan", "feb", "mar", "apr", "may",
			"jun", "jly", "aug", "sep", "oct", "nov", "dec" };

	@Transactional
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public Response uploadImage(HttpServletRequest request,
			@RequestParam("year") int year,
			@RequestParam("image") MultipartFile[] images) {

		String contextPath = request.getServletContext().getRealPath("");
		String folderName = contextPath + IMAGE_PATH + year + File.separator;
		File file = new File(folderName);
		file.mkdirs();

		StringBuffer message = new StringBuffer();
		int count = 0;
		for (MultipartFile multipartFile : images) {
			String fileName = MONTHS[count];
			count++;
			if (multipartFile == null || multipartFile.isEmpty()) {
				if (message.length() <= 0) {
					message.append("Calendar Image for month(s) [ ");
				} else {
					message.append(", ");
				}
				message.append(fileName);
			} else {
				BufferedOutputStream stream = null;
				try {
					stream = new BufferedOutputStream(new FileOutputStream(
							new File(folderName + fileName + ".png")));
					stream.write(multipartFile.getBytes());
					stream.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		PushResponse resp = new PushResponse();
		resp.setFrom("calendar");

		try {
			List<String> deviceIds = pushRegistrationService
					.getAllRegisteredDevice();
			pushRegistrationService.send(deviceIds,
					mapper.writeValueAsString(resp));
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if (message.length() <= 0) {
			message.append("Calendar uploaded successfully.");
		} else {
			message.append(" ] not uploaded. Either image is corrupted or null.");
		}

		Object data = message.toString();
		boolean success = true;

		Response response = new Response(success, data);
		return response;
	}

	@Transactional
	@RequestMapping(value = "/getCalendarFor", method = RequestMethod.GET)
	@ResponseBody
	public void getCalendarFor(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");

		ServletContext ctx = request.getServletContext();
		String contextPath = ctx.getRealPath("");
		String folderName = contextPath + IMAGE_PATH + year + File.separator;

		File file = new File(folderName + month + ".png");
		if (!file.exists()) {
			byte[] bufferData = ("File not found for month: " + month
					+ " and year: " + year).getBytes();

			response.setContentType("text/*");
			response.setContentLength(bufferData.length);
			ServletOutputStream os = response.getOutputStream();
			os.write(bufferData);
			os.flush();
			os.close();
		} else {
			InputStream fis = new FileInputStream(file);
			String mimeType = ctx.getMimeType(file.getAbsolutePath());
			response.setContentType(mimeType != null ? mimeType
					: "application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", "inline; filename=\""
					+ month + ".png\"");
			ServletOutputStream os = response.getOutputStream();
			byte[] bufferData = new byte[1024];
			int read = 0;
			while ((read = fis.read(bufferData)) != -1) {
				os.write(bufferData, 0, read);
			}
			os.flush();
			os.close();
			fis.close();
		}
	}
}
