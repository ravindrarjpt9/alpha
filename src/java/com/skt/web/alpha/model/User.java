package com.skt.web.alpha.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skt.web.alpha.constants.DeviceType;
import com.skt.web.alpha.constants.Gender;
import com.skt.web.alpha.constants.MaritalStatus;
import com.skt.web.alpha.constants.UserStatus;
import com.skt.web.common.model.BaseModel;

@NamedQueries({ @NamedQuery(name = "findUserByFbUserId", query = "select u from User u "
		+ "where u.fbUserId = :fbUserId") })
@Entity
@Table(name = "users")
// @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
// property = "@id")
public class User extends BaseModel {

	private static final long serialVersionUID = -2492101905224479548L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	// To disable getter and setter
	@JsonIgnore
	private Set<Registration> registrations = new HashSet<>();

	@Column(name = "IMEI_NUM")
	private String imeiNo;

	@Column(name = "PUSH_ID")
	private String pushId;

	@Column(name = "DEVICE_MODEL")
	private String deviceModel;

	@Column(name = "DEVICE_TYPE")
	@Enumerated(EnumType.ORDINAL)
	private DeviceType deviceType;

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
	private Gender gender;

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

//	@Lob
//	@Column(name = "FB_LIKES")
//	private String fbLikes;

	@Lob
	@Column(name = "FB_GROUPS")
	private String fbGroups;

	
	@Lob
	@Column(name = "FB_WORK_HISTORY")
	private String fbWorkHistory;

	@Lob
	@Column(name = "FB_EDUCATION_HISTORY")
	private String fbEducationHistory;
	
	@Column(name = "FB_CREATION_DATE")
	private String fbCreationDate;
	
	@Column(name = "FB_CURRENT_LOCATION")
	private String fbCurrentLocation;
	
	@Column(name = "FB_RELATIONSHIP_STATUS")
	private String fbRelationsShips;
	

	@Column(name = "MARITAL_STATUS")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Column(name = "INVITATION_CODE")
	private String invitationCode;

	@Column(name = "USER_CREATION_TIME", updatable = false)
	private Date userCreationTime = new Date();

	@Column(name = "USER_STATUS")
	@Enumerated(EnumType.STRING)
	// Setting default value of userStatus as ACTIVE
	private UserStatus userStatus = UserStatus.ACTIVE;

	@Column(name = "CITY")
	private String city;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "LOCALITY")
	private String locality;

	@Column(name = "NATIVE_LOCATION")
	private String nativeLocation;

	@Lob
	@Column(name = "INTERESTS")
	private String interests;

	@Column(name = "JOB_TYPE")
	private String jobType;

	@Column(name = "INDUSTRY")
	private String industry;

	@Column(name = "COLLEGE")
	private String college;

	@Column(name = "SCHOOL")
	private String school;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "USER_SCORE")
	private int userScore;

	@Column(name = "USER_LIKE_COUNT")
	private int userLikeCount;

	@Column(name = "USER_SPAM_COUNT")
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

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	// Return a clone of the userCreationTime
	public Date getUserCreationTime() {
		if (userCreationTime != null) {
			return (Date) this.userCreationTime.clone();
		}
		return null;
	}

	// Commenting setter, as userCreationTime should never be updatedd
	// public void setUserCreationTime(Date userCreationTime) {
	// this.userCreationTime = userCreationTime;
	// }

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

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
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
	@Override
	public String toString() {
		
		return "{ id :"+id+" imeiNo :"+imeiNo+" First Name :"+firstName +" LastName :"+lastName +" fbId"+fbUserId+" fbURL :"+fbTimelineUrl;
	}
}
