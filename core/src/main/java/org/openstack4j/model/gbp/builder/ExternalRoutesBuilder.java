package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.ExternalRoutes;

public interface ExternalRoutesBuilder extends Builder<ExternalRoutesBuilder,ExternalRoutes>{
    ExternalRoutesBuilder destination(String destination);
    ExternalRoutesBuilder nextHop(String nextHop);
}
