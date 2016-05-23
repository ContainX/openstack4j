package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.senlin.Cluster;
import org.openstack4j.openstack.common.ListResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is a model of a senlinCluster. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("cluster")
public class SenlinCluster implements Cluster {
	private static final long serialVersionUID = -4148491479364580015L;

	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("data")
	private Map<String, Object> data;
	@JsonProperty("desired_capacity")
	private String desired_capacity;
	@JsonProperty("domain")
	private String domain;
	@JsonProperty("id")
	private String id;
	@JsonProperty("init_at")
	private String init_at;
	@JsonProperty("max_size")
	private String max_size;
	@JsonProperty("metadata")
	private Map<String, Object> metadata;
	@JsonProperty("min_size")
	private String min_size;
	@JsonProperty("name")
	private String name;
	@JsonProperty("nodes")
	private ArrayList nodes;
	@JsonProperty("policies")
	private ArrayList policies;
	@JsonProperty("profile_id")
	private String profile_id;
	@JsonProperty("profile_name")
	private String profile_name;
	@JsonProperty("project")
	private String project;
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_reason")
	private String status_reason;
	@JsonProperty("timeout")
	private String timeout;
	@JsonProperty("updated_at")
	private String updated_at;
	@JsonProperty("user")
	private String user;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "SenlinCluster{" +
				"created_at='" + created_at + '\'' +
				", data=" + data +
				", desired_capacity='" + desired_capacity + '\'' +
				", domain='" + domain + '\'' +
				", id='" + id + '\'' +
				", init_at='" + init_at + '\'' +
				", max_size='" + max_size + '\'' +
				", metadata=" + metadata +
				", min_size='" + min_size + '\'' +
				", name='" + name + '\'' +
				", nodes=" + nodes +
				", policies=" + policies +
				", profile_id='" + profile_id + '\'' +
				", profile_name='" + profile_name + '\'' +
				", project='" + project + '\'' +
				", status='" + status + '\'' +
				", status_reason='" + status_reason + '\'' +
				", timeout='" + timeout + '\'' +
				", updated_at='" + updated_at + '\'' +
				", user='" + user + '\'' +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinCluster
	 * 
	 * @author lion
	 * 
	 */
	public static class Cluster extends ListResult<SenlinCluster> {
		private static final long serialVersionUID = -3382404528209154988L;
		@JsonProperty("clusters")
		private List<SenlinCluster> list;

		protected List<SenlinCluster> value() {
			return list;
		}
	}
}
