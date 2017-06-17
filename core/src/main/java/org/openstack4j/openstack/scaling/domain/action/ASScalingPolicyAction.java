package org.openstack4j.openstack.scaling.domain.action;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public final class ASScalingPolicyAction {
	public static class Execute implements ScalingPolicyAction {
		@JsonProperty
		private String action = "execute";
	}

	public static class Resume implements ScalingPolicyAction {
		@JsonProperty
		private String action = "resume";
	}
	
	public static class Pause implements ScalingPolicyAction {
		@JsonProperty
		private String action = "pause";
	}
}
