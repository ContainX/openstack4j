package org.openstack4j.model.cloudkitty.builder.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.hashmap.Service;

public interface ServiceBuilder extends Buildable.Builder<ServiceBuilder, Service> {

    ServiceBuilder id(String id);

    ServiceBuilder name(String name);

}
