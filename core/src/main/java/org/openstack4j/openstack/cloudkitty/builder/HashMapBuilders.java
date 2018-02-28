package org.openstack4j.openstack.cloudkitty.builder;

import org.openstack4j.model.cloudkitty.builder.hashmap.FieldBuilder;
import org.openstack4j.model.cloudkitty.builder.hashmap.GroupBuilder;
import org.openstack4j.model.cloudkitty.builder.hashmap.MappingBuilder;
import org.openstack4j.model.cloudkitty.builder.hashmap.ServiceBuilder;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapField;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapGroup;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapMapping;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapService;

public class HashMapBuilders {

    public FieldBuilder field() {
        return HashMapField.builder();
    }

    public GroupBuilder group() {
        return HashMapGroup.builder();
    }

    public ServiceBuilder service() {
        return HashMapService.builder();
    }

    public MappingBuilder mapping() {
        return HashMapMapping.builder();
    }
}
