package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.ServiceInfo;
import org.openstack4j.model.cloudkitty.builder.ServiceInfoBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * A cloudkitty service info object
 * @author mariusleu
 */
public class CloudkittyServiceInfo implements ServiceInfo {

    private List<String> metadata;
    @JsonProperty("service_id")
    private String serviceId;
    private String unit;

    @Override
    public List<String> getMetadata() {
        return metadata;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public ServiceInfoBuilder toBuilder() {
        return new CloudkittyServiceInfoConcreteBuilder(this);
    }

    public static CloudkittyServiceInfoConcreteBuilder builder() {
        return new CloudkittyServiceInfoConcreteBuilder();
    }

    public static class CloudkittyServiceInfoConcreteBuilder implements ServiceInfoBuilder {

        private CloudkittyServiceInfo model;

        public CloudkittyServiceInfoConcreteBuilder(CloudkittyServiceInfo model) {
            this.model = model;
        }

        public CloudkittyServiceInfoConcreteBuilder() {
            this(new CloudkittyServiceInfo());
        }

        @Override
        public ServiceInfoBuilder metadata(List<String> metadata) {
            model.metadata = metadata;
            return this;
        }

        @Override
        public ServiceInfoBuilder serviceId(String serviceId) {
            model.serviceId  = serviceId;
            return this;
        }

        @Override
        public ServiceInfoBuilder unit(String unit) {
            model.unit = unit;
            return this;
        }

        @Override
        public ServiceInfo build() {
            return model;
        }

        @Override
        public ServiceInfoBuilder from(ServiceInfo in) {
            model = (CloudkittyServiceInfo) in;
            return this;
        }
    }

    public static class ServiceInfos extends ListResult<CloudkittyServiceInfo> {

        @JsonProperty
        List<CloudkittyServiceInfo> services;

        @Override
        protected List<CloudkittyServiceInfo> value() {
            return services;
        }
    }
}
