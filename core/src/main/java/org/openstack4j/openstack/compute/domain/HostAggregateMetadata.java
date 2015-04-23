package org.openstack4j.openstack.compute.domain;

import java.util.Map;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonRootName;
/**
 * set metadata for an aggregate.
 * @author liujunpeng
 */
@JsonRootName("set_metadata")
public class HostAggregateMetadata implements ModelEntity {

	private static final long serialVersionUID = 1L;

	Map <String, String> metadata;
	/**
	 * @param metadata for hostAggregateMetadata
	 * @return HostAggregateMetadata
	 */
	public HostAggregateMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	
	public HostAggregateMetadata() {
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	

}
