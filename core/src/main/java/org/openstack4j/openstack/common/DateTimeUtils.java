package org.openstack4j.openstack.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	/**
	 * DateTime format YYYY-MM-dd'T'HH:mm'Z'
	 */
	public static final String FORMAT_YMDTHMZ = "YYYY-MM-dd'T'HH:mm'Z'";
	
	/**
	 * DateTime format YYYY-MM-dd'T'HH:mm:ss'Z'
	 */
	public static final String FORMAT_YMDTHMSZ = "YYYY-MM-dd'T'HH:mm:ss'Z'";
	
	/**
	 * DateTime format YYYY-MM-dd'T'HH:mm:ss'Z'
	 * 
	 * example: 2016-12-02T13:00:00.121
	 */
	public static final String FORMAT_YMDTHMS_SSS = "YYYY-MM-dd'T'HH:mm:ss.SSS";
	
	/**
	 * DateTime format YYYY-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_YMDHMS = "YYYY-MM-dd HH:mm:ss";
	
	/**
	 * format date
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
}
