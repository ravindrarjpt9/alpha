package com.skt.web.alpha.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.Photo;
import com.skt.web.alpha.service.PhotoService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.PhotoIdTo;
import com.skt.web.alpha.to.PhotoTo;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.TopicIdTo;
import com.skt.web.alpha.util.PhotoUtil;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class PhotoController {

	private static final Logger LOG = Logger.getLogger(PhotoController.class);

	@Autowired
	PhotoService photoService;

	@Autowired
	PhotoUtil photoUtil;

	@Transactional
	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response uploadPhoto(@RequestParam("data") MultipartFile file) {
		Object data = null;
		boolean success = false;
		try {
			try {
				if (!file.isEmpty()) {
					MediaType mt = MediaType.parseMediaType(file
							.getContentType());
					byte thumbImage[] = photoUtil.thumbImage(file.getInputStream(), mt.getSubtype());
					byte[] compressPhoto = photoUtil.resize(file.getInputStream(), mt.getSubtype());
					com.skt.web.alpha.model.Photo photo = new com.skt.web.alpha.model.Photo(
							mt.getSubtype(),compressPhoto,thumbImage);
					photo = photoService.uploadPhoto(photo);
					PhotoIdTo photoTo = new PhotoIdTo();
					photoUtil.setPhotoToFromPhotoForId(photoTo, photo);
					// this.crmService.writeUserProfilePhoto(user, mt,
					// bytesForProfilePhoto);
					data = photoTo;
					success = true;

				} else {
					throw new ApplicationException(Photo.FILE_EMPTY);
				}
			} catch (IOException e) {
				throw new ApplicationException(
						Constants.ERROR_MSG_FILE_DATA_BYTE);
			}
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getOrgPhoto", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getPhoto(@RequestBody PhotoTo photoTo) {
		Object data = null;
		boolean success = false;
		try {
			int id = photoTo.getId();
			com.skt.web.alpha.model.Photo photo = photoService.getPhoto(id);
			photoUtil.setPhotoToFromPhotoForOrgPhoto(photoTo, photo);
			data = photoTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		
		return new Response(success, data);
	}
	@Transactional
	@RequestMapping(value = "/getThuPhoto", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getThumbnailPhoto(@RequestBody PhotoIdTo photoTo) {
		Object data = null;
		boolean success = false;
		try {
			int id = photoTo.getId();
			com.skt.web.alpha.model.Photo photo = photoService.getPhoto(id);
			photoUtil.setPhotoToFromPhotoForId(photoTo, photo);
			data = photoTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		
		return new Response(success, data);
	}
	@Transactional
	@RequestMapping(value="/getImage/{id}", method = RequestMethod.GET)
	  public void fooBar(@PathVariable("id") int id, HttpServletResponse resp) throws IOException {
		
		try {
			com.skt.web.alpha.model.Photo photo = photoService.getPhoto(id);
			resp.reset();
			
	        resp.setContentType(photoUtil.getContentType(photo.getType()));
	        resp.setContentLength(photo.getThuImg().length);

	        final BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(photo.getThuImg()));
	        FileCopyUtils.copy(in, resp.getOutputStream());
            resp.flushBuffer();
		} catch (ApplicationException e) {
			LOG.error("Error : While downloading image id ["+id+"]");
		}
		
		
		
	}

}
