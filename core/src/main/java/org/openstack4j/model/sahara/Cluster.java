package org.openstack4j.model.sahara;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

/**
 * An OpenStack Cluster
 * 
 * @author Ekasit Kijsipongse 
 */
public interface Cluster extends ModelEntity {

	/**
	 * The current cluster Status
	 * 
	 */
	public enum Status {
		ACTIVE, CREATING, UNRECOGNIZED;
		
		@JsonValue
		public String value() {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static Status fromValue(String status) {
			try {
				return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(status, "status must not be null")));
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}
	
	/**
	 * @return the identifier for the cluster
	 */
	String getId();
	
	/**
	 * @return the name of the cluster
	 */
	String getName();
	
	/**
	 * @return the description of the cluster
	 */
	String getDescription();
	
	/**
	 * @return the status of the cluster
	 */
	Status getStatus();
	
	/**
	 * @return the plugin of the cluster
	 */
	String getPlugin();
	
	/**
	 * @return the hadoop version of the cluster
	 */
	String getHadoopVersion();
	
	/**
	 * @return the created date of the cluster
	 */
	Date getCreatedAt();
	
	/**
	 * @return the base image of the cluster
	 */
	String getBaseImage();
	
}
