package com.skt.web.alpha.to;

public class FbWorkHistory {

	private Common position;

	private Common employer;

	private String startDate;

	private String endDate;

	private Common location;

	private String description;

	public Common getPosition() {
		return position;
	}

	public void setPosition(Common position) {
		this.position = position;
	}

	public Common getEmployer() {
		return employer;
	}

	public void setEmployer(Common employer) {
		this.employer = employer;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Common getLocation() {
		return location;
	}

	public void setLocation(Common location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}