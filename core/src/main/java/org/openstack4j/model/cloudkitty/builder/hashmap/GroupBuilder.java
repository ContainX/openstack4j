package org.openstack4j.model.cloudkitty.builder.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.hashmap.Group;

public interface GroupBuilder extends Buildable.Builder<GroupBuilder, Group> {

    GroupBuilder id(String id);

    GroupBuilder name(String name);
}
