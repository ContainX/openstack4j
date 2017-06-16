package org.openstack4j.openstack.scaling.domain.action;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public final class ASScalingGroupAction {

	public static class Resume implements ScalingGroupAction {
		@JsonProperty
		private String action = "resume";
	}

	public static class Pause implements ScalingGroupAction {
		@JsonProperty
		private String action = "pause";
	}
}
