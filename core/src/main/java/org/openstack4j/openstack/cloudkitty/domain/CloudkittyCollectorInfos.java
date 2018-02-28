package org.openstack4j.openstack.cloudkitty.domain;

import org.openstack4j.model.cloudkitty.CollectorInfos;
import org.openstack4j.model.cloudkitty.builder.CollectorInfosBuilder;

/**
 * A collector info object
 * @author mariusleu
 */
public class CloudkittyCollectorInfos implements CollectorInfos {

    private boolean enabled;
    private String name;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CollectorInfosBuilder toBuilder() {
        return new CloudkittyCollectorInfosConcreteBuilder(this);
    }

    public static CollectorInfosBuilder builder() {
        return new CloudkittyCollectorInfosConcreteBuilder();
    }

    public static class CloudkittyCollectorInfosConcreteBuilder implements CollectorInfosBuilder {

        private CloudkittyCollectorInfos model;

        public CloudkittyCollectorInfosConcreteBuilder(CloudkittyCollectorInfos model) {
            this.model = model;
        }

        public CloudkittyCollectorInfosConcreteBuilder() {
            this(new CloudkittyCollectorInfos());
        }

        @Override
        public CollectorInfosBuilder enabled(boolean enabled) {
            model.enabled = enabled;
            return this;
        }

        @Override
        public CollectorInfosBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public CollectorInfos build() {
            return model;
        }

        @Override
        public CollectorInfosBuilder from(CollectorInfos in) {
            model = (CloudkittyCollectorInfos) in;
            return this;
        }
    }
}
