package com.skt.web.alpha.service;

import java.util.List;

import com.skt.web.alpha.model.Locality;
import com.skt.web.common.exception.ApplicationException;

public interface LocalityService {

	 Locality create(Locality locality) throws ApplicationException;
	
	 Locality getLocalityByLocation(String localityName,String City) throws ApplicationException;
	 
	 Locality get(int id) throws ApplicationException;
	 
	 Locality update(Locality locality) throws ApplicationException;
	 
	 List<Locality> getLocalityList() throws ApplicationException;
	 
	
	
	
	
}
