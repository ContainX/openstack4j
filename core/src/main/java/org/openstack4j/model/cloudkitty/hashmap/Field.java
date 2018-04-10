package org.openstack4j.model.cloudkitty.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.hashmap.FieldBuilder;

public interface Field extends ModelEntity, Buildable<FieldBuilder> {

    String getId();

    String getName();

    String getServiceId();
}
