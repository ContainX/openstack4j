package org.openstack4j.openstack.compute.domain;

import java.util.Map;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Wrapper for flavor extra specs to get
 * 
 * @author Octopus Zhang
 */
public class ExtarSpecsWrapper implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty("extra_specs")
	Map<String, String> extraSpecs;

	public ExtarSpecsWrapper() { }

	public ExtarSpecsWrapper(Map<String, String> extraSpecs) {
		this.extraSpecs = extraSpecs;
	}
	/**
	 * Wraps the given extraSpecs into the wrapper
	 * 
	 * @param extraSpecs
	 * @return extraSpecs wrapper
	 */
	public static ExtarSpecsWrapper wrap(Map<String, String> extraSpecs) {
		return new ExtarSpecsWrapper(extraSpecs);
	}
	
	/**
	 * @return the extraSpecs
	 */
	public Map<String, String> getExtraSpecs() {
		return extraSpecs;
	}

}
