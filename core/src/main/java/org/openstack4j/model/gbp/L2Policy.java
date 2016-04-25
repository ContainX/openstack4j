package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.L2PolicyBuilder;

public interface L2Policy extends Resource, Buildable<L2PolicyBuilder> {

    String getDescription();

    String getNetworkId();

    String getL3PolicyId();

    boolean isShared();

    List<PolicyTargetGroup> getPolicyTargetGroups();

}
