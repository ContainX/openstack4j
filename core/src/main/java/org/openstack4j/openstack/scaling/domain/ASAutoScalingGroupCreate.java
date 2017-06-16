package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingGroupCreate;
import org.openstack4j.openstack.common.IdResourceEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingGroupCreate implements ScalingGroupCreate {

	private static final long serialVersionUID = -8059070824334840043L;
	
	@JsonProperty("scaling_group_id")
	String groupId;

	@JsonProperty("scaling_group_name")
	String groupName;

	@JsonProperty("scaling_configuration_id")
	String configId;

	@JsonProperty("desire_instance_number")
	Integer desireInstanceNumber;

	@JsonProperty("min_instance_number")
	Integer minInstanceNumber;

	@JsonProperty("max_instance_number")
	Integer maxInstanceNumber;

	@JsonProperty("cool_down_time")
	Integer coolDownTime;

	@JsonProperty("lb_listener_id")
	String lbListenerId;

	@JsonProperty("availability_zones")
	List<String> availabilityZones;

	@JsonProperty("networks")
	List<IdResourceEntity> networks;

	@JsonProperty("security_groups")
	List<IdResourceEntity> securityGroups;

	@JsonProperty("vpc_id")
	String vpcId;

	@JsonProperty("health_periodic_audit_method")
	String healthPeriodicAuditMethod;

	@JsonProperty("health_periodic_audit_time")
	Integer healthPeriodicAuditTime;

	@JsonProperty("instance_terminate_policy")
	String instanceTerminatePolicy;

	@JsonProperty("notifications")
	List<String> notifications;

	@JsonProperty("delete_publicip")
	Boolean deletePublicip;

	@Override
	public String groupId() {
		return groupId;
	}

	@Override
	public String groupName() {
		return groupName;
	}

	@Override
	public String configId() {
		return configId;
	}

	@Override
	public Integer desireInstanceNumber() {
		return desireInstanceNumber;
	}

	@Override
	public Integer minInstanceNumber() {
		return minInstanceNumber;
	}

	@Override
	public Integer maxInstanceNumber() {
		return maxInstanceNumber;
	}

	@Override
	public Integer coolDownTime() {
		return coolDownTime;
	}

	@Override
	public String lbListenerId() {
		return lbListenerId;
	}

	@Override
	public List<String> availabilityZones() {
		return availabilityZones;
	}

	@Override
	public List<IdResourceEntity> networks() {
		return networks;
	}

	@Override
	public List<IdResourceEntity> securityGroups() {
		return securityGroups;
	}

	@Override
	public String vpcId() {
		return vpcId;
	}

	@Override
	public String healthPeriodicAuditMethod() {
		return healthPeriodicAuditMethod;
	}

	@Override
	public Integer healthPeriodicAuditTime() {
		return healthPeriodicAuditTime;
	}

	@Override
	public String instanceTerminatePolicy() {
		return instanceTerminatePolicy;
	}

	@Override
	public List<String> notifications() {
		return notifications;
	}

	@Override
	public Boolean deletePublicip() {
		return deletePublicip;
	}
}
