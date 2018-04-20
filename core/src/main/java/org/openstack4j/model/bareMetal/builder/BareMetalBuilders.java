package org.openstack4j.model.bareMetal.builder;

/**
 * The Bare Metal Builders
 *
 * @author zhangliang
 */
public interface BareMetalBuilders {

    /**
     * The Builder to create a ChassisBuilder
     * @return the chassis builder
     */
    ChassisBuilder chassis();

    /**
     * The Builder to create a ChassisUpdateBuilder
     * @return the chassisUpdate builder
     */
    ChassisUpdateBuilder chassisUpdate();

    /**
     * The Builder to create a DriverBuilder
     * @return the driver builder
     */
    DriverBuilder driver();

    /**
     * The Builder to create a NodeBuilder
     * @return the node builder
     */
    NodeBuilder node();

    /**
     * The Builder to create a NodeUpdateBuilder
     * @return the nodeUpdate builder
     */
    NodeUpdateBuilder nodeUpdate();

    /**
     * The Builer to create a NodeManangementeBuilder
     * @return the nodeManagement builder
     */
    NodeManagementBuilder nodeManagement();

    /**
     * The Builder to create a PortBuilder
     * @return the port builder
     */
    PortBuilder port();

    /**
     * The Builder to create a PortUpdateBuilder
     * @return the portUpdate builder
     */
    PortUpdateBuilder portUpdate();

}
