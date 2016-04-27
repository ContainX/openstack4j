package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.NatPool;
import org.openstack4j.model.gbp.builder.NatPoolBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
/**
 * Model implementation for nat pool
 * 
 * @author vinod borole
 */
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
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isShared() {
        return shared;
    }

    @Override
    public String getExternalSegmentId() {
        return externalSegmentId;
    }

    @Override
    public String getIpPool() {
        return ipPool;
    }

    @Override
    public String getIpVersion() {
        return ipVersion;
    }

    @Override
    public String getSubnetId() {
        return subnetId;
    }

    @Override
    public NatPoolBuilder toBuilder() {
        return new NatPoolConcreteBuilder(this);
    }
    
    public static class NatPools extends ListResult<GbpNatPool>{

        private static final long serialVersionUID = 1L;
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
