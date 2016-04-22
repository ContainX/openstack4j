package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.NatPool;
import org.openstack4j.model.gbp.builder.NatPoolBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("nat_pool")
public class GbpNatPool implements NatPool {
    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    private boolean shared;
    @JsonProperty("external_segment_id")
    private String externalSegmentId;
    @JsonProperty("ip_pool")
    private String ipPool;
    @JsonProperty("ip_version")
    private String ipVersion;
    @JsonProperty("subnet_id")
    private String subnetId;
    
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

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getExternalSegmentId() {
        return externalSegmentId;
    }

    public void setExternalSegmentId(String externalSegmentId) {
        this.externalSegmentId = externalSegmentId;
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

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    @Override
    public NatPoolBuilder toBuilder() {
        return new NatPoolConcreteBuilder(this);
    }
    
    public static class NatPools extends ListResult<GbpNatPool>{

        @JsonProperty("nat_pools")
        private List<GbpNatPool> natpools;
        
        @Override
        protected List<GbpNatPool> value() {
            return natpools;
        }
        
    }
    
    public static class NatPoolConcreteBuilder implements NatPoolBuilder{

        private GbpNatPool natPool;
        
        public NatPoolConcreteBuilder(GbpNatPool gbpNatPool) {
           this.natPool=gbpNatPool;
        }

        @Override
        public NatPool build() {
            return natPool;
        }

        @Override
        public NatPoolBuilder from(NatPool in) {
            this.natPool=(GbpNatPool) in;
            return this;
        }
        
    }

}
