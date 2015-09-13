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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skt.web.alpha.constants.DeviceType;
import com.skt.web.alpha.constants.Gender;
import com.skt.web.alpha.constants.MaritalStatus;
import com.skt.web.alpha.constants.VerificationStatus;
import com.skt.web.common.model.BaseModel;

@NamedQueries({ @NamedQuery(name = "findRegistrationWithUserByFbUserId", query = "select r from Registration r "
		+ "where r.fbUserId = :fbUserId "+ "AND r.user IS NOT NULL") })
// Remove : + "AND r.user IS NOT NULL"
@Entity
@Table(name = "registrations")
// @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
// property="@id")
public class Registration extends BaseModel {

	private static final long serialVersionUID = -1575333191433840459L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	// To disable getter and setter
	@JsonIgnore
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USERS_REGISTRATIONS"))
	// To disable getter and setter
	@JsonIgnore
	private User user;

	@Column(name = "IMEI_NUM")
	private String imeiNo;

	@Column(name = "PUSH_ID")
	private String pushId;

	@Column(name = "DEVICE_MODEL")
	private String deviceModel;

	@Column(name = "DEVICE_TYPE")
	@Enumerated(EnumType.ORDINAL)
	// Setting default value of deviceType
	private DeviceType deviceType = DeviceType.ANDROID;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "OS_VERSION")
	private int osVersion;

	@Column(name = "LOCALE")
	private String locale;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "MOBILE_NUM")
	private long mobileNo;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "ABOUT_ME")
	private String aboutMe;

	@Column(name = "GENDER")
	@Enumerated(EnumType.STRING)
	// Setting default value of gender
	private Gender gender = Gender.MALE;

	@Column(name = "EMAIL")
	private String eMail;

//	@Lob
//	@Column(name = "FB_POSTS")
//	private String fbPosts;

	@Column(name = "NUM_OF_FRIENDS")
	private int noOfFriends;

	@Column(name = "COVER_PHOTO_URL")
	private String coverPhotoUrl;

	@Column(name = "FB_USER_ID")
	private String fbUserId;

	@Column(name = "FB_TIMELINE_URL")
	private String fbTimelineUrl;

	@Lob
	@Column(name = "FB_GROUPS")
	private String fbGroups;
	
	@Column(name = "FB_CREATION_DATE")
	private String fbCreationDate;
	
	@Column(name = "FB_CURRENT_LOCATION")
	private String fbCurrentLocation;
	
	@Column(name = "FB_RELATIONSHIP_STATUS")
	private String fbRelationsShips;
	
	
//	@Lob
//	@Column(name = "FB_LIKES")
//	private String fbLikes;

	@Lob
	@Column(name = "FB_WORK_HISTORY")
	private String fbWorkHistory;

	@Lob
	@Column(name = "FB_EDUCATION_HISTORY")
	private String fbEducationHistory;

	@Column(name = "MARITAL_STATUS")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Column(name = "VERIFICATION_CODE")
	private String verificationCode;

	@Column(name = "VERIFICATION_CODE_TIME")
	private Date verificationCodeTime;

	@Column(name = "USER_VERIFICATION_TIME")
	private Date userVerificationTime;

	@Column(name = "VERIFICATION_STATUS")
	@Enumerated(EnumType.STRING)
	private VerificationStatus verificationStatus;

	@Column(name = "INVITATION_CODE")
	private String invitationCode;

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

	// This is used only in case an exiting user re-logins via. the
	// 'Registration' screen
	// To enable getting the user object in JSON (serialization)
	@JsonProperty
	public User getUser() {
		return user;
	}

	// To keep setter disabled (de-serialization) as client can not specify the
	// USER_ID for this registration request
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}

	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(int osVersion) {
		this.osVersion = osVersion;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

//	public String getFbPosts() {
//		return fbPosts;
//	}
//
//	public void setFbPosts(String fbPosts) {
//		this.fbPosts = fbPosts;
//	}

	public int getNoOfFriends() {
		return noOfFriends;
	}

	public void setNoOfFriends(int noOfFriends) {
		this.noOfFriends = noOfFriends;
	}

	public String getCoverPhotoUrl() {
		return coverPhotoUrl;
	}

	public void setCoverPhotoUrl(String coverPhotoUrl) {
		this.coverPhotoUrl = coverPhotoUrl;
	}

	public String getFbUserId() {
		return fbUserId;
	}

	public void setFbUserId(String fbUserId) {
		this.fbUserId = fbUserId;
	}

	public String getFbTimelineUrl() {
		return fbTimelineUrl;
	}

	public void setFbTimelineUrl(String fbTimelineUrl) {
		this.fbTimelineUrl = fbTimelineUrl;
	}

//	public String getFbLikes() {
//		return fbLikes;
//	}
//
//	public void setFbLikes(String fbLikes) {
//		this.fbLikes = fbLikes;
//	}

	public String getFbWorkHistory() {
		return fbWorkHistory;
	}

	public void setFbWorkHistory(String fbWorkHistory) {
		this.fbWorkHistory = fbWorkHistory;
	}

	public String getFbEducationHistory() {
		return fbEducationHistory;
	}

	public void setFbEducationHistory(String fbEducationHistory) {
		this.fbEducationHistory = fbEducationHistory;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Date getVerificationCodeTime() {
		return verificationCodeTime;
	}

	public void setVerificationCodeTime(Date verificationCodeTime) {
		this.verificationCodeTime = verificationCodeTime;
	}

	public Date getUserVerificationTime() {
		return userVerificationTime;
	}

	public void setUserVerificationTime(Date userVerificationTime) {
		this.userVerificationTime = userVerificationTime;
	}

	public VerificationStatus getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(VerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getFbGroups() {
		return fbGroups;
	}

	public void setFbGroups(String fbGroups) {
		this.fbGroups = fbGroups;
	}

	public String getFbCreationDate() {
		return fbCreationDate;
	}

	public void setFbCreationDate(String fbCreationDate) {
		this.fbCreationDate = fbCreationDate;
	}

	public String getFbCurrentLocation() {
		return fbCurrentLocation;
	}

	public void setFbCurrentLocation(String fbCurrentLocation) {
		this.fbCurrentLocation = fbCurrentLocation;
	}

	public String getFbRelationsShips() {
		return fbRelationsShips;
	}

	public void setFbRelationsShips(String fbRelationsShips) {
		this.fbRelationsShips = fbRelationsShips;
	}
	@Override
	public String toString() {
		
		return "ID :"+id+" FirstName :"+firstName+" LastName "+lastName+" fbUd:"+fbUserId+" FBUrl :"+fbTimelineUrl;
	}
}
