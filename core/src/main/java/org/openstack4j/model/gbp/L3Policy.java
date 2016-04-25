package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.L3PolicyBuilder;

public interface L3Policy extends Resource, Buildable<L3PolicyBuilder> {

    String getDescription();

    String getExternalSegments();

    String getIpPool();

    String getIpVersion();

    List<L2Policy> getL2Policies();

    List<String> getRouters();

    boolean isShared();

    String getSubnetPrefixLength();

}
