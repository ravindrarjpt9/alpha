package com.skt.web.alpha.model;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.skt.web.alpha.constants.UserVotingStatus;
import com.skt.web.common.model.BaseModel;

@Entity
@Table(name = "votings", uniqueConstraints = @UniqueConstraint(columnNames = {
		"GROUP_ID", "USER_ID" }, name = "UK_GROUP_USER_VOTINGS"))
public class Voting extends BaseModel {

	private static final long serialVersionUID = 7072066412005931207L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_GROUPS_VOTINGS"))
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USERS_VOTINGS"))
	private User user;

	@Column(name = "USER_VOTING_STATUS")
	@Enumerated(EnumType.STRING)
	// Setting the default value for userVotingStatus
	private UserVotingStatus userVotingStatus = UserVotingStatus.PENDING;

	@Column(name = "USER_ACCEPTED_COUNT")
	private long userAcceptedCount;

	@Column(name = "USER_REJECTED_COUNT")
	private long userRejectedCount;

	@Column(name = "USER_VOTING_SCORE")
	private long userVotingScore;

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

	public UserVotingStatus getUserVotingStatus() {
		return userVotingStatus;
	}

	public void setUserVotingStatus(UserVotingStatus userVotingStatus) {
		this.userVotingStatus = userVotingStatus;
	}

	public long getUserAcceptedCount() {
		return userAcceptedCount;
	}

	public void setUserAcceptedCount(long userAcceptedCount) {
		this.userAcceptedCount = userAcceptedCount;
	}

	public long getUserRejectedCount() {
		return userRejectedCount;
	}

	public void setUserRejectedCount(long userRejectedCount) {
		this.userRejectedCount = userRejectedCount;
	}

	public long getUserVotingScore() {
		return userVotingScore;
	}

	public void setUserVotingScore(long userVotingScore) {
		this.userVotingScore = userVotingScore;
	}
}
