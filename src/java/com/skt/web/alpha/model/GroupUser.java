package com.skt.web.alpha.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.skt.web.alpha.constants.UserGroupRole;
import com.skt.web.alpha.constants.UserGroupStatus;
import com.skt.web.common.model.BaseModel;

@NamedQueries({
		@NamedQuery(name = "findAllGroupUserTosByUserId", query = ""
				+ "select gu.id, gu.group, gu.userGroupStatus, gu.userGroupRole, gu.userAddTime "
				+ "from GroupUser gu " + "where gu.user.id = :userId"),
		@NamedQuery(name = "findByUserIdAndGroupId", query = "" + "select gu "
				+ "from GroupUser gu "
				+ "where gu.user.id = :userId AND gu.group.id = :groupId") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "findAllUserNameTosByGroupId", query = ""
				+ "select gu.USER_ID, gu.USER_NAME " + "from groups_users gu "
				+ "where gu.GROUP_ID = :groupId"),
		@NamedNativeQuery(name = "findAllUserIdTosByGroupId", query = ""
						+ "select gu.USER_ID , gu.USER_NAME " + " from groups_users gu "
						+ "where gu.GROUP_ID = :groupId"),
		@NamedNativeQuery(name = "findAllGroupIdsByUserId", query = ""
				+ "select gu.GROUP_ID " + "from groups_users gu "
				+ "where gu.USER_ID = :userId"),
		@NamedNativeQuery(name = "findAllGroupDisplayNameTosByUserId", query = ""
				+ "select gu.GROUP_ID, gu.GROUP_DISPLAY_NAME "
				+ "from groups_users gu " + "where gu.USER_ID = :userId") })
@Entity
@Table(name = "groups_users", uniqueConstraints = @UniqueConstraint(columnNames = {
		"GROUP_ID", "USER_ID" }, name = "UK_GROUPS_USERS"))
public class GroupUser extends BaseModel {

	private static final long serialVersionUID = 7072066412005931207L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	// TODO: Make bi-directional join if required
	// Uni-directional join
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_GROUPS_GROUPS_USERS"), nullable = false)
	private Group group;

	// TODO: Make bi-directional join if required
	// Uni-directional join
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "USER_ID",updatable=true, foreignKey = @ForeignKey(name = "FK_USERS_GROUPS_USERS"), nullable = false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;

	@Column(name = "GROUP_DISPLAY_NAME", nullable = false)
	private String groupDisplayName;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "USER_GROUP_STATUS")
	@Enumerated(EnumType.STRING)
	// Setting default user status as ACTIVE
	private UserGroupStatus userGroupStatus = UserGroupStatus.ACTIVE;

	@Column(name = "USER_GROUP_ROLE")
	@Enumerated(EnumType.STRING)
	// Setting default value of userGroupRole
	private UserGroupRole userGroupRole = UserGroupRole.USER;

	@Column(name = "USER_ADD_TIME", updatable = false)
	private Date userAddTime = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGroupDisplayName() {
		return groupDisplayName;
	}

	public void setGroupDisplayName(String groupDisplayName) {
		this.groupDisplayName = groupDisplayName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserGroupStatus getUserGroupStatus() {
		return userGroupStatus;
	}

	public void setUserGroupStatus(UserGroupStatus userGroupStatus) {
		this.userGroupStatus = userGroupStatus;
	}

	public UserGroupRole getUserGroupRole() {
		return userGroupRole;
	}

	public void setUserGroupRole(UserGroupRole userGroupRole) {
		this.userGroupRole = userGroupRole;
	}

	// Return a clone of the userAddTime
	public Date getUserAddTime() {
		if (userAddTime != null) {
			return (Date) this.userAddTime.clone();
		}
		return null;
	}

	// Commenting setter, as userAddTime should never be modified
	// public void setUserAddTime(Date userAddTime) {
	// this.userAddTime = userAddTime;
	// }
}
