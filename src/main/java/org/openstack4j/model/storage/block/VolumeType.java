package org.openstack4j.model.storage.block;

import java.util.Map;

import org.openstack4j.model.ModelEntity;

/**
 * The volume type defines the characteristics of a volume. It usually maps to a set of capabilities
 * of the storage back-end driver to be used for this volume. 
 * Examples: "Performance", "SSD", "Backup", etc. 
 * 
 * @author Jeremy Unruh
 */
public interface VolumeType extends ModelEntity {

	/**
	 * @return the identifier for the volume type
	 */
	String getId();
	
	/**
	 * @return the name of the volume type
	 */
	String getName();
	
	/**
	 * @return the extra specifications (meta-data) associated with the volume type
	 */
	Map<String, String> getExtraSpecs();
	
	
}
