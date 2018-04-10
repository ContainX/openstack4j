package org.openstack4j.model.cloudkitty.builder.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;

public interface MappingBuilder extends Buildable.Builder<MappingBuilder, Mapping> {

    MappingBuilder id(String id);

    MappingBuilder cost(float cost);

    MappingBuilder fieldId(String fieldId);

    MappingBuilder tenantId(String tenantId);

    MappingBuilder type(Mapping.Type type);

    MappingBuilder value(String value);
}
