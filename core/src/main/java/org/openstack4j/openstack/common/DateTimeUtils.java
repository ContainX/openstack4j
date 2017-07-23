package org.openstack4j.openstack.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm'Z'
	 */
	public static final String FORMAT_YMDTHMZ = "yyyy-MM-dd'T'HH:mm'Z'";
	
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm:ss'Z'
	 */
	public static final String FORMAT_YMDTHMSZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	/**
	 * DateTime format yyyy-MM-dd'T'HH:mm:ss'Z'
	 * 
	 * example: 2016-12-02T13:00:00.121
	 */
	public static final String FORMAT_YMDTHMS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	
	public static final String FORMAT_YMDTHMS_SSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	/**
	 * DateTime format yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * DateTime format yyyy-MM-d
	 */
	public static final String FORMAT_YMD = "yyyy-MM-d";
	
	/**
	 * format date
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(format(new Date(), FORMAT_YMDTHMS_SSSZ));
	}
}
