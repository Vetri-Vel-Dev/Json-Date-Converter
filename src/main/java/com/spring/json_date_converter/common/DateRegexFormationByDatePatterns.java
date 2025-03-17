package com.spring.json_date_converter.common;

import java.util.HashMap;
import java.util.Map;

public class DateRegexFormationByDatePatterns {

	private static String[] DATE_FORMATS;
//	= List.of(
//		    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
//		    "yyyy-MM-dd'T'HH:mm:ss'Z'",
//		    "yyyy-MM-dd HH:mm:ss",
//		    "dd-MM-yyyy HH:mm:ss",
//		    "yyyy/MM/dd HH:mm:ss"
//		);

	public DateRegexFormationByDatePatterns(String[] dateformats) {
		DATE_FORMATS = dateformats;
	}

	public static final Map<String, String> formatToRegexMap = new HashMap<>();

	public static String convertDateFormatToRegex(String[] formats) {
		
//		for (String format : formats) {
			
//			formatToRegexMap.put(format, regex);
//		}
//		formatToRegexMap.forEach((key, value) -> System.out.println(key + " = " + value));
//
//		String dateRegex = "\"([^\"]+)\"\\s*:\\s*\"(" + String.join("|", formatToRegexMap.values()) + ")\"";
//		return dateRegex;
		for (String format : formats) {
//			String regex = format.replace("yyyy", "\\d{4}").replace("MM", "\\d{2}").replace("dd", "\\d{2}")
//					.replace("HH", "\\d{2}").replace("mm", "\\d{2}").replace("ss", "\\d{2}").replace("SSS", "\\d{3}")
//					.replace("Z", "Z?").replace("'", "").replace(".", "\\.");
			String regex = format.replace("yyyy", "\\d{4}").replace("MM", "\\d{2}").replace("dd", "\\d{2}")
					.replace("HH", "\\d{2}").replace("mm", "\\d{2}").replace("ss", "\\d{2}").replace("SSS", "\\d{3}")
					.replace("Z", "Z?").replace("'", "").replace(".", "\\.");
//
			formatToRegexMap.put(format, regex);
		}
		String dateRegex = "\"([^\"]+)\"\\s*:\\s*\"(" + String.join("|", formatToRegexMap.values()) + ")\"";
		return dateRegex;
//		  return formatToRegexMap;
	}
}
