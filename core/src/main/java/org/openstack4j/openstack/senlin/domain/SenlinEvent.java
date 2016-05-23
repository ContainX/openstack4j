package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.senlin.Event;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * This is a model of a senlinEvent. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("event")
public class SenlinEvent implements Event {
	private static final long serialVersionUID = -8305526522823061209L;

	@JsonProperty("action")
	private String action;
	@JsonProperty("cluster_id")
	private String cluster_id;
	@JsonProperty("id")
	private String id;
	@JsonProperty("level")
	private String level;
	@JsonProperty("obj_id")
	private String obj_id;
	@JsonProperty("obj_name")
	private String obj_name;
	@JsonProperty("obj_type")
	private String obj_type;
	@JsonProperty("project")
	private String project;
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_reason")
	private String status_reason;
	@JsonProperty("timestamp")
	private String timestamp;
	@JsonProperty("user")
	private String user;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "SenlinEvent{" +
				"action='" + action + '\'' +
				", cluster_id='" + cluster_id + '\'' +
				", id='" + id + '\'' +
				", level='" + level + '\'' +
				", obj_id='" + obj_id + '\'' +
				", obj_name='" + obj_name + '\'' +
				", obj_type='" + obj_type + '\'' +
				", project='" + project + '\'' +
				", status='" + status + '\'' +
				", status_reason='" + status_reason + '\'' +
				", timestamp='" + timestamp + '\'' +
				", user='" + user + '\'' +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinEvent
	 * 
	 * @author lion
	 * 
	 */
	public static class Event extends ListResult<SenlinEvent> {
		private static final long serialVersionUID = 7647975201092736501L;

		@JsonProperty("events")
		private List<SenlinEvent> list;

		protected List<SenlinEvent> value() {
			return list;
		}
	}
}
