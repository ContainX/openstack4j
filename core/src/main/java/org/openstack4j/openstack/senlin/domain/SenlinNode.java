package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.senlin.Node;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;
import java.util.Map;

/**
 * This is a model of a senlinNode. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("node")
public class SenlinNode implements Node {
	private static final long serialVersionUID = 4953618741806882198L;

	@JsonProperty("cluster_id")
	private String cluster_id;
	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("domain")
	private String domain;
	@JsonProperty("id")
	private String id;
	@JsonProperty("index")
	private String index;
	@JsonProperty("data")
	private Map<String, Object> data;
	@JsonProperty("details")
	private Map<String, Object> details;
	@JsonProperty("init_at")
	private String init_at;
	@JsonProperty("metadata")
	private Map<String, Object> metadata;
	@JsonProperty("name")
	private String name;
	@JsonProperty("physical_id")
	private String physical_id;
	@JsonProperty("profile_id")
	private String profile_id;
	@JsonProperty("profile_name")
	private String profile_name;
	@JsonProperty("project")
	private String project;
	@JsonProperty("role")
	private String role;
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_reason")
	private String status_reason;
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
		return "SenlinNode{" +
				"cluster_id='" + cluster_id + '\'' +
				", created_at='" + created_at + '\'' +
				", domain='" + domain + '\'' +
				", id='" + id + '\'' +
				", index='" + index + '\'' +
				", data=" + data +
				", details=" + details +
				", init_at='" + init_at + '\'' +
				", metadata=" + metadata +
				", name='" + name + '\'' +
				", physical_id='" + physical_id + '\'' +
				", profile_id='" + profile_id + '\'' +
				", profile_name='" + profile_name + '\'' +
				", project='" + project + '\'' +
				", role='" + role + '\'' +
				", status='" + status + '\'' +
				", status_reason='" + status_reason + '\'' +
				", updated_at='" + updated_at + '\'' +
				", user='" + user + '\'' +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinNode
	 * 
	 * @author lion
	 * 
	 */
	public static class Node extends ListResult<SenlinNode> {
		private static final long serialVersionUID = 9043454000625258331L;

		@JsonProperty("nodes")
		private List<SenlinNode> list;

		protected List<SenlinNode> value() {
			return list;
		}
	}
}
