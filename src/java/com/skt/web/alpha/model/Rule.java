package com.skt.web.alpha.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.skt.web.common.model.BaseModel;

@Entity
@Table(name = "rules")
public class Rule extends BaseModel {

	private static final long serialVersionUID = -5878152507714637709L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
