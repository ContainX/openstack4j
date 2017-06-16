package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date 2017-06-14 09:04:49
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("scaling_group")
public class ASAutoScalingGroup implements ScalingGroup {

	private static final long serialVersionUID = -1524859815313839858L;

	@JsonProperty("scaling_group_id")
	String groupId;

	@JsonProperty("scaling_group_name")
	String groupName;
	
	@JsonProperty("scaling_group_status")
	String groupStatus;

	@JsonProperty("scaling_configuration_id")
	String configId;
	
	@JsonProperty("scaling_configuration_name")
	String configName;
	
	@JsonProperty("current_instance_number")
	Integer currentInstanceNumber;

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

	@JsonProperty("create_time")
	String createTime;
	
	@JsonProperty("vpc_id")
	String vpcId;
	
	@JsonProperty
	String detail;
	
	@JsonProperty("is_scaling")
	Boolean isScaling;

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

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String groupId() {
		return groupId;
	}

	/*
	 * {@inheritDoc}
	 */
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
	
	@Override
	public String groupStatus() {
		return groupStatus;
	}

	@Override
	public String configName() {
		return configName;
	}

	@Override
	public Integer currentInstanceNumber() {
		return currentInstanceNumber;
	}

	@Override
	public String createTime() {
		return createTime;
	}

	@Override
	public String detail() {
		return detail;
	}

	@Override
	public Boolean isScaling() {
		return isScaling;
	}

	public static class ASAutoScalingGroups extends ListResult<ASAutoScalingGroup> {
		private static final long serialVersionUID = 1L;

		@JsonProperty("scaling_groups")
		private List<ASAutoScalingGroup> scalingGroups;

		@Override
		protected List<ASAutoScalingGroup> value() {
			return scalingGroups;
		}
	}

}
