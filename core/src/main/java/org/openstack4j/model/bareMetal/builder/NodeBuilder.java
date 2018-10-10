package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.bareMetal.Node;

import java.util.Map;

/**
 * A Builder which creates a Bare Metal Node
 *
 * @author zhangliang
 */
public interface NodeBuilder extends Builder<NodeBuilder, Node> {

    /**
     * set driver
     * @param driver
     * @return
     */
    NodeBuilder driver(String driver);

    /**
     * set chassisUuid
     * @param chassisUuid
     * @return
     */
    NodeBuilder chassisUuid(String chassisUuid);

    /**
     * set extra
     * @param extra
     * @return
     */
    NodeBuilder extra(Map<String, String> extra);

    /**
     * add key and value to extraItem
     * @param key
     * @param value
     * @return
     */
    NodeBuilder addExtraItem(String key, String value);

    /**
     * set properties
     * @param properties
     * @return
     */
    NodeBuilder properties(Map<String, Object> properties);

    /**
     * add key and value to properties
     * @param key
     * @param value
     * @return
     */
    NodeBuilder addProperties(String key, Object value);

    /**
     * set driverInfo
     * @param driverInfo
     * @return
     */
    NodeBuilder driverInfo(Map<String, Object> driverInfo);

    /**
     * add key and value to driverInfo
     * @param key
     * @param value
     * @return
     */
    NodeBuilder addDriverInfo(String key, Object value);

}
