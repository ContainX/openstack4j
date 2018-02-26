package org.openstack4j.model.cloudkitty.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.hashmap.ServiceBuilder;

public interface Service extends ModelEntity, Buildable<ServiceBuilder> {

    String getId();

    String getName();
}
