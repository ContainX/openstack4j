package org.openstack4j.model.cloudkitty.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.hashmap.ThresholdBuilder;

public interface Threshold extends ModelEntity, Buildable<ThresholdBuilder> {

    String getId();

    float getCost();

    float getLevel();

    String getTenantId();

    Mapping.Type getType();
}
