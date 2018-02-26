package org.openstack4j.model.cloudkitty.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.hashmap.MappingBuilder;

public interface Mapping extends ModelEntity, Buildable<MappingBuilder> {

    enum Type {
        FLAT,
        RATE
    }

    String getId();

    float getCost();

    String getFieldId();

    String getTenantId();

    Type getType();

    String getValue();
}
