package org.openstack4j.openstack.compute.domain;

import java.util.HashMap;
import java.util.Map;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.compute.ServerCreate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

/**
 * an entity for build a sevrer with sheduler hints
 * @author octopus zhang
 */
public class NovaServerCreateWithHints implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty("os:scheduler_hints")
	private Map<String, String> schedulerHints = new HashMap<String, String>();
	private ServerCreate server;

	public Map<String, String> getSchedulerHints() {
		return schedulerHints;
	}

	public ServerCreate getServer() {
		return server;
	}

	public NovaServerCreateWithHints addSchedulerHint(String key, String value) {
		if (schedulerHints == null)
			schedulerHints = Maps.newHashMap();

		schedulerHints.put(key, value);
		return this;
	}

	public NovaServerCreateWithHints(ServerCreate server2,
			Map<String, String> hints) {
		this.server = server2;
		this.schedulerHints = hints;
	}

}
