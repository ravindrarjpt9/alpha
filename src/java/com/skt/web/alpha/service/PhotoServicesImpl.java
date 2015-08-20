package com.skt.web.alpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.PhotoDao;
import com.skt.web.alpha.model.Photo;
import com.skt.web.alpha.model.Topic;
import com.skt.web.common.exception.ApplicationException;

@Service
public class PhotoServicesImpl implements PhotoService {

	@Autowired
	PhotoDao photoDao;
	
	@Override
	public Photo uploadPhoto(Photo photo) throws ApplicationException
	{
		return photoDao.persist(photo);
	}
	
	@Override
	public Photo getPhoto(int id) throws ApplicationException {
		
		Photo photo = photoDao.findById(Photo.class, id);
		if (photo == null) {
			throw new ApplicationException("No photo exists for the ID: "
					+ id);
		} else {
			return photo;
		}
	}
}
