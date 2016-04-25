package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.PolicyTargetGroup;

/**
 * This interface defines all methods for the manipulation of groups
 * 
 * @author vinod borole
 * 
 */
public interface GroupService{
    List<? extends PolicyTargetGroup> list();
    List<? extends PolicyTargetGroup> list(Map<String, String> filteringParams);
    PolicyTargetGroup get(String id);
    ActionResponse delete(String id);
    PolicyTargetGroup create(PolicyTargetGroup policyTargetGroup);
    PolicyTargetGroup update(PolicyTargetGroup policyTargetGroup);
}
