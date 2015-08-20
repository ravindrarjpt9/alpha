package com.skt.web.alpha.model;

import java.util.Date;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skt.web.alpha.constants.TopicStatus;
import com.skt.web.common.model.BaseModel;

@NamedNativeQueries({
		@NamedNativeQuery(name = "findAllTopicTosByGroupIds", query = ""
				+ "select t.ID, t.TYPE, t.NAME, t.GROUP_ID, t.OWNER_USER_ID, "
				+ "t.GROUP_DISPLAY_NAME, t.OWNER_USER_NAME, t.DESPCRIPTION, "
				+ "t.IMAGE_PATH, t.TOPIC_STATUS, t.TOPIC_LIKE_COUNT, t.TOPIC_SPAM_COUNT, "
				+ "t.TOPIC_VIEW_COUNT, t.LAST_VIEW_TIME, t.CREATION_TIME , t.PHOTO_ID ,t.OWNER_FB_ID " 
				+ "from topics t " + "where t.GROUP_ID IN (:groupIds)"),
		@NamedNativeQuery(name = "findAllTopicTosByOwnerUserId", query = ""
				+ "select t.ID, t.TYPE, t.NAME, t.GROUP_ID, t.OWNER_USER_ID, "
				+ "t.GROUP_DISPLAY_NAME, t.OWNER_USER_NAME, t.DESPCRIPTION, "
				+ "t.IMAGE_PATH, t.TOPIC_STATUS, t.TOPIC_LIKE_COUNT, t.TOPIC_SPAM_COUNT, "
				+ "t.TOPIC_VIEW_COUNT, t.LAST_VIEW_TIME, t.CREATION_TIME , t.PHOTO_ID , t.OWNER_FB_ID "
				+ "from topics t " + "where t.OWNER_USER_ID = :ownerUserId") })
@Entity
@Table(name = "topics")
public class Topic extends BaseModel {

	private static final long serialVersionUID = -301652400356542685L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	// To disable getter and setter
	@JsonIgnore
	private int id;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "NAME")
	private String name;

	// Uni-directional ManyToOne mapping
	// TODO: Create bi-directional mapping if needed
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_GROUPS_TOPICS"), nullable = false)
	private Group group;

	// Uni-directional ManyToOne mapping
	// TODO: Create bi-directional mapping if needed
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER_USER_ID", foreignKey = @ForeignKey(name = "FK_USERS_TOPICS"), nullable = false)
	private User ownerUser;

	@Column(name = "GROUP_DISPLAY_NAME", nullable = false)
	private String groupDisplayName;

	@Column(name = "OWNER_USER_NAME", nullable = false)
	private String ownerUserName;

	@Column(name = "DESPCRIPTION")
	private String description;

	@Column(name = "IMAGE_PATH")
	private String imagePath;

	@Column(name = "TOPIC_STATUS")
	@Enumerated(EnumType.STRING)
	// Setting default status for each topic as OPEN
	private TopicStatus topicStatus = TopicStatus.OPEN;

	@Column(name = "TOPIC_LIKE_COUNT")
	private int topicLikeCount;

	@Column(name = "TOPIC_SPAM_COUNT")
	private int topicSpamCount;

	@Column(name = "TOPIC_VIEW_COUNT")
	private int topicViewCount;

	@Column(name = "LAST_VIEW_TIME")
	private Date lastViewTime;
	
	@Column(name = "OWNER_FB_ID")
	private String ownerFbId;

	// @Column(name = "CREATION_TIME", columnDefinition =
	// "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =
	// false)
	@Column(name = "CREATION_TIME", updatable = false)
	private Date creationTime = new Date();

	@Column(name = "PHOTO_ID")
	private int photoId;
	// To enable getting the id in JSON (serialization)
	@JsonProperty
	public int getId() {
		return id;
	}

	// To keep setter disabled (de-serialization) so as to use @GeneratedValue
	@JsonIgnore
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public String getGroupDisplayName() {
		return groupDisplayName;
	}

	public void setGroupDisplayName(String groupDisplayName) {
		this.groupDisplayName = groupDisplayName;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public TopicStatus getTopicStatus() {
		return topicStatus;
	}

	public void setTopicStatus(TopicStatus topicStatus) {
		this.topicStatus = topicStatus;
	}

	public int getTopicLikeCount() {
		return topicLikeCount;
	}

	public void setTopicLikeCount(int topicLikeCount) {
		this.topicLikeCount = topicLikeCount;
	}

	public int getTopicSpamCount() {
		return topicSpamCount;
	}

	public void setTopicSpamCount(int topicSpamCount) {
		this.topicSpamCount = topicSpamCount;
	}

	public int getTopicViewCount() {
		return topicViewCount;
	}

	public void setTopicViewCount(int topicViewCount) {
		this.topicViewCount = topicViewCount;
	}

	public Date getLastViewTime() {
		return lastViewTime;
	}

	public void setLastViewTime(Date lastViewTime) {
		this.lastViewTime = lastViewTime;
	}

	// Returning a clone of creationTime
	public Date getCreationTime() {
		if (creationTime != null) {
			return (Date) this.creationTime.clone();
		}
		return null;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getOwnerFbPhotoId() {
		return ownerFbId;
	}

	public void setOwnerFbPhotoId(String ownerFbPhotoId) {
		this.ownerFbId = ownerFbPhotoId;
	}

	// Commenting setter, as creationTime should never be modified
	// public void setCreationTime(Date creationTime) {
	// this.creationTime = creationTime;
	// }
}
