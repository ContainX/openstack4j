package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import org.openstack4j.model.network.ext.ListenerUpdate;
import org.openstack4j.model.network.ext.builder.ListenerUpdateBuilder;

/**
 * Entity for updating lbaas v2 listener
 * @author emjburns
 */
@JsonRootName("listener")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronListenerV2Update implements ListenerUpdate{

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp;

    /**
     * The maximum number of connections allowed for the listener. Default is -1, meaning no limit.
     */
    @JsonProperty("connection_limit")
    private Integer connectionLimit;

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerUpdateBuilder toBuilder(){
        return new ListenerUpdateConcreteBuilder(this);
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
    public Integer getConnectionLimit(){
        return connectionLimit;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("adminStateUp", adminStateUp)
                .add("description", description)
                .add("name", name)
                .add("connectionLimit", connectionLimit)
                .toString();
    }

    public static class ListenerUpdateConcreteBuilder implements ListenerUpdateBuilder{
        private NeutronListenerV2Update m;

        public ListenerUpdateConcreteBuilder(){
            this(new NeutronListenerV2Update());
        }

        public ListenerUpdateConcreteBuilder(NeutronListenerV2Update m){
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListenerUpdate build(){
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListenerUpdateBuilder from(ListenerUpdate in){
            m = (NeutronListenerV2Update) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListenerUpdateBuilder name(String name){
            m.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListenerUpdateBuilder description(String description){
            m.description = description;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListenerUpdateBuilder adminStateUp(boolean adminStateUp){
            m.adminStateUp = adminStateUp;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListenerUpdateBuilder connectionLimit(Integer connectionLimit){
            m.connectionLimit = connectionLimit;
            return this;
        }
    }

    public static ListenerUpdateBuilder builder() {
        return new ListenerUpdateConcreteBuilder();
    }
}
