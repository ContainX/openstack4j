package org.openstack4j.model.cloudkitty.builder.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.hashmap.Field;

public interface FieldBuilder extends Buildable.Builder<FieldBuilder, Field> {

    FieldBuilder id(String id);

    FieldBuilder name(String name);

    FieldBuilder serviceId(String serviceId);
}
