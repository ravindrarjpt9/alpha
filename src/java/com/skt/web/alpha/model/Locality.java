package com.skt.web.alpha.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.skt.web.common.model.BaseModel;

@Entity
@Table(name="locality_list")
public class Locality extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2485599937088656818L;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name="CITY")
	private String city;

	@Column(name="LOCALITY_NAME")
	private String localityName;

	@Column(name="LOCATION")
	private String location;

	public Locality() {
		
	}
	public Locality(String city, String localityName, String location) {
		
		this.city = city;
		this.localityName = localityName;
		this.location = location;
	}
	

	public Locality(int id, String city, String localityName, String location) {
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
