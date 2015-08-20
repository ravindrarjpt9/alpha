package com.skt.web.alpha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.skt.web.alpha.constants.GroupCategory;
import com.skt.web.alpha.constants.GroupGenderCategory;
import com.skt.web.alpha.constants.GroupIconCategory;
import com.skt.web.alpha.constants.GroupStatus;
import com.skt.web.common.model.BaseModel;

@NamedQueries({ @NamedQuery(name = "findByName", query = "select g from Group g "
		+ "where g.name = :groupName") })
@Entity
@Table(name = "groups", uniqueConstraints = @UniqueConstraint(columnNames = "NAME", name = "UK_NAME_GROUPS"))
public class Group extends BaseModel {

	private static final long serialVersionUID = -1353299076894565904L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	// Replacing unique tag with @UniqueConstraint to specify constraint name
	// @Column(name = "NAME", unique = true)
	@Column(name = "NAME")
	String name;

	@Column(name = "DISPLAY_NAME")
	String displayName;

	@Column(name = "DESCRIPTION")
	String description;

	@Column(name = "GROUP_STATUS")
	@Enumerated(EnumType.STRING)
	// Setting default value of groupStatus
	private GroupStatus groupStatus = GroupStatus.OPEN;

	@Column(name = "GROUP_GENDER_CATEGORY")
	@Enumerated(EnumType.STRING)
	// Setting the default value of groupGenderCategory
	private GroupGenderCategory groupGenderCategory = GroupGenderCategory.UNISEX;

	@Column(name = "GROUP_CATEGORY")
	private String groupCategory;

	@Column(name = "GROUP_ICON_CATEGORY")
	@Enumerated(EnumType.STRING)
	private GroupIconCategory groupIconCategory;
	
	@Column(name = "USERS_COUNT")
	private int userCount = 1;

	@Column(name = "GROUP_CREATION_TIME", updatable = false)
	private Date groupCreationTime = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GroupStatus getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(GroupStatus groupStatus) {
		this.groupStatus = groupStatus;
	}

	public GroupGenderCategory getGroupGenderCategory() {
		return groupGenderCategory;
	}

	public void setGroupGenderCategory(GroupGenderCategory groupGenderCategory) {
		this.groupGenderCategory = groupGenderCategory;
	}

	public String getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}

	public GroupIconCategory getGroupIconCategory() {
		return groupIconCategory;
	}

	public void setGroupIconCategory(GroupIconCategory groupIconCategory) {
		this.groupIconCategory = groupIconCategory;
	}

	// Return a clone of the groupCreationTime
	public Date getGroupCreationTime() {
		if (groupCreationTime != null) {
			return (Date) this.groupCreationTime.clone();
		}
		return null;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	// Commenting setter, as groupCreationDate should never be modified
	// public void setGroupCreationTime(Date groupCreationTime) {
	// this.groupCreationTime = groupCreationTime;
	// }
}
