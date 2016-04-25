package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.ExternalSegmentBuilder;
import org.openstack4j.openstack.gbp.domain.GbpExternalPolicy;
import org.openstack4j.openstack.gbp.domain.GbpExternalRoutes;

public interface ExternalSegment extends Resource, Buildable<ExternalSegmentBuilder> {

    List<GbpExternalPolicy> getExternalPolicies();

    String getIpVersion();

    String getCidr();

    String getDescription();

    boolean isShared();

    String getSubnetId();

    List<L3Policy> getL3Policies();

    boolean isPortAddressTranslation();

    GbpExternalRoutes getExternalRoutes();

}
 