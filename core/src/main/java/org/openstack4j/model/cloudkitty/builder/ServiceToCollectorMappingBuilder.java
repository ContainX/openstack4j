package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.ServiceToCollectorMapping;

public interface ServiceToCollectorMappingBuilder extends Buildable.Builder<ServiceToCollectorMappingBuilder, ServiceToCollectorMapping> {

    ServiceToCollectorMappingBuilder collector(String collector);

    ServiceToCollectorMappingBuilder service(String service);
}
