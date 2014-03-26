package org.openstack4j.openstack.internal;

import java.text.ParseException;
import java.util.Date;

import org.codehaus.jackson.map.util.StdDateFormat;

/**
 * Provides common parser routines when dealing with Headers or other non-json payloads
 * @author Jeremy Unruh
 */
public abstract class Parser {
	
	/**
	 * Takes a String Numeric and returns null or the Long value
	 * @param number the number in string form
	 * @return the Long or null
	 */
	protected Long asLong(String number) {
		if (number == null) return null;
		return Long.parseLong(number);
	}

	/**
	 * Takes a String Boolean and returns null or as Boolean value
	 * @param bool the boolean in String form
	 * @return the Boolean or null
	 */
	protected Boolean asBoolean(String bool) {
		if (bool == null) return null;
		return Boolean.parseBoolean(bool);
	}
	
	/**
	 * Takes a String Date and decodes it into a date.  Returns null if the date is null
	 * @param date the date in String form
	 * @return Date or null
	 */
	protected Date asDate(String date) {
		try {
			if (date != null)
				return StdDateFormat.instance.parse(date);
		}
		catch (ParseException e) { 
			e.printStackTrace();
		}
		return null;
	}

}
