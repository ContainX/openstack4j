package org.openstack4j.model.telemetry;

import java.util.Date;
import java.util.Map;

import org.openstack4j.model.ModelEntity;

/**
 * A single measurement for a given meter and resource.
 * 
 * @author Jeremy Unruh
 */
public interface Sample extends ModelEntity {

	/**
	 * @return the name of the meter
	 */
	String getCounterName();
	
	/**
	 * @return the type of meter
	 */
	Meter.Type getCounterType();

	/**
	 * @return The unit of measure for the value in the counter volume
	 */
	String getCounterUnit();

	/**
	 * @return the actual measured value
	 */
	Float getCounterVolume();

	/**
	 * @return The ID of the source that identifies where the sample comes from
	 */
	String getSource();

	/**
	 * @return he ID of the project or tenant that owns the resource
	 */
	String getProjectId();

	/**
	 * @return The ID of the user who last triggered an update to the resource
	 */
	String getUserId();

	/**
	 * @return The ID of the Resource for which the measurements are taken
	 */
	String getResourceId();

	/**
	 * @return UTC date and time when the measurement was made
	 */
	Date getTimestamp();
	
	/**
   * @return UTC date and time when the sample was recorded
   */
	Date getRecordedAt();

	/**
	 * @return A unique identifier for the sample
	 */
	String getMessageId();

	/**
	 * @return Arbitrary metadata associated with the resource
	 */
	Map<String, Object> getMetadata();

	
}
