package com.skt.web.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.LocalityDao;
import com.skt.web.alpha.model.Locality;
import com.skt.web.common.exception.ApplicationException;

@Service
public class LocalityServiceImpl implements LocalityService {

	@Autowired
	LocalityDao localityDao;
	
	@Override
	public Locality create(Locality locality) throws ApplicationException {
		
		return localityDao.persist(locality);
	}
	
	@Override
	public Locality get(int id) throws ApplicationException {
		
		Locality locality = localityDao.findById(Locality.class, id);
		if (locality == null) {
			throw new ApplicationException("No locality exists for the ID: "
					+ id);
		} else {
			return locality;
		}
	}
	@Override
	public Locality getLocalityByLocation(String localityName,String city)
			throws ApplicationException {
		Locality locality = localityDao.getLocalityByLocation(localityName,city);
		if(locality == null)
		{
			throw new ApplicationException("No Locality found under ["+localityName+"] City ["+city+"]");
		}
		return locality;
	}
	@Override
	public List<Locality> getLocalityList() throws ApplicationException {
		return localityDao.getLocalityList();
	}
	@Override
	public Locality update(Locality locality) throws ApplicationException {
		
		return localityDao.merge(locality);
	}
	
	
}
