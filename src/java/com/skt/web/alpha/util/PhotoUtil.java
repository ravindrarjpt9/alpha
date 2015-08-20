package com.skt.web.alpha.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import com.skt.web.alpha.to.PhotoIdTo;
import com.skt.web.alpha.to.PhotoTo;

@Component
public class PhotoUtil {

	public void setPhotoToFromPhotoForId(PhotoIdTo photoIdTo,
			com.skt.web.alpha.model.Photo photo) {
		photoIdTo.setId(photo.getId());
		photoIdTo.setType(photo.getType());
		photoIdTo.setThuImg(photo.getThuImg());

	}

	public void setPhotoToFromPhotoForOrgPhoto(PhotoTo photoTo,
			com.skt.web.alpha.model.Photo photo) {
		photoTo.setId(photo.getId());
		photoTo.setType(photo.getType());
		photoTo.setOrgImg(photo.getOrgImg());

	}

	public void setPhotoToFromPhotoForThuPhoto(PhotoTo photoTo,
			com.skt.web.alpha.model.Photo photo) {
		photoTo.setId(photo.getId());
		photoTo.setType(photo.getType());
		photoTo.setThuImg(photo.getThuImg());

	}

	public String encodeToString(byte[] imageBytes) {

		BASE64Encoder encoder = new BASE64Encoder();
		String imageString = encoder.encode(imageBytes);

		return imageString;
	}

	public byte[] decodeToByte(String imgData) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(imgData);

	}

	public byte[] thumbImage(InputStream input, String formate) {
		try {
			BufferedImage originalImage = ImageIO.read(input);

			originalImage = Scalr.resize(originalImage, Scalr.Method.QUALITY,
					Scalr.Mode.FIT_EXACT, 100, 120);
			// To save with original ratio uncomment next line and comment the
			// above.
			// originalImage= Scalr.resize(originalImage, 153, 128);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, formate, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (Exception e) {
			return null;
		}
	}

	public byte[] resize(InputStream input, String formate) {
		try {

			BufferedImage originalImage = ImageIO.read(input);

			originalImage = Scalr.resize(originalImage,
					Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 250, 300);
			// To save with original ratio uncomment next line and comment the
			// above.
			// originalImage= Scalr.resize(originalImage, 153, 128);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, formate, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (Exception e) {
			return null;
		}

	}

	public String getContentType(String imgType) {
		if ("jpg".equalsIgnoreCase(imgType.toLowerCase())) {
			return MediaType.IMAGE_JPEG_VALUE;
		} else if ("jpeg".equalsIgnoreCase(imgType.toLowerCase())) {
			return MediaType.IMAGE_JPEG_VALUE;
		} else if ("gif".equalsIgnoreCase(imgType.toLowerCase())) {
			return MediaType.IMAGE_GIF_VALUE;
		} else if ("png".equalsIgnoreCase(imgType.toLowerCase())) {
			return MediaType.IMAGE_PNG_VALUE;
		}

		return MediaType.IMAGE_JPEG_VALUE;
	}

}
