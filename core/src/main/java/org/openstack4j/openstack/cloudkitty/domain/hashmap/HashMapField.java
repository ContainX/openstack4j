package org.openstack4j.openstack.cloudkitty.domain.hashmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.builder.hashmap.FieldBuilder;
import org.openstack4j.model.cloudkitty.hashmap.Field;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class HashMapField implements Field {

    @JsonProperty("field_id")
    private String id;
    private String name;
    @JsonProperty("service_id")
    private String serviceId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public FieldBuilder toBuilder() {
        return new HashMapFieldConcreteBuilder(this);
    }

    public static FieldBuilder builder() {
        return new HashMapFieldConcreteBuilder();
    }

    public static class HashMapFieldConcreteBuilder implements FieldBuilder {
        HashMapField model;

        public HashMapFieldConcreteBuilder(HashMapField model) {
            this.model = model;
        }

        public HashMapFieldConcreteBuilder() {
            this(new HashMapField());
        }

        @Override
        public FieldBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public FieldBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public FieldBuilder serviceId(String serviceId) {
            model.serviceId = serviceId;
            return this;
        }

        @Override
        public Field build() {
            return model;
        }

        @Override
        public FieldBuilder from(Field in) {
            this.model = (HashMapField) in;
            return this;
        }
    }

    public static class Fields extends ListResult<HashMapField> {

        @JsonProperty
        List<HashMapField> fields;

        @Override
        protected List<HashMapField> value() {
            return fields;
        }
    }
}
