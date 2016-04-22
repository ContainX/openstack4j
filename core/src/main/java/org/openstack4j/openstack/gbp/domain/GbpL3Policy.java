package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.L2Policy;
import org.openstack4j.model.gbp.L3Policy;
import org.openstack4j.model.gbp.builder.L3PolicyBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("l3_policy")
public class GbpL3Policy implements L3Policy {
    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    @JsonProperty("external_segments")
    private String externalSegments;
    @JsonProperty("ip_pool")
    private String ipPool;
    @JsonProperty("ip_version")
    private String ipVersion;
    @JsonProperty("l2_policies")
    private List<L2Policy> l2Policies;
    private List<String> routers;
    private boolean shared;
    @JsonProperty("subnet_prefix_length")
    private String subnetPrefixLength;
    
    
    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId=tenantId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalSegments() {
        return externalSegments;
    }

    public void setExternalSegments(String externalSegments) {
        this.externalSegments = externalSegments;
    }

    public String getIpPool() {
        return ipPool;
    }

    public void setIpPool(String ipPool) {
        this.ipPool = ipPool;
    }

    public String getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(String ipVersion) {
        this.ipVersion = ipVersion;
    }

    public List<L2Policy> getL2Policies() {
        return l2Policies;
    }

    public void setL2Policies(List<L2Policy> l2Policies) {
        this.l2Policies = l2Policies;
    }

    public List<String> getRouters() {
        return routers;
    }

    public void setRouters(List<String> routers) {
        this.routers = routers;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getSubnetPrefixLength() {
        return subnetPrefixLength;
    }

    public void setSubnetPrefixLength(String subnetPrefixLength) {
        this.subnetPrefixLength = subnetPrefixLength;
    }

    @Override
    public L3PolicyBuilder toBuilder() {
        return new L3PolicyConcreteBuilder(this);
    }

    public static class L3Policies extends ListResult<GbpL3Policy>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("l3_policies")
        private List<GbpL3Policy> l3Policies;
        
        @Override
        protected List<GbpL3Policy> value() {
            return l3Policies;
        }
        
    }
    
    public static class L3PolicyConcreteBuilder implements L3PolicyBuilder{

        private GbpL3Policy l3Policy;
        
        public L3PolicyConcreteBuilder(GbpL3Policy gbpL3Policy) {
            this.l3Policy=gbpL3Policy;
        }

        @Override
        public L3Policy build() { 
            return l3Policy;
        }

        @Override
        public L3PolicyBuilder from(L3Policy in) {
            this.l3Policy = (GbpL3Policy) in;
            return this;
        }
        
    }
}
