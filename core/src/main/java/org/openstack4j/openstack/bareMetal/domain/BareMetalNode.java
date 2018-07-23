package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.openstack4j.model.bareMetal.Node;
import org.openstack4j.model.bareMetal.builder.NodeBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Bare Metal Node
 *
 * @author zhangliang
 */
public class BareMetalNode implements Node {

    @JsonProperty("driver")
    private String driver;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("chassis_uuid")
    private String chassisUuid;

    @JsonProperty("instance_uuid")
    private String instanceUuid;

    @JsonProperty("power_state")
    private String powerState;

    @JsonProperty("target_power_state")
    private String targetPowerState;

    @JsonProperty("provision_state")
    private String provisionState;

    @JsonProperty("target_provision_state")
    private String targetProvisionState;

    @JsonProperty("maintenance")
    private Boolean maintenance;

    @JsonProperty("maintenance_reason")
    private String maintenanceReason;

    @JsonProperty("reservation")
    private String reservation;

    @JsonProperty("extra")
    private Map<String, String> extra;

    @JsonProperty("properties")
    private Map<String, Object> properties;

    @JsonProperty("driver_info")
    private Map<String, Object> driverInfo;

    @JsonProperty("driver_internal_info")
    private Map<String, Object> driverInternalInfo;

    @JsonProperty("raid_config")
    private Map<String, Object> raidConfig;

    @JsonProperty("target_raid_config")
    private Map<String, Object> targetRaidConfig;

    @JsonProperty("instance_info")
    private Map<String, Object> instanceInfo;

    @JsonProperty("last_error")
    private String lastError;

    @JsonProperty("provision_updated_at")
    private Date provisionUpdatedAt;

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getChassisUuid() {
        return chassisUuid;
    }

    @Override
    public String getInstanceUuid() {
        return instanceUuid;
    }

    @Override
    public String getPowerState() {
        return powerState;
    }

    @Override
    public String getTargetPowerState() {
        return targetPowerState;
    }

    @Override
    public String getProvisionState() {
        return provisionState;
    }

    @Override
    public String getTargetProvisionState() {
        return targetProvisionState;
    }

    @Override
    public Boolean getMaintenance() {
        return maintenance;
    }

    @Override
    public String getMaintenanceReason() {
        return getMaintenanceReason();
    }

    @Override
    public String getReservation() {
        return getReservation();
    }

    @Override
    public Map<String, String> getExtra() {
        return extra;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public Map<String, Object> getDriverInfo() {
        return driverInfo;
    }

    @Override
    public Map<String, Object> getDriverInternalInfo() {
        return driverInternalInfo;
    }

    @Override
    public Map<String, Object> getRaidConfig() {
        return raidConfig;
    }

    @Override
    public Map<String, Object> getTargetRaidConfig() {
        return targetRaidConfig;
    }

    @Override
    public Map<String, Object> getInstanceInfo() {
        return instanceInfo;
    }

    @Override
    public String getLastError() {
        return lastError;
    }

    @Override
    public Date getProvisionUpdateAt() {
        return provisionUpdatedAt;
    }

    public static NodeBuilder builder(){
        return new NodeConcreteBuilder();
    }

    @Override
    public NodeBuilder toBuilder() {
        return new NodeConcreteBuilder(this);
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

    public static class Nodes extends ListResult<BareMetalNode> {

        @JsonProperty("nodes")
        private List<BareMetalNode> nodes;

        @Override
        protected List<BareMetalNode> value() {
            return nodes;
        }
    }

    public static class NodeConcreteBuilder implements NodeBuilder {

        private BareMetalNode m;

        public NodeConcreteBuilder(){
            this(new BareMetalNode());
        }

        public NodeConcreteBuilder(BareMetalNode m){
            this.m = m;
        }

        @Override
        public Node build() {
            return m;
        }

        @Override
        public NodeBuilder from(Node in) {
            m = (BareMetalNode) in;
            return this;
        }

        @Override
        public NodeBuilder driver(String driver) {
            m.driver = driver;
            return this;
        }

        @Override
        public NodeBuilder chassisUuid(String chassisUuid) {
            m.chassisUuid = chassisUuid;
            return this;
        }

        @Override
        public NodeBuilder extra(Map<String, String> extra) {
            m.extra = extra;
            return this;
        }

        @Override
        public NodeBuilder addExtraItem(String key, String value) {
            if (m.extra == null) {
                m.extra = Maps.newHashMap();
            }
            m.extra.put(key, value);
            return this;
        }

        @Override
        public NodeBuilder properties(Map<String, Object> properties) {
            m.properties = properties;
            return this;
        }

        @Override
        public NodeBuilder addProperties(String key, Object value) {
            if(m.properties == null) {
                m.properties = Maps.newHashMap();
            }
            m.properties.put(key, value);
            return this;
        }

        @Override
        public NodeBuilder driverInfo(Map<String, Object> driverInfo) {
            m.driverInfo = driverInfo;
            return this;
        }

        @Override
        public NodeBuilder addDriverInfo(String key, Object value) {
            if (m.driverInfo == null) {
                m.driverInfo = Maps.newHashMap();
            }
            m.driverInfo.put(key, value);
            return this;
        }
    }
}