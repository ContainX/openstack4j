package org.openstack4j.model.cloudkitty.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.hashmap.GroupBuilder;

public interface Group extends ModelEntity, Buildable<GroupBuilder> {

    String getId();

    String getName();
}
