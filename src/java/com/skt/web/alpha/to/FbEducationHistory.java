package com.skt.web.alpha.to;

import java.util.ArrayList;
import java.util.List;

public class FbEducationHistory {

	private String type;

	private Common year;

	private List<Common> with = new ArrayList<>();

	private Common school;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Common getYear() {
		return year;
	}

	public void setYear(Common year) {
		this.year = year;
	}

	public List<Common> getWith() {
		return with;
	}

	public void setWith(List<Common> with) {
		this.with = with;
	}

	public Common getSchool() {
		return school;
	}

	public void setSchool(Common school) {
		this.school = school;
	}
}
