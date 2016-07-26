package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.MemberV2;
import org.openstack4j.model.network.ext.builder.MemberV2Builder;

/**
 * A member of a v2 lbaas pool
 * @author emjburns
 */
@JsonRootName("member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronMemberV2 implements MemberV2 {
    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String address;

    @JsonProperty("protocol_port")
    private Integer protocolPort;

    /**
     * 1~100
     */
    private Integer weight;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp;

    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdminStateUp(){
        return adminStateUp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubnetId(){
        return subnetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getWeight(){
        return weight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getProtocolPort(){
        return protocolPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAddress(){
        return address;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTenantId(){
        return tenantId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId(){
        return id;
    }

    @Override
    public String toString(){
        return "NeutronMemberV2{" +
                "id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", address='" + address + '\'' +
                ", protocolPort=" + protocolPort +
                ", weight=" + weight +
                ", adminStateUp=" + adminStateUp +
                ", subnetId='" + subnetId + '\'' +
                '}';
    }

    /**
     * MemberV2 create builder
     */
    public static class MemberV2ConcreteBuilder implements MemberV2Builder {
        private NeutronMemberV2 m;

        public MemberV2ConcreteBuilder() {
            this(new NeutronMemberV2());
        }

        public MemberV2ConcreteBuilder(NeutronMemberV2 m) {
            this.m = m;
        }

        @Override
        public MemberV2 build(){
            return m;
        }

        @Override
        public MemberV2Builder from(MemberV2 in){
            m = (NeutronMemberV2) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Builder tenantId(String tenantId){
            m.tenantId = tenantId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Builder address(String address){
            m.address = address;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Builder protocolPort(Integer protocolPort){
            m.protocolPort = protocolPort;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Builder subnetId(String subnetId){
            m.subnetId = subnetId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Builder weight(Integer weight){
            m.weight = weight;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Builder adminStateUp(boolean adminStateUp){
            m.adminStateUp = adminStateUp;
            return this;
        }
    }

    @Override
    public MemberV2Builder toBuilder(){
        return new MemberV2ConcreteBuilder(this);
    }

    public static MemberV2Builder builder(){
        return new MemberV2ConcreteBuilder();
    }
}
