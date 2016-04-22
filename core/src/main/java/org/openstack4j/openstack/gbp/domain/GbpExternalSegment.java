package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.ExternalSegment;
import org.openstack4j.model.gbp.L3Policy;
import org.openstack4j.model.gbp.NatPool;
import org.openstack4j.model.gbp.builder.ExternalSegmentBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@JsonRootName("external_segment")
public class GbpExternalSegment implements ExternalSegment {
    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    @JsonProperty("external_policies")
    private List<GbpExternalPolicy> externalPolicies;
    @JsonProperty("l3_policies")
    private List<L3Policy> l3Policies;
    @JsonProperty("nat_pools")
    private List<NatPool> natpools;
    @JsonProperty("ip_version")
    private String ipVersion;
    @JsonProperty("cidr")
    private String cidr;
    private String description;
    private boolean shared;
    @JsonProperty("subnet_id")
    private String subnetId;
    @JsonProperty("port_address_translation")
    private boolean portAddressTranslation;
    @JsonProperty("external_routes")
    private GbpExternalRoutes externalRoutes;
    
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
    

    public List<GbpExternalPolicy> getExternalPolicies() {
        return externalPolicies;
    }

    public String getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(String ipVersion) {
        this.ipVersion = ipVersion;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
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

    public String getSubnetId() {
        return subnetId;
    }

    public List<L3Policy> getL3Policies() {
        return l3Policies;
    }

    public void setL3Policies(List<L3Policy> l3Policies) {
        this.l3Policies = l3Policies;
    }

    public List<NatPool> getNatpools() {
        return natpools;
    }

    public void setNatpools(List<NatPool> natpools) {
        this.natpools = natpools;
    }

    public void setExternalPolicies(List<GbpExternalPolicy> externalPolicies) {
        this.externalPolicies = externalPolicies;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }
    
    
    public boolean isPortAddressTranslation() {
        return portAddressTranslation;
    }

    public void setPortAddressTranslation(boolean portAddressTranslation) {
        this.portAddressTranslation = portAddressTranslation;
    }

    public GbpExternalRoutes getExternalRoutes() {
        return externalRoutes;
    }

    public void setExternalRoutes(GbpExternalRoutes externalRoutes) {
        this.externalRoutes = externalRoutes;
    }

    @Override
    public ExternalSegmentBuilder toBuilder() {
        return new ExternalSegmentConcreteBuilder(this);
    }
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues().add("", "").toString();
    }
    
    public static class ExternalSegments extends ListResult<GbpExternalSegment> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("external_segments")
        private List<GbpExternalSegment> externalSegments;

        @Override
        public List<GbpExternalSegment> value() {
            return externalSegments;
        }
    }
    
    public static class ExternalSegmentConcreteBuilder implements ExternalSegmentBuilder{

        private GbpExternalSegment extSegment;
        
        public ExternalSegmentConcreteBuilder(){
            this(new GbpExternalSegment());
        }
        
        public ExternalSegmentConcreteBuilder(GbpExternalSegment gbpExternalSegment) {
            this.extSegment=gbpExternalSegment;
        }

        @Override
        public ExternalSegment build() {
            return extSegment;
        }

        @Override
        public ExternalSegmentBuilder from(ExternalSegment in) {
            extSegment = (GbpExternalSegment)in;
            return this;
        }
        
    }

}
