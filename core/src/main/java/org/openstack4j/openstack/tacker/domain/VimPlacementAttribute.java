package org.openstack4j.openstack.tacker.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 16, 2016
 */
@JsonRootName("placement_attr")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VimPlacementAttribute {
	
	private List<String> regions;
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("regions", regions)
				.toString();
	}

	/**
	 * @return the regions
	 */
	public List<String> getRegions() {
		return regions;
	}
}