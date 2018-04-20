package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.bareMetal.Driver;
import org.openstack4j.model.bareMetal.builder.DriverBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Bare Metal Driver
 *
 * @author zhangliang
 */
public class BareMetalDriver implements Driver {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("hosts")
    private List<String> hosts;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<String> getHosts() {
        return hosts;
    }

    public static DriverBuilder builder(){
        return new DriverConcreteBuilder();
    }

    @Override
    public DriverBuilder toBuilder() {
        return new DriverConcreteBuilder(this);
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
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    public static class Drivers extends ListResult<BareMetalDriver> {

        @JsonProperty("drivers")
        private List<BareMetalDriver> drivers;

        @Override
        protected List<BareMetalDriver> value() {
            return drivers;
        }
    }

    public static class DriverConcreteBuilder implements DriverBuilder {

        private BareMetalDriver m;

        public DriverConcreteBuilder(){
            this(new BareMetalDriver());
        }

        public DriverConcreteBuilder(BareMetalDriver m){
            this.m = m;
        }

        @Override
        public Driver build() {
            return m;
        }

        @Override
        public DriverBuilder from(Driver in) {
            m = (BareMetalDriver) in;
            return this;
        }
    }
}
