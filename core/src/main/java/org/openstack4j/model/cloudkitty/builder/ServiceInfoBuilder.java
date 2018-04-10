package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.ServiceInfo;

import java.util.List;

/**
 * A cloudkitty service info builder
 */
public interface ServiceInfoBuilder extends Buildable.Builder<ServiceInfoBuilder, ServiceInfo> {

    ServiceInfoBuilder metadata(List<String> metadata);

    ServiceInfoBuilder serviceId(String serviceId);

    ServiceInfoBuilder unit(String unit);
}
