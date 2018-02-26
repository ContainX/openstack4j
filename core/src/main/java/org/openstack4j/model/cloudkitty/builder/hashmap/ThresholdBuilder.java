package org.openstack4j.model.cloudkitty.builder.hashmap;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.cloudkitty.hashmap.Threshold;

public interface ThresholdBuilder extends Buildable.Builder<ThresholdBuilder, Threshold> {

    ThresholdBuilder id(String id);

    ThresholdBuilder cost(float cost);

    ThresholdBuilder fieldId(String fieldId);

    ThresholdBuilder tenantId(String tenantId);

    ThresholdBuilder type(Mapping.Type type);
}
