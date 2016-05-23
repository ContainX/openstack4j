package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.senlin.Action;
import org.openstack4j.openstack.common.ListResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is a model of a senlinAction. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SenlinAction implements Action {
	private static final long serialVersionUID = -1125919503657762374L;

	@JsonProperty("action")
	private String action;
	@JsonProperty("cause")
	private String cause;
	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("depended_by")
	private ArrayList depended_by;
	@JsonProperty("depended_on")
	private ArrayList depended_on;
	@JsonProperty("end_time")
	private String end_time;
	@JsonProperty("id")
	private String id;
	@JsonProperty("inputs")
	private HashMap inputs;
	@JsonProperty("interval")
	private String interval;
	@JsonProperty("name")
	private String name;
	@JsonProperty("outputs")
	private HashMap outputs;
	@JsonProperty("owner")
	private String owner;
	@JsonProperty("start_time")
	private String start_time;
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_reason")
	private String status_reason;
	@JsonProperty("target")
	private String target;
	@JsonProperty("timeout")
	private String timeout;
	@JsonProperty("updated_at")
	private String updated_at;

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
		return "SenlinAction{" +
				"action='" + action + '\'' +
				", cause='" + cause + '\'' +
				", created_at='" + created_at + '\'' +
				", depended_by=" + depended_by +
				", depended_on=" + depended_on +
				", end_time='" + end_time + '\'' +
				", id='" + id + '\'' +
				", inputs=" + inputs +
				", interval='" + interval + '\'' +
				", name='" + name + '\'' +
				", outputs=" + outputs +
				", owner='" + owner + '\'' +
				", start_time='" + start_time + '\'' +
				", status='" + status + '\'' +
				", status_reason='" + status_reason + '\'' +
				", target='" + target + '\'' +
				", timeout='" + timeout + '\'' +
				", updated_at='" + updated_at + '\'' +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinAction
	 * 
	 * @author lion
	 * 
	 */
	public static class Action extends ListResult<SenlinAction> {
		private static final long serialVersionUID = 298037693459165073L;
		@JsonProperty("actions")
		private List<SenlinAction> list;

		protected List<SenlinAction> value() {
			return list;
		}
	}
}
