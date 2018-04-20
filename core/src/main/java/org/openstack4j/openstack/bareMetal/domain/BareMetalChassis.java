package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.openstack4j.model.bareMetal.Chassis;
import org.openstack4j.model.bareMetal.builder.ChassisBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;
import java.util.Map;

/**
 * Bare Metal Chassis
 *
 * @author zhangliang
 */
public class BareMetalChassis implements Chassis {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("description")
    private String description;
    @JsonProperty("extra")
    private Map<String, String> extra;

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Map<String, String> getExtra() {
        return extra;
    }

    public static ChassisBuilder builder() {
        return new ChassisConcreteBuilder();
    }

    @Override
    public ChassisBuilder toBuilder() {
        return new ChassisConcreteBuilder(this);
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public void setTenantId(String tenantId) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    public static class Chassises extends ListResult<BareMetalChassis> {

        @JsonProperty("chassis")
        private List<BareMetalChassis> chassises;

        @Override
        protected List<BareMetalChassis> value() {
            return chassises;
        }

    }

    public static class ChassisConcreteBuilder implements ChassisBuilder {

        private BareMetalChassis m;

        public ChassisConcreteBuilder() {
            this(new BareMetalChassis());
        }

        public ChassisConcreteBuilder(BareMetalChassis m) {
            this.m = m;
        }

        @Override
        public ChassisBuilder description(String description) {
            m.description = description;
            return this;
        }

        @Override
        public ChassisBuilder extra(Map<String, String> extra) {
            m.extra = extra;
            return this;
        }

        @Override
        public ChassisBuilder addExtraItem(String key, String value) {
            if (m.extra == null) {
                m.extra = Maps.newHashMap();
            }
            m.extra.put(key, value);
            return this;
        }

        @Override
        public Chassis build() {
            return m;
        }

        @Override
        public ChassisBuilder from(Chassis in) {
            m = (BareMetalChassis) in;
            return this;
        }
    }
}
