package org.openstack4j.openstack.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.util.StdDateFormat;

/**
 * Provides common parser routines when dealing with Headers or other non-json payloads
 * @author Jeremy Unruh
 */
public final class Parser {
	
    private static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private static SimpleDateFormat DF;
    private static final SimpleDateFormat RFC822_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);

    
	/**
	 * Takes a String Numeric and returns null or the Long value
	 * @param number the number in string form
	 * @return the Long or null
	 */
	public static Long asLong(String number) {
		return asLong(number, null);
	}
	
	/**
     * Takes a String Numeric and returns null or the Long value
     * @param number the number in string form
     * @param defaultValue the default value if number is null
     * @return the Long or null
     */
	public static Long asLong(String number, Long defaultValue) {
	    if (number == null) return defaultValue;
        return Long.parseLong(number);
	}

	/**
	 * Takes a String Boolean and returns null or as Boolean value
	 * @param bool the boolean in String form
	 * @return the Boolean or null
	 */
	public static Boolean asBoolean(String bool) {
		if (bool == null) return null;
		return Boolean.parseBoolean(bool);
	}
	
	/**
	 * Takes a String Date and decodes it into a date.  Returns null if the date is null
	 * @param date the date in String form
	 * @return Date or null
	 */
	public static Date asDate(String date) {
		try {
			if (date != null)
				return StdDateFormat.instance.parse(date);
		}
		catch (ParseException e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Takes a Date and returns it's equivalent in RFC 1123
	 * @param date the date to format
	 * @return the formatted date string
	 */
	public static String toRFC1123(Date date) 
	{
	    if (DF == null) {
	        DF = new SimpleDateFormat(PATTERN_RFC1123, Locale.US);
	        DF.setTimeZone(TimeZone.getTimeZone("GMT"));
	    }
	    return DF.format(date);
	}
	
	/**
	 * Parses a String in RFC 822 format into a Date object
	 * 
	 * @param toParse the date to parse
	 * @return the parsed date
	 */
	public static Date toRFC822DateParse(String toParse) {
	      synchronized (RFC822_FORMAT) {
	         try {
	            return RFC822_FORMAT.parse(toParse);
	         } catch (ParseException pe) {
	            throw new IllegalArgumentException("Error parsing date at " + pe.getErrorOffset(), pe);
	         }
	      }
	   }
}
