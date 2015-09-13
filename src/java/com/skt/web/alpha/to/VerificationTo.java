package com.skt.web.alpha.to;

import java.util.List;

public class VerificationTo {

	// Setting default value of registrationId as -1 so that it does not return
	// any group in registrationDao.get(registrationId)
	private int registrationId = -1;

	private String verificationCode;

	private String city;

	private String location;

	private String locality;

	private String nativeLocation;

	private List<String> interests;

	private String jobType;

	private String industry;

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getNativeLocation() {
		return nativeLocation;
	}

	public void setNativeLocation(String nativeLocation) {
		this.nativeLocation = nativeLocation;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Override
	public String toString() {
		
		return "{+registrationId"+registrationId+" city "+city+" location "+location+" locality "+locality+
				" nativeLocation "+ nativeLocation+" [ interests"+interests+" ] jobType "+ jobType+ " industry "+industry;
	}
}
