package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.bareMetal.Chassis;

import java.util.Map;

/**
 * A Builder which creates a Chassis
 *
 * @author zhangliang
 */
public interface ChassisBuilder extends Builder<ChassisBuilder, Chassis> {

    /**
     * set description
     * @param description
     * @return ChassisBuilder
     */
    ChassisBuilder description(String description);

    /**
     * set extra
     * @param extra
     * @return ChassisBuilder
     */
    ChassisBuilder extra(Map<String, String> extra);

    /**
     * add extraItem
     * @param key
     * @param value
     * @return ChassisBuilder
     */
    ChassisBuilder addExtraItem(String key, String value);

}
