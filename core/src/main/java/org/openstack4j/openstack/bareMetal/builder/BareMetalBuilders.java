package org.openstack4j.openstack.bareMetal.builder;

import org.openstack4j.model.bareMetal.builder.*;
import org.openstack4j.openstack.bareMetal.domain.*;

/**
 * The BareMetal Builders v1
 */
public class BareMetalBuilders implements org.openstack4j.model.bareMetal.builder.BareMetalBuilders {

    /**
     * The Builder to create a ChassisBuilder
     * @return the chassis builder
     */
    @Override
    public ChassisBuilder chassis() {
        return BareMetalChassis.builder();
    }

    /**
     * The Builder to create a ChassisUpdateBuilder
     * @return the chassisUpdate builder
     */
    @Override
    public ChassisUpdateBuilder chassisUpdate() {
        return BareMetalChassisUpdate.builder();
    }

    /**
     * The Builder to create a DriverBuilder
     * @return the driver builder
     */
    @Override
    public DriverBuilder driver() {
        return BareMetalDriver.builder();
    }

    /**
     * The Builder to create a NodeBuilder
     * @return the node builder
     */
    @Override
    public NodeBuilder node() {
        return BareMetalNode.builder();
    }

    /**
     * The Builder to create a NodeUpdateBuilder
     * @return the nodeUpdate builder
     */
    @Override
    public NodeUpdateBuilder nodeUpdate() {
        return BareMetalNodeUpdate.builder();
    }

    /**
     * The Builer to create a NodeManangementeBuilder
     * @return the nodeManagement builder
     */
    @Override
    public NodeManagementBuilder nodeManagement() {
        return BareMetalNodeManagement.builder();
    }

    /**
     * The Builder to create a PortBuilder
     * @return the port builder
     */
    @Override
    public PortBuilder port() {
        return BareMetalPort.builder();
    }

    /**
     * The Builder to create a PortUpdateBuilder
     * @return the portUpdate builder
     */
    @Override
    public PortUpdateBuilder portUpdate() {
        return BareMetalPortUpdate.builder();
    }
}
