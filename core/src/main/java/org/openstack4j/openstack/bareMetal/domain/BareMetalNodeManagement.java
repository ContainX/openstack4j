package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.bareMetal.NodeManagement;
import org.openstack4j.model.bareMetal.builder.NodeManagementBuilder;

/**
 * bare metal node management
 *
 * @author zhangliang
 */
public class BareMetalNodeManagement implements NodeManagement {

    @JsonProperty("target")
    private String target;

    @Override
    public NodeManagementBuilder toBuilder() {
        return new NodeManagementConcreteBuilder(this);
    }

    public static NodeManagementBuilder builder() {
        return new NodeManagementConcreteBuilder();
    }

    public static class NodeManagementConcreteBuilder implements NodeManagementBuilder {

        private BareMetalNodeManagement m;

        public NodeManagementConcreteBuilder() {
            this(new BareMetalNodeManagement());
        }

        public NodeManagementConcreteBuilder(BareMetalNodeManagement m) {
            this.m = m;
        }

        @Override
        public NodeManagementBuilder target(String target) {
            m.target = target;
            return this;
        }

        @Override
        public NodeManagement build() {
            return m;
        }

        @Override
        public NodeManagementBuilder from(NodeManagement in) {
            m = (BareMetalNodeManagement) in;
            return this;
        }
    }

}
