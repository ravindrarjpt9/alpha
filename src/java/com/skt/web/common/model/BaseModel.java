package com.skt.web.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This represents that base entity for all POJOs and DTO objects. It contains
 * common attributes and get/set methods that can be reused across all objects.
 * All DTOs and POJOs in the application should extend this class.
 */
@MappedSuperclass
public class BaseModel implements Serializable {
	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -618135517888369225L;

	/**
	 * The latest time that the object was modified.
	 */
	// @Version will update the field with every update and insert
	@Version
	@Column(name = "MODIFIED_TIME")
	@JsonIgnore
	private Date modifiedTime;

	public Date getModifiedTime() {
		if (modifiedTime != null) {
			return (Date) this.modifiedTime.clone();
		}
		return null;
	}

	// No need to set modifiedTime explicitly as it's value will always be set
	// by DB
	// public void setModifiedTime(Date modifiedTime) {
	// if (modifiedTime != null) {
	// this.modifiedTime = (Date) modifiedTime.clone();
	// }
	// }
}