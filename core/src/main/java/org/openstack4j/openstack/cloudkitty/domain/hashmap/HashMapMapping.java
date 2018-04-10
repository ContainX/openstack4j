package org.openstack4j.openstack.cloudkitty.domain.hashmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.builder.hashmap.MappingBuilder;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class HashMapMapping implements Mapping {
    @JsonProperty("mapping_id")
    private String id;
    private float cost;
    @JsonProperty("field_id")
    private String fieldId;
    @JsonProperty("tenant_id")
    private String tenantId;
    private Type type;
    private String value;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public String getFieldId() {
        return fieldId;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public MappingBuilder toBuilder() {
        return new HashMapMappingConcreteBuilder(this);
    }

    public static MappingBuilder builder() {
        return new HashMapMappingConcreteBuilder();
    }

    public static class HashMapMappingConcreteBuilder implements MappingBuilder {
        private HashMapMapping model;

        public HashMapMappingConcreteBuilder(HashMapMapping model) {
            this.model = model;
        }

        public HashMapMappingConcreteBuilder() {
            this(new HashMapMapping());
        }

        @Override
        public MappingBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public MappingBuilder cost(float cost) {
            model.cost = cost;
            return this;
        }

        @Override
        public MappingBuilder fieldId(String fieldId) {
            model.fieldId = fieldId;
            return this;
        }

        @Override
        public MappingBuilder tenantId(String tenantId) {
            model.tenantId = tenantId;
            return this;
        }

        @Override
        public MappingBuilder type(Type type) {
            model.type = type;
            return this;
        }

        @Override
        public MappingBuilder value(String value) {
            model.value = value;
            return this;
        }

        @Override
        public Mapping build() {
            return model;
        }

        @Override
        public MappingBuilder from(Mapping in) {
            model = (HashMapMapping) in;
            return this;
        }
    }

    public static class Mappings extends ListResult<HashMapMapping> {

        @JsonProperty
        List<HashMapMapping> mappings;

        @Override
        protected List<HashMapMapping> value() {
            return mappings;
        }
    }
}
