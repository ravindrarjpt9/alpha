package com.skt.web.alpha.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.model.Locality;
import com.skt.web.alpha.service.LocalityService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.LocationTo;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.util.LocationUtil;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class LocalityController {

	
	private static final Logger LOG = Logger.getLogger(LocalityController.class);
	
	
	@Autowired
	LocalityService localityService;
	
	@Autowired
	LocationUtil locationUtil;
	
	@Transactional
	@RequestMapping(value="/creatrLocation",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response createLocation(@RequestBody LocationTo location)
	{
		boolean success = false;
		Object data = null;
		LocationTo locationTo = new LocationTo();
		Locality locality = null;
		try
		{
			
				if(location.getId() != 0)
				{
					
					locality = localityService.update(new Locality(location.getId(), location.getCity(), location.getLocalityName(), location.getLocation()));
				}
				else
					
				{
					locality = localityService.create(new Locality(location.getCity(), location.getLocalityName(), location.getLocation()));
				}
				
				//locationTo = locationUtil.setLocationToFromLocality(locationTo,locationTo);
				success = true;
				data = new LocationTo(locality.getId(), locality.getCity(), locality.getLocalityName(), locality.getLocation());
			
			
			
		}catch(ApplicationException e)
		{
			LOG.error("Error while creating location "+location +"", e);
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}
	
	@Transactional
	@RequestMapping(value = "/getGetAllLocation", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getGroupIdsByUserId() {
		boolean success = false;
		Object data = null;

		try {
			// Fetching the required data from DB as a List of Integer
			List<Locality> list = localityService.getLocalityList();

			// Returning the List of location
			data = list;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	
}
