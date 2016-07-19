package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.LbMethod;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.Protocol;
import org.openstack4j.model.network.ext.SessionPersistence;
import org.openstack4j.model.network.ext.builder.LbPoolV2Builder;

import java.util.List;

/**
 * Lbaas V2 load balancer pool
 * @author emjburns
 */
//TODO: is this the right json root name?
@JsonRootName("pool_V2")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronLbPoolV2 implements LbPoolV2{

    @JsonProperty("health_monitors")
    private List<String> healthMonitors;

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String name;

    private String description;

    private Protocol protocol;

    @JsonProperty("lb_method")
    private LbMethod lbMethod;

    private SessionPersistence sessionPersistence;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp;

    private List<Listener> listeners;

    private List<String> members;

    private String healthMonitorId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId(){
        return id;
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
    public String getName(){
        return name;
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
    public Protocol getProtocol(){
        return protocol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbMethod getLbMethod(){
        return lbMethod;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionPersistence getSessionPersistence(){
        return sessionPersistence;
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
    public List<Listener> getListeners(){
        return listeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMembers(){
        return members;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHealthMonitorId(){
        return healthMonitorId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2Builder toBuilder(){
        //TODO: implement builder
        return null;
    }

    public static LbPoolV2Builder builder(){
        //TODO: implement builder
        return null;
    }

    @Override
    public String toString(){
        return "NeutronLbPoolV2{" +
                "healthMonitors=" + healthMonitors +
                ", id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", protocol=" + protocol +
                ", lbMethod=" + lbMethod +
                ", sessionPersistence=" + sessionPersistence +
                ", adminStateUp=" + adminStateUp +
                ", listeners=" + listeners +
                ", members=" + members +
                ", healthMonitorId='" + healthMonitorId + '\'' +
                '}';
    }
}
