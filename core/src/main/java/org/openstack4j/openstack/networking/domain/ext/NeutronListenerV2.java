package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.LoadBalancerV2;
import org.openstack4j.model.network.ext.Protocol;
import org.openstack4j.model.network.ext.builder.ListenerBuilder;

import java.util.List;

/**
 * lbaas v2 listener
 * @author emjburns
 */
@JsonRootName("listener_v2")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronListenerV2 implements Listener {
    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String name;

    private String description;

    /**
     * The protocol of the VIP address. A valid value is TCP, HTTP, or HTTPS
     */
    private Protocol protocol;

    /**
     * The port on which to listen to client traffic that is associated with the
     * VIP address. A valid value is from 0 to 65535.
     */
    @JsonProperty("protocol_port")
    private Integer protocolPort;

    /**
     * The maximum number of connections allowed for the VIP. Default is -1, meaning no limit.
     */
    @JsonProperty("connection_limit")
    private Integer connectionLimit;

    @JsonProperty("default_pool_id")
    private String defaultPoolId;

    private List<LoadBalancerV2> loadbalancers;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp;


    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerBuilder toBuilder(){
        //TODO: implement builder
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LoadBalancerV2> getLoadBalancers(){
        return loadbalancers;
    }

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
    public String getDefaultPoolId(){
        return defaultPoolId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getConnectionLimit(){
        return connectionLimit;
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
    public Protocol getProtocol(){
        return protocol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(){
        return name;
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

    public static ListenerBuilder builder(){
        //TODO: implement builder
        return null;
    }

    @Override
    public String toString(){
        return "NeutronListenerV2{" +
                "id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", protocol=" + protocol +
                ", protocolPort=" + protocolPort +
                ", connectionLimit=" + connectionLimit +
                ", defaultPoolId='" + defaultPoolId + '\'' +
                ", loadbalancers=" + loadbalancers +
                ", adminStateUp=" + adminStateUp +
                '}';
    }
}
