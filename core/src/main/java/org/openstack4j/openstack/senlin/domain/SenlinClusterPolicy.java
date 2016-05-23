package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.senlin.ClusterPolicy;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * This is a model of a senlinCluster_policy. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("cluster_policy")
public class SenlinClusterPolicy implements ClusterPolicy {
	private static final long serialVersionUID = 5451213592273429432L;

	@JsonProperty("cluster_id")
	private String cluster_id;
	@JsonProperty("cluster_name")
	private String cluster_name;
	@JsonProperty("enabled")
	private String enabled;
	@JsonProperty("id")
	private String id;
	@JsonProperty("policy_id")
	private String policy_id;
	@JsonProperty("policy_name")
	private String policy_name;
	@JsonProperty("policy_type")
	private String policy_type;
	@JsonProperty("name")

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "SenlinClusterPolicy{" +
				"cluster_id='" + cluster_id + '\'' +
				", cluster_name='" + cluster_name + '\'' +
				", enabled='" + enabled + '\'' +
				", id='" + id + '\'' +
				", policy_id='" + policy_id + '\'' +
				", policy_name='" + policy_name + '\'' +
				", policy_type='" + policy_type + '\'' +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinCluster_policy
	 * 
	 * @author lion
	 * 
	 */
	public static class ClusterPolicy extends ListResult<SenlinClusterPolicy> {
		private static final long serialVersionUID = 600661296207420793L;
		
		@JsonProperty("cluster_policies")
		private List<SenlinClusterPolicy> list;

		protected List<SenlinClusterPolicy> value() {
			return list;
		}
	}
}
