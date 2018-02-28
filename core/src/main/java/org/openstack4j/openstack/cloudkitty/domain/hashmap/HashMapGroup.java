package org.openstack4j.openstack.cloudkitty.domain.hashmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.model.cloudkitty.builder.hashmap.GroupBuilder;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class HashMapGroup implements Group {

    @JsonProperty("group_id")
    private String id;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GroupBuilder toBuilder() {
        return new HashMapGroupConcreteBuilder(this);
    }

    public static GroupBuilder builder() {
        return new HashMapGroupConcreteBuilder();
    }

    public static class HashMapGroupConcreteBuilder implements GroupBuilder {
        HashMapGroup model;

        public HashMapGroupConcreteBuilder(HashMapGroup model) {
            this.model = model;
        }

        public HashMapGroupConcreteBuilder() {
            this(new HashMapGroup());
        }

        @Override
        public GroupBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public GroupBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public Group build() {
            return model;
        }

        @Override
        public GroupBuilder from(Group in) {
            model = (HashMapGroup) in;
            return this;
        }
    }

    public static class Groups extends ListResult<HashMapGroup> {

        @JsonProperty
        List<HashMapGroup> groups;

        @Override
        protected List<HashMapGroup> value() {
            return groups;
        }
    }
}
