package org.openstack4j.model.trove.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.trove.DatabaseCreate;
import org.openstack4j.model.trove.Flavor;
import org.openstack4j.model.trove.InstanceCreate;

/**
 * Created by cp16net on 2/14/16.
 */
public interface InstanceCreateBuilder extends Buildable.Builder<InstanceCreateBuilder, InstanceCreate> {
    InstanceCreateBuilder name(String name);
    InstanceCreateBuilder flavor(String flavorId);
    InstanceCreateBuilder flavor(Flavor flavor);
    InstanceCreateBuilder size(Integer size);
    InstanceCreateBuilder databases(DatabaseCreate databases);

}
