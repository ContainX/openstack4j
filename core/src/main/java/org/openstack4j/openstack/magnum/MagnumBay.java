package org.openstack4j.openstack.magnum;

import java.util.List;

import org.openstack4j.model.magnum.Bay;
import org.openstack4j.model.magnum.BayBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class MagnumBay implements Bay {
    private static final long serialVersionUID = 1L;
    @JsonProperty("status")
    private String status;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("links")
    private List<GenericLink> links;
    @JsonProperty("stack_id")
    private String stackId;
    @JsonProperty("master_count")
    private Integer masterCount;
    @JsonProperty("baymodel_id")
    private String baymodelId;
    @JsonProperty("node_count")
    private Integer nodeCount;
    @JsonProperty("bay_create_timeout")
    private String bayCreateTimeout;
    @JsonProperty("name")
    private String name;

    public static BayBuilder builder() {
        return new BayConcreteBuilder();
    }

    @Override
    public BayBuilder toBuilder() {
        return new BayConcreteBuilder(this);
    }

    public String getStatus() {
        return status;
    }

    public String getUuid() {
        return uuid;
    }

    public List<GenericLink> getLinks() {
        return links;
    }

    public String getStackId() {
        return stackId;
    }

    public Integer getMasterCount() {
        return masterCount;
    }

    public String getBaymodelId() {
        return baymodelId;
    }

    public Integer getNodeCount() {
        return nodeCount;
    }

    public String getBayCreateTimeout() {
        return bayCreateTimeout;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("status", status).add("uuid", uuid).add("links", links)
                .add("stackId", stackId).add("masterCount", masterCount).add("baymodelId", baymodelId)
                .add("nodeCount", nodeCount).add("bayCreateTimeout", bayCreateTimeout).add("name", name).toString();
    }

    /**
     * Concrete builder containing MagnumBay as model
     *
     */
    public static class BayConcreteBuilder implements BayBuilder {
        MagnumBay model;

        public BayConcreteBuilder() {
            this(new MagnumBay());
        }

        public BayConcreteBuilder(MagnumBay model) {
            this.model = model;
        }

        @Override
        public Bay build() {
            return model;
        }

        @Override
        public BayBuilder from(Bay in) {
            if (in != null)
                this.model = (MagnumBay) in;
            return this;
        }

        @Override
        public BayBuilder status(String status) {
            model.status = status;
            return this;
        }

        @Override
        public BayBuilder uuid(String uuid) {
            model.uuid = uuid;
            return this;
        }

        @Override
        public BayBuilder links(List<GenericLink> links) {
            model.links = links;
            return this;
        }

        @Override
        public BayBuilder stackId(String stackId) {
            model.stackId = stackId;
            return this;
        }

        @Override
        public BayBuilder masterCount(Integer masterCount) {
            model.masterCount = masterCount;
            return this;
        }

        @Override
        public BayBuilder baymodelId(String baymodelId) {
            model.baymodelId = baymodelId;
            return this;
        }

        @Override
        public BayBuilder nodeCount(Integer nodeCount) {
            model.nodeCount = nodeCount;
            return this;
        }

        @Override
        public BayBuilder bayCreateTimeout(String bayCreateTimeout) {
            model.bayCreateTimeout = bayCreateTimeout;
            return this;
        }

        @Override
        public BayBuilder name(String name) {
            model.name = name;
            return this;
        }

        public static class Bays extends ListResult<MagnumBay> {
            private static final long serialVersionUID = 1L;
            @JsonProperty("bays")
            private List<MagnumBay> list;

            @Override
            public List<MagnumBay> value() {
                return list;
            }
        }
    }
}
