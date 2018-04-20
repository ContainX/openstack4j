package org.openstack4j.model.bareMetal;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.builder.ChassisBuilder;
import org.openstack4j.model.common.Resource;

import java.util.Map;

/**
 * Bare Metal Chassis
 *
 * @author zhangliang
 */
public interface Chassis extends Resource, Buildable<ChassisBuilder> {

    /**
     * Return the chassis uuid
     * @return
     */
    String getUuid();

    /**
     * 	Descriptive text about the Ironic service.
     * @return
     */
    String getDescription();

    /**
     * A set of one or more arbitrary metadata key and value pairs.
     * @return
     */
    Map<String, String> getExtra();

}
