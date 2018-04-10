package org.openstack4j.model.cloudkitty.hashmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.hashmap.MappingBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;


public interface Mapping extends ModelEntity, Buildable<MappingBuilder> {

    enum Type {
        FLAT,
        RATE;

        @JsonValue
        public String value() {
            return name().toUpperCase();
        }

        //default to PRIMARY
        @JsonCreator
        public static Type value(String v)
        {
            if (v == null) return FLAT;
            try {
                return valueOf(v.toUpperCase());
            } catch (IllegalArgumentException e) {
                return FLAT;
            }
        }
    }

    public static class Types extends ListResult<Type> {
        List<Type> types;

        @Override
        @JsonValue
        public List<Type> value() {
            return types;
        }

        @JsonCreator
        public static Types valueOf(List<Type> types) {
            Types list = new Types();
            list.types = types;
            return list;
        }
    }

    String getId();

    float getCost();

    String getFieldId();

    String getTenantId();

    Type getType();

    String getValue();
}
