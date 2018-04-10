package org.openstack4j.openstack.cloudkitty.domain.hashmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.builder.hashmap.ThresholdBuilder;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.cloudkitty.hashmap.Threshold;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class HashMapThreshold implements Threshold {
    @JsonProperty("threshold_id")
    private String id;
    private float cost;
    private float level;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("field_id")
    private String fieldId;
    private Mapping.Type type;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public Mapping.Type getType() {
        return type;
    }

    @Override
    public String getFieldId() {
        return fieldId;
    }

    @Override
    public ThresholdBuilder toBuilder() {
        return new HashMapThresholdConcreteBuilder(this);
    }

    public static ThresholdBuilder builder() {
        return new HashMapThresholdConcreteBuilder();
    }

    public static class HashMapThresholdConcreteBuilder implements ThresholdBuilder {

        HashMapThreshold model;

        public HashMapThresholdConcreteBuilder(HashMapThreshold model) {
            this.model = model;
        }

        public HashMapThresholdConcreteBuilder() {
            this(new HashMapThreshold());
        }

        @Override
        public ThresholdBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public ThresholdBuilder cost(float cost) {
            model.cost = cost;
            return this;
        }

        @Override
        public ThresholdBuilder fieldId(String fieldId) {
            model.fieldId = fieldId;
            return this;
        }

        @Override
        public ThresholdBuilder level(float level) {
            model.level = level;
            return this;
        }

        @Override
        public ThresholdBuilder tenantId(String tenantId) {
            model.tenantId = tenantId;
            return this;
        }

        @Override
        public ThresholdBuilder type(Mapping.Type type) {
            model.type = type;
            return this;
        }

        @Override
        public Threshold build() {
            return model;
        }

        @Override
        public ThresholdBuilder from(Threshold in) {
            model = (HashMapThreshold) in;
            return this;
        }
    }

    public static class Thresholds extends ListResult<HashMapThreshold> {

        @JsonProperty
        List<HashMapThreshold> thresholds;

        @Override
        protected List<HashMapThreshold> value() {
            return thresholds;
        }
    }
}
