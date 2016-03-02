package org.openstack4j.model.trove;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.trove.builder.InstanceCreateBuilder;

/**
 * Created by cp16net on 2/14/16.
 */
public interface InstanceCreate extends ModelEntity, Buildable<InstanceCreateBuilder> {

    String getName();
    String getFlavorRef();

}
