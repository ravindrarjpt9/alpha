package com.skt.web.alpha.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.skt.web.common.model.BaseModel;

@Entity
@Table(name="photo")
public class Photo extends BaseModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1170162581903140863L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name="TYPE")
	private String type;
	
	@Lob
    @Column(name="ORG_IMG")
    private byte[] orgImg;
	
	@Lob
    @Column(name="THU_IMG")
    private byte[] thuImg;

	public Photo() {
		
	}
	
	

	public Photo(String type, byte[] orgImg, byte[] thuImg) {
		
		this.type = type;
		this.orgImg = orgImg;
		this.thuImg = thuImg;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(byte[] orgImg) {
		this.orgImg = orgImg;
	}

	public byte[] getThuImg() {
		return thuImg;
	}

	public void setThuImg(byte[] thuImg) {
		this.thuImg = thuImg;
	}
	
	
}
