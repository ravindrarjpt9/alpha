package com.skt.web.alpha.to;

import java.util.Date;

import com.skt.web.alpha.constants.TopicStatus;

public class TopicTo {

	private int id;

	private String type;

	private String name;

	// Setting default value of groupId as -1 so that it does not return any
	// group in groupDao.get(groupId)
	private int groupId = -1;

	// Setting default value of ownerUserId as -1 so that it does not return any
	// user in userDao.get(ownerUserId)
	private int ownerUserId = -1;

	private String groupDisplayName;

	private String ownerUserName;

	private String description;

	private String imagePath;

	// Setting default status for each topic as OPEN
	private TopicStatus topicStatus = TopicStatus.OPEN;

	private int topicLikeCount;

	private int topicSpamCount;

	private int topicViewCount;

	private Date lastViewTime;

	private Date creationTime;

	private boolean ownerUser;

	private int photoId;
	
	private String ownerFbId;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(int ownerUserId) {
		this.ownerUserId = ownerUserId;
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

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public boolean isOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(boolean ownerUser) {
		this.ownerUser = ownerUser;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getOwnerFbId() {
		return ownerFbId;
	}

	public void setOwnerFbId(String ownerFbId) {
		this.ownerFbId = ownerFbId;
	}

	
}
