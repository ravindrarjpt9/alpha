package com.skt.web.alpha.constants;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

	private Constants() {
		throw new RuntimeException(
				"Cannot instantiate object of Constants class");
	}

	public static final String PTOTOCOL = "http";
	
	public static  String HOST = "app.neargroup.in";
	
	//public static final String HOST = "119.9.107.53:8080";

	public static final String APPLICATION_NAME = "alphaserver";
	
	public static final String SERVICE_NAME = "alpha/getImage";
	
	public static final int VERIFICATION_CODE_LENGTH = 4;

	public static final String VERIFICATION_PUSH_MESSAGE = "NearGroup verification code is: ";

	public static final int VERIFICATION_MINUTES_RANGE = 30;

	public static  int MINIMUM_FRIENDS_REQUIRED = 50;
	
	public static String ADMIN_USER_FB_ID = "935276429872137";

	public static final String ERROR_MSG_INVALID_USER = "Sorry. Minimum "
			+ MINIMUM_FRIENDS_REQUIRED
			+ " friends on facebook are needed. Please try again later.";

	public static final String ERROR_MSG_USER_ALREADY_EXITS = "User with this FB user account already exists. "
			+ "Please login with the existing account.";

	public static final String ERROR_MSG_VERIFICATION_CODE_EXPIRED = "Verification code has expired for the REGISTRATION_ID: ";

	public static final String ERROR_MSG_INVALID_VERIFICATION_CODE = "Invalid verification code entered for the REGISTRATION_ID: ";

	public static final String ERROR_MSG_DOB_PARSE_EXCEPTION = "Unable to parse DOB";
	
	public static final String ERROR_MSG_FILE_DATA_BYTE = "Unable to convert file data to byte";

	public static final String ERROR_MSG_UNABLE_TO_CONVERT_DATE_TO_STRING = "Unable to convert Date to String";

	public static final String GROUP_NAME_SEPARATOR = "-";

	public static final String IN = " in ";

	public static final String GROUP_IN = " Group in ";

	public static final String GROUP = " Group";
	
	public static final String RESIDENTS = " Residents";

	public static final String GIRLS = "Girls";
	
	public static final String ONLY = "Only ";
	

	public static final String GIRLS_IN = "Girls in ";

	public static final int FOURTY_INTEGER = 40;

	public static final int THIRTY_FIVE_INTEGER = 35;

	public static final String ZERO_TO_FOURTY_STRING = "0-40";

	public static final String ZERO_TO_THIRTY_FIVE_STRING = "0-35";

	public static final String FOURTY_ABOVE_STRING = "40+";

	public static final String THIRTY_FIVE_ABOVE_STRING = "35+";

	public static final String NONE_OR_OTHER = "None/Other";

	public static final String AMERICA = "America";

	public static final String CURRENT_LOCATION = "Current Location";

	public static final String LOCALITY_OR_APARTMENT = "Locality or Apartment";

	public static final String NATIVE_LOCATION = "Native Location";

	public static final String JOB_TYPE = "Job Type";

	public static final String INDUSTRY = "Industry";

	public static final String YOUNG_ACHIEVERS_IN= "Achievers In ";
	
	public static final String YOUNG_ACHIEVERS= "Young Achievers ";
	
	public static final String NEAR_BY = "Nearby";
	
	public static final String SPACE = " ";

	public static final String ACHIEVERS_CODE = "250";

	public static final String COUNTRY_CODE_INDIA = "+91";

	public static final String COUNTRY_CODE_US = "+1";

	public static final Map<String, String> COLLEGE_CODE_MAP = new HashMap<>();

	public static final Map<String, String> CORPORATE_CODE_MAP = new HashMap<>();
	
	public static final Map<String, String> SCHOOL_CODE_MAP = new HashMap<>();

	public static final String STUDENT_COLLEGE = "Student - College";

	public static final String ALUMNI_IN = " Alumni in ";

	public static final String SUCCESS_MSG_LEAVE_TOPIC = "Successfully left the topic chat.";

	static {
		//TODO : Need to add more code
		CORPORATE_CODE_MAP.put("2000", "24 / 7 Customer");
		CORPORATE_CODE_MAP.put("2010", "2Coms Consulting");
		CORPORATE_CODE_MAP.put("2020", "3 Global Services");
	}
	
	static {
		SCHOOL_CODE_MAP.put("4000", "Vasant Valley School, Delhi");
		SCHOOL_CODE_MAP.put("4010", "Cathedral and John Connon School, Mumbai");
		SCHOOL_CODE_MAP.put("4020", "Valley School, Bangalore");
		
	}
	static {
		COLLEGE_CODE_MAP.put("110", "AIMA");
		COLLEGE_CODE_MAP.put("120", "Aligarh Muslim University");
		COLLEGE_CODE_MAP.put("130", "Allahabad University");
		COLLEGE_CODE_MAP.put("140", "Amaravati University");
		COLLEGE_CODE_MAP.put("150", "Amity Business School");
		COLLEGE_CODE_MAP.put("160", "Andhra University");
		COLLEGE_CODE_MAP.put("170", "Anna University");
		COLLEGE_CODE_MAP.put("180", "Annamalai University");
		COLLEGE_CODE_MAP.put("190", "Apeejay School of Marketing");
		COLLEGE_CODE_MAP.put("200", "APTECH");
		COLLEGE_CODE_MAP.put("210", "Banaras Hindu University");
		COLLEGE_CODE_MAP.put("220", "Bangalore University");
		COLLEGE_CODE_MAP.put("230", "Barkatullah University");
		COLLEGE_CODE_MAP.put("240", "Berhampur University");
		COLLEGE_CODE_MAP.put("250", "Bharathiar University");
		COLLEGE_CODE_MAP.put("260", "Bharathidasan University");
		COLLEGE_CODE_MAP.put("270", "BITS (Mesra)");
		COLLEGE_CODE_MAP.put("280", "BITS (Pilani)");
		COLLEGE_CODE_MAP.put("290", "Board of Technical Education");
		COLLEGE_CODE_MAP.put("300", "Calcutta University");
		COLLEGE_CODE_MAP.put("310", "Calicut University");
		COLLEGE_CODE_MAP.put("320", "CDAC");
		COLLEGE_CODE_MAP.put("330", "Chennai University");
		COLLEGE_CODE_MAP.put("340", "DAVV Indore");
		COLLEGE_CODE_MAP.put("350", "Delhi College of Engineering");
		COLLEGE_CODE_MAP.put("360", "Delhi University");
		COLLEGE_CODE_MAP.put("370", "Devi Ahilya University");
		COLLEGE_CODE_MAP.put("380", "DOEACC");
		COLLEGE_CODE_MAP.put("390", "Dr.B.R.Ambedkar University Agra");
		COLLEGE_CODE_MAP.put("400", "Fergusson College Pune");
		COLLEGE_CODE_MAP.put("410", "Film & Television Inst. of India Pune");
		COLLEGE_CODE_MAP.put("420", "FMS Delhi");
		COLLEGE_CODE_MAP.put("430", "Fore School of Management");
		COLLEGE_CODE_MAP.put("440", "Goa Institute of Management");
		COLLEGE_CODE_MAP.put("450", "Goa University");
		COLLEGE_CODE_MAP.put("460", "Gujarat University");
		COLLEGE_CODE_MAP.put("470", "Gulbarga University");
		COLLEGE_CODE_MAP.put("480", "Hindu College Delhi");
		COLLEGE_CODE_MAP.put("490", "Hyderabad University");
		COLLEGE_CODE_MAP.put("500", "ICFAI");
		COLLEGE_CODE_MAP.put("510", "ICSI");
		COLLEGE_CODE_MAP.put("520", "ICWA");
		COLLEGE_CODE_MAP.put("530", "IGNOU");
		COLLEGE_CODE_MAP.put("540", "IIIT Hyderabad");
		COLLEGE_CODE_MAP.put("550", "IIM Ahmedabad");
		COLLEGE_CODE_MAP.put("560", "IIM Bangalore");
		COLLEGE_CODE_MAP.put("570", "IIM Calcutta");
		COLLEGE_CODE_MAP.put("580", "IIM Indore");
		COLLEGE_CODE_MAP.put("590", "IIM Kozhikode");
		COLLEGE_CODE_MAP.put("600", "IIM Lucknow");
		COLLEGE_CODE_MAP.put("610", "IIMT STUDIES");
		COLLEGE_CODE_MAP.put("620", "IIPM");
		COLLEGE_CODE_MAP.put("630", "IISWBM Calcutta");
		COLLEGE_CODE_MAP.put("640", "IIT Chennai");
		COLLEGE_CODE_MAP.put("650", "IIT Delhi");
		COLLEGE_CODE_MAP.put("660", "IIT Guwahati");
		COLLEGE_CODE_MAP.put("670", "IIT Kanpur");
		COLLEGE_CODE_MAP.put("680", "IIT Kharagpur");
		COLLEGE_CODE_MAP.put("690", "IIT Mumbai");
		COLLEGE_CODE_MAP.put("700", "IIT Roorkee");
		COLLEGE_CODE_MAP.put("710", "IMI Delhi");
		COLLEGE_CODE_MAP.put("720", "IMT Ghaziabad");
		COLLEGE_CODE_MAP.put("730", "Indian Institute of Foreign Trade");
		COLLEGE_CODE_MAP.put("740", "Indian Institute of Science");
		COLLEGE_CODE_MAP.put("750", "Indian Statistical Institute");
		COLLEGE_CODE_MAP.put("760", "Institute of Chartered Accountants of India");
		COLLEGE_CODE_MAP.put("770", "IRM Anand");
		COLLEGE_CODE_MAP.put("780", "IT BHU");
		COLLEGE_CODE_MAP.put("790", "Jadavpur University");
		COLLEGE_CODE_MAP.put("800", "Jamia Millia Islamia");
		COLLEGE_CODE_MAP.put("810", "Jamnalal Bajaj Institute of Mgmt Studies");
		COLLEGE_CODE_MAP.put("820", "Jawaharlal Nehru University");
		COLLEGE_CODE_MAP.put("830", "Jiwaji University");
		COLLEGE_CODE_MAP.put("840", "JNTU Hyderabad");
		COLLEGE_CODE_MAP.put("850", "Kakatiya University");
		COLLEGE_CODE_MAP.put("860", "Kanpur University");
		COLLEGE_CODE_MAP.put("870", "Karanataka University");
		COLLEGE_CODE_MAP.put("880", "Kerala University");
		COLLEGE_CODE_MAP.put("890", "Kurukshetra University");
		COLLEGE_CODE_MAP.put("900", "Kuvempu University");
		COLLEGE_CODE_MAP.put("910", "Loyola College Chennai");
		COLLEGE_CODE_MAP.put("920", "LSR College Delhi");
		COLLEGE_CODE_MAP.put("930", "Lucknow University");
		COLLEGE_CODE_MAP.put("940", "Madras Medical College Chennai");
		COLLEGE_CODE_MAP.put("950", "Madurai Kamaraj University");
		COLLEGE_CODE_MAP.put("960", "Mahatma Gandhi University");
		COLLEGE_CODE_MAP.put("970", "Mangalore University");
		COLLEGE_CODE_MAP.put("980", "Maulana Azad Medical College Delhi");
		COLLEGE_CODE_MAP.put("990", "MDI Gurgaon");
		COLLEGE_CODE_MAP.put("1000", "Meerut University");
		COLLEGE_CODE_MAP.put("1010", "Miranda House Delhi");
		COLLEGE_CODE_MAP.put("1020", "Mizoram University");
		COLLEGE_CODE_MAP.put("1030", "Mumbai University");
		COLLEGE_CODE_MAP.put("1040", "Nagaland University");
		COLLEGE_CODE_MAP.put("1050", "Nagarjuna University");
		COLLEGE_CODE_MAP.put("1060", "Nagpur University");
		COLLEGE_CODE_MAP.put("1070", "National Institute of Design Ahmedabad");
		COLLEGE_CODE_MAP.put("1080", "National Institute of Engineering");
		COLLEGE_CODE_MAP.put("1090", "National Institute of Fashion Technology");
		COLLEGE_CODE_MAP.put("1100", "NIFT");
		COLLEGE_CODE_MAP.put("1110", "NIIT");
		COLLEGE_CODE_MAP.put("1120", "NIT/RECs");
		COLLEGE_CODE_MAP.put("1130", "NMIMS Mumbai");
		COLLEGE_CODE_MAP.put("1140", "North Maharashtra University");
		COLLEGE_CODE_MAP.put("1150", "Oberoi Centre for Learning & Development");
		COLLEGE_CODE_MAP.put("1160", "Osmania University");
		COLLEGE_CODE_MAP.put("1170", "Patna University");
		COLLEGE_CODE_MAP.put("1180", "Pondicherry University");
		COLLEGE_CODE_MAP.put("1190", "Presidency College Chennai");
		COLLEGE_CODE_MAP.put("1200", "Presidency College Kolkatta");
		COLLEGE_CODE_MAP.put("1210", "PSG Coimbatore");
		COLLEGE_CODE_MAP.put("1220", "Pune University");
		COLLEGE_CODE_MAP.put("1230", "Punjab Technical University");
		COLLEGE_CODE_MAP.put("1240", "Punjab University");
		COLLEGE_CODE_MAP.put("1250", "Rajasthan University");
		COLLEGE_CODE_MAP.put("1260", "Ranchi University");
		COLLEGE_CODE_MAP.put("1270", "RV College Of Engineering");
		COLLEGE_CODE_MAP.put("1280", "Sambalpur University");
		COLLEGE_CODE_MAP.put("1290", "Sardar Patel University");
		COLLEGE_CODE_MAP.put("1300", "Saurashtra University");
		COLLEGE_CODE_MAP.put("1310", "School Of Planning & Architecture");
		COLLEGE_CODE_MAP.put("1320", "Shivaji University");
		COLLEGE_CODE_MAP.put("1330", "South Gujarat University Surat");
		COLLEGE_CODE_MAP.put("1340", "SP Jain Institute of Management Studies");
		COLLEGE_CODE_MAP.put("1350", "SRCC Delhi");
		COLLEGE_CODE_MAP.put("1360", "Sri Jayachamarajendra College of Engineering");
		COLLEGE_CODE_MAP.put("1370", "Sri Venkateshwara College Delhi");
		COLLEGE_CODE_MAP.put("1380", "Sri Venkateshwara University");
		COLLEGE_CODE_MAP.put("1390", "St Stephens College Delhi");
		COLLEGE_CODE_MAP.put("1400", "St.Xaviers College Kolkatta");
		COLLEGE_CODE_MAP.put("1410", "St.Xaviers College Mumbai");
		COLLEGE_CODE_MAP.put("1420", "Stella Maris College Chennai");
		COLLEGE_CODE_MAP.put("1430", "Symbiosis Pune");
		COLLEGE_CODE_MAP.put("1440", "TA Pai Management Institute");
		COLLEGE_CODE_MAP.put("1450", "Tata Institute of Social Sciences");
		COLLEGE_CODE_MAP.put("1460", "Thapar Institute of Engineering & Technology");
		COLLEGE_CODE_MAP.put("1470", "The National Law School Bangalore");
		COLLEGE_CODE_MAP.put("1480", "The Times School of Journalism");
		COLLEGE_CODE_MAP.put("1490", "The Times School of Marketing");
		COLLEGE_CODE_MAP.put("1500", "University of Mysore");
		COLLEGE_CODE_MAP.put("1510", "Utkal University");
		COLLEGE_CODE_MAP.put("1520", "Vellore Engineering College");
		COLLEGE_CODE_MAP.put("1530", "Visveshwaraiah University");
		COLLEGE_CODE_MAP.put("1540", "Welcome Group Grad. Sch. of Hotel Admin Manipal");
		COLLEGE_CODE_MAP.put("1550", "XIM Bhubaneswar");
		COLLEGE_CODE_MAP.put("1560", "XISS Ranchi");
		COLLEGE_CODE_MAP.put("1570", "XLRI Jamshedpur");
		COLLEGE_CODE_MAP.put("1580", "YMCA");
	}
	
	
	
	
	// Group Interest 
	
	public static final String LOCALITY_GROUP = "Locality Group";
	public static final String NATIVE_LOCATION_GROUP = "Native Location Group";
	public static final String JOB_PROFILE_GROUP = "Job-Profile Group";
	public static final String INDUSTRY_GROUP = "Industry Group";
	public static final String GRILS_ONLY_GROUP = "Grils Only Group";
	public static final String YOUNG_ACHIEVER_GROUP = "Young Achiever Group";
	public static final String COLLEGE_STUDENT = "College Student";
	public static final String SCHOOL_STUDENT = "School Student";
	public static final String COLLEGE_ALUMNI = "College Alumni";
	public static final String SCHOOL_ALUMNI = "School Alumni";
	public static final String COMPANY_GROUP = "Company Group";
	
	
	
	
	
	
	
	
	
	
			
}
