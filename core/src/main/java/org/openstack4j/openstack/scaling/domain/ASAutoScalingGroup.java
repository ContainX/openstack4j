package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-14 09:04:49
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingGroup implements ScalingGroup {

	private static final long serialVersionUID = -1524859815313839858L;
	
	@JsonProperty("scaling_group_id")
	String groupId;
	
	@JsonProperty("scaling_group_name")
	String groupName;

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
