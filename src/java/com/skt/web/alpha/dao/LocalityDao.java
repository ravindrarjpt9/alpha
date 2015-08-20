package com.skt.web.alpha.dao;

import java.util.List;

import com.skt.web.alpha.model.Locality;
import com.skt.web.common.dao.BaseDao;

public interface LocalityDao extends BaseDao<Locality>{

	Locality getLocalityByLocation(String localityName,String city);

	List<Locality> getLocalityList();

	
}
