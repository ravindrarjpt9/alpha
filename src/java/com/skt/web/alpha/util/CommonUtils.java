package com.skt.web.alpha.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	public String generateRandomString(int length) {
		// Random string will be alpha-numeric
		// char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
		// .toCharArray();

		// Random string will contain integers only
		char[] CHARSET_AZ_09 = "0123456789".toCharArray();
		char[] characterSet = CHARSET_AZ_09;
		Random random = new Random();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}
		return new String(result);
	}

	public long compareDatesInMinutes(Date newDate, Date oldDate,
			int minutesFactor) {
		long oldDateMillis = oldDate.getTime();
		long newDateMillis = newDate.getTime();
		long factorInMillis = 0;
		if (minutesFactor != 0) {
			factorInMillis = minutesFactor * 60 * 1000;
		}
		long differenceInMillis = newDateMillis
				- (oldDateMillis + factorInMillis);
		return differenceInMillis;
	}

	public Date convertStringToDate(String date, String strLocale)
			throws ParseException {
		Locale locale = Locale.forLanguageTag(strLocale.replace('_', '-'));
		DateFormat format = DateFormat
				.getDateInstance(DateFormat.SHORT, locale);
		return format.parse(date);
	}

	public String convertDateToString(Date date, String strLocale)
			throws ParseException {
		Locale locale = Locale.forLanguageTag(strLocale.replace('_', '-'));
		DateFormat format = DateFormat
				.getDateInstance(DateFormat.SHORT, locale);
		return format.format(date);
	}

	public int calculateAge(Date dob) {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(dob);
		Calendar now = Calendar.getInstance();
		int age = now.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		// If the month of birth has not yet arrived in current year
		if (now.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH)) {
			age--;
		}
		// If (now.Month == dateOfBirth.Month) but, date is still less
		else if (now.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)
				&& now.get(Calendar.DAY_OF_MONTH) < dateOfBirth
						.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return age;
	}
}
