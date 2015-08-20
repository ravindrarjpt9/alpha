package com.skt.web.alpha.to;

public class LocationTo {

	
	private int id;
	private String city;
	private String localityName;
	private String location;
	
	public LocationTo() {
		
	}
	public LocationTo(int id, String city, String localityName, String location) {
		
		this.id = id;
		this.city = city;
		this.localityName = localityName;
		this.location = location;
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
}
