/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.networking.domain.ext;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.Protocol;
import com.huawei.openstack4j.model.network.ext.SessionPersistence;
import com.huawei.openstack4j.model.network.ext.Vip;
import com.huawei.openstack4j.model.network.ext.builder.VipBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * @author liujunpeng
 *
 */
@JsonRootName("vip")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronVip implements Vip {

    private static final long serialVersionUID = 1L;

    private String id;
    @JsonProperty("tenant_id")
    private String tenantId;

    private String name;

    private String description;
    /**
     * The ID of the subnet on which to allocate the VIP address.
     */
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * The IP address of the VIP.
     */
    private String address;

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

    @JsonProperty("pool_id")
    private String poolId;

    /**
     * Session persistence parameters for the VIP. Omit the session_persistence
     * parameter to prevent session persistence. When no session persistence is
     * used, the session_persistence parameter does not appear in the API
     * response. To clear session persistence for the VIP, set the
     * session_persistence parameter to null in a VIP update request.
     */
    @JsonProperty("session_persistence")
    private NeutronSessionPersistence sessionPersistence;

    /**
     * The maximum number of connections allowed for the VIP. Default is -1, meaning no limit.
     */
    @JsonProperty("connection_limit")
    private Integer connectionLimit;
    @JsonProperty("admin_state_up")
    private boolean adminStateUp;

    /**
     * The status of the VIP. Indicates whether the VIP is operational.
     */
    private String status;
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public VipBuilder toBuilder() {
        return new VipContreteBuilder() ;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAddress() {
        return address;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Integer getConnectionLimit() {
        return connectionLimit;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getPoolId() {
        return poolId;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Integer getProtocolPort() {
        return protocolPort;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public SessionPersistence getSessionPersistence() {
        return sessionPersistence;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getSubnetId() {
        return subnetId;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getTenantId() {
        return tenantId;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("address", address)
                .add("adminStateUp", adminStateUp)
                .add("connectionLimit", connectionLimit)
                .add("description", description)
                .add("id", id)
                .add("name", name)
                .add("poolId", poolId)
                .add("protocol", protocol)
                .add("protocolPort", protocolPort)
                .add("status", status)
                .add("subnetId", subnetId)
                .add("tenantId", tenantId)
                .add("sessionPersistence", sessionPersistence)
                .toString();
    }


    /**
     *
     * @author liujunpeng
     *
     */
    public static class Vips extends ListResult<NeutronVip>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("vips")
        List<NeutronVip> vips;
        @Override
        public List<NeutronVip> value() {
            return vips;
        }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("vips", vips).toString();
        }

    }
    public static class VipContreteBuilder implements VipBuilder {

        private NeutronVip m;
        public VipContreteBuilder(){
            this(new NeutronVip());
        }
        public VipContreteBuilder(NeutronVip m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public Vip build() {
            return m;
        }

        @Override
        public VipBuilder from(Vip in) {
            m = (NeutronVip)in;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder tenantId(String tenantId) {
            m.tenantId = tenantId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder name(String name) {
            m.name = name;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder description(String description) {
            m.description = description;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder subnetId(String subnetId) {
            m.subnetId = subnetId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder address(String address) {
            m.address = address;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder protocol(Protocol protocol) {
            m.protocol = protocol;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder protocolPort(Integer protocolPort) {
            m.protocolPort = protocolPort;
            return this;
        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder poolId(String poolId) {
            m.poolId = poolId;
            return this;
        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder sessionPersistence(
                SessionPersistence sessionPersistence) {
            m.sessionPersistence = (NeutronSessionPersistence)sessionPersistence;
            return this;
        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder connectionLimit(Integer connectionLimit) {
            m.connectionLimit = connectionLimit;
            return this;
        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public VipBuilder adminStateUp(boolean adminStateUp) {
            m.adminStateUp = adminStateUp;
            return this;
        }

    }

    public static VipBuilder  builder(){
        return new VipContreteBuilder();

    }
}
