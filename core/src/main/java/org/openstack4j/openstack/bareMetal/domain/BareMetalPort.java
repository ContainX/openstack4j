package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.openstack4j.model.bareMetal.Port;
import org.openstack4j.model.bareMetal.builder.PortBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;
import java.util.Map;

/**
 * Bare Metal Port
 *
 * @author zhangliang
 */
public class BareMetalPort implements Port {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("address")
    private String address;

    @JsonProperty("node_uuid")
    private String nodeUuid;

    @JsonProperty("portgroup_uuid")
    private String portgroupUuid;

    @JsonProperty("pxe_enable")
    private Boolean pxeEnable;

    @JsonProperty("physical_network")
    private String physicalNetwork;

    @JsonProperty("extra")
    private Map<String, String> extra;

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getNodeUuid() {
        return nodeUuid;
    }

    @Override
    public String getPortgroupUuid() {
        return portgroupUuid;
    }

    @Override
    public Boolean getPxeEnable() {
        return pxeEnable;
    }

    @Override
    public String getPhysicalNetwork() {
        return physicalNetwork;
    }

    @Override
    public Map<String, String> getExtra() {
        return extra;
    }

    public static PortBuilder builder(){
        return new PortConcreteBuilder();
    }

    @Override
    public PortBuilder toBuilder() {
        return new PortConcreteBuilder(this);
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

    public static class Ports extends ListResult<BareMetalPort> {

        @JsonProperty("ports")
        private List<BareMetalPort> ports;

        @Override
        protected List<BareMetalPort> value() {
            return ports;
        }
    }

    public static class PortConcreteBuilder implements PortBuilder {

        private BareMetalPort m;

        public PortConcreteBuilder() {
            this(new BareMetalPort());
        }

        public PortConcreteBuilder(BareMetalPort m) {
            this.m = m;
        }

        @Override
        public Port build() {
            return m;
        }

        @Override
        public PortBuilder from(Port in) {
            m = (BareMetalPort) in;
            return this;
        }

        @Override
        public PortBuilder nodeUuid(String nodeUuid) {
            m.nodeUuid = nodeUuid;
            return this;
        }

        @Override
        public PortBuilder address(String address) {
            m.address = address;
            return this;
        }

        @Override
        public PortBuilder portgroupUuid(String portgroupUuid) {
            m.portgroupUuid = portgroupUuid;
            return this;
        }

        @Override
        public PortBuilder pxeEnable(Boolean pxeEnable) {
            m.pxeEnable = pxeEnable;
            return this;
        }

        @Override
        public PortBuilder physicalNetwork(String networkName) {
            m.physicalNetwork = networkName;
            return this;
        }

        @Override
        public PortBuilder extra(Map<String, String> extra) {
            m.extra = extra;
            return this;
        }

        @Override
        public PortBuilder addExtraItem(String key, String value) {
            if(m.extra == null){
                m.extra = Maps.newHashMap();
            }
            m.extra.put(key, value);
            return this;
        }
    }
}
