package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import org.openstack4j.model.network.ext.LbMethod;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.Protocol;
import org.openstack4j.model.network.ext.SessionPersistence;
import org.openstack4j.model.network.ext.builder.LbPoolV2Builder;
import org.openstack4j.openstack.common.ListResult;

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

    //should these be strings?
    private List<Listener> listeners;

    private List<String> members;

    private String healthMonitorId;

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2Builder toBuilder() {
        return new LbPoolV2ConcreteBuilder(this);
    }


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


    public static class LbPoolV2ConcreteBuilder implements LbPoolV2Builder{

        private NeutronLbPoolV2 m;

        @Override
        public LbPoolV2 build() {
            return m;
        }

        public LbPoolV2ConcreteBuilder() {
            this(new NeutronLbPoolV2());
        }

        public LbPoolV2ConcreteBuilder(NeutronLbPoolV2 m) {
            this.m = m;
        }

        @Override
        public LbPoolV2Builder from(LbPoolV2 in){
            m = (NeutronLbPoolV2) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder tenantId(String tenantId){
            m.tenantId = tenantId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder name(String name){
            m.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder description(String description){
            m.description = description;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder protocol(Protocol protocol){
            m.protocol = protocol;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder lbMethod(LbMethod lbMethod){
            m.lbMethod = lbMethod;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder sessionPersistence(SessionPersistence sessionPersistence){
            m.sessionPersistence = sessionPersistence;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder adminStateUp(boolean adminStateUp){
            m.adminStateUp = adminStateUp;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2Builder listenerId(String listenerId){
            // TODO: how do we get listener id into the listeners list?
            return null;
        }
    }

    public static class LbPoolsV2 extends ListResult<NeutronLbPoolV2> {
        @JsonProperty("pools")
        List<NeutronLbPoolV2> lbPools;
        /**
         * {@inheritDoc}
         */
        @Override
        protected List<NeutronLbPoolV2> value(){
            return lbPools;
        }

        @Override
        public String toString(){
            return Objects.toStringHelper(this)
                    .add("lbPools", lbPools)
                    .toString();
        }
    }

    public static LbPoolV2Builder builder() {
        return new LbPoolV2ConcreteBuilder();
    }
}
