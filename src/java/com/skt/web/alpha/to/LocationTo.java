package com.skt.web.alpha.to;

import java.util.Date;

public class LocationTo {

	
	private int id;
	private String city;
	private String localityName;
	private String location;
	private String modifiedBy;
	private Date modifiedTime;
	
	
	public LocationTo() {
		
	}
	public LocationTo(int id, String city, String localityName, String location, String modifiedBy, Date modifiedTime) {
		
		this.id = id;
		this.city = city;
		this.localityName = localityName;
		this.location = location;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocalityName() {
		return localityName;
	}
	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	
}
