package com.skt.web.alpha.to;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skt.web.alpha.constants.Gender;
import com.skt.web.alpha.constants.MaritalStatus;
import com.skt.web.alpha.constants.UserStatus;
import com.skt.web.alpha.model.Registration;

public class UserTo {

	private int id;

	// To disable getter and setter
	@JsonIgnore
	// TODO: Change to registrationTo
	private Set<Registration> registrations = new HashSet<>();

	private String imeiNo;

	private String pushId;

	private String deviceModel;

	private int deviceType;

	private String manufacturer;

	private int osVersion;

	private String locale;

	private String countryCode;

	private long mobileNo;

	private String firstName;

	private String lastName;

	private String fullName;

	private Date dob;

	private String aboutMe;

	private Gender gender;

	private String eMail;

	//private List<FbPost> fbPosts;
	private String fbGroups;
	
	private String fbCreationDate;
	
	private String fbCurrentLocation;
	
	private String fbRelationsShips;


	private int noOfFriends;

	private String coverPhotoUrl;

	private String fbUserId;

	private String fbTimelineUrl;

	//private List<FbLike> fbLikes;

	private List<FbWorkHistory> fbWorkHistory;

	private List<FbEducationHistory> fbEducationHistory;

	private MaritalStatus maritalStatus;

	private String invitationCode;

	private Date userCreationTime;

	private UserStatus userStatus;

	private String city;

	private String location;

	private String locality;

	private String nativeLocation;

	private List<String> interests;

	private String jobType;

	private String industry;

	private String college;

	private String school;

	private String company;

	private int userScore;

	private int userLikeCount;

	private int userSpamCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
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

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
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

//	public List<FbPost> getFbPosts() {
//		return fbPosts;
//	}
//
//	public void setFbPosts(List<FbPost> fbPosts) {
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

//	public List<FbLike> getFbLikes() {
//		return fbLikes;
//	}
//
//	public void setFbLikes(List<FbLike> fbLikes) {
//		this.fbLikes = fbLikes;
//	}

	public List<FbWorkHistory> getFbWorkHistory() {
		return fbWorkHistory;
	}

	public void setFbWorkHistory(List<FbWorkHistory> fbWorkHistory) {
		this.fbWorkHistory = fbWorkHistory;
	}

	public List<FbEducationHistory> getFbEducationHistory() {
		return fbEducationHistory;
	}

	public void setFbEducationHistory(
			List<FbEducationHistory> fbEducationHistory) {
		this.fbEducationHistory = fbEducationHistory;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public Date getUserCreationTime() {
		return userCreationTime;
	}

	public void setUserCreationTime(Date userCreationTime) {
		this.userCreationTime = userCreationTime;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getNativeLocation() {
		return nativeLocation;
	}

	public void setNativeLocation(String nativeLocation) {
		this.nativeLocation = nativeLocation;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public int getUserLikeCount() {
		return userLikeCount;
	}

	public void setUserLikeCount(int userLikeCount) {
		this.userLikeCount = userLikeCount;
	}

	public int getUserSpamCount() {
		return userSpamCount;
	}

	public void setUserSpamCount(int userSpamCount) {
		this.userSpamCount = userSpamCount;
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
}
