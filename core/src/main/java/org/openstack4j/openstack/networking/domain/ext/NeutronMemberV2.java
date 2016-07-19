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
@JsonRootName("member_v2")
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
    public MemberV2Builder toBuilder(){
        //TODO: implement builder
        return null;
    }

    public static MemberV2Builder builder(){
        //TODO: implement builder
        return null;
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
}
