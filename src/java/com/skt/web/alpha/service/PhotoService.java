package com.skt.web.alpha.service;

import com.skt.web.alpha.model.Photo;
import com.skt.web.common.exception.ApplicationException;

public interface PhotoService {
	public Photo uploadPhoto(Photo photo) throws ApplicationException;

	public com.skt.web.alpha.model.Photo getPhoto(int id) throws ApplicationException;
}
