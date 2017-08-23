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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.LbMethod;
import com.huawei.openstack4j.model.network.ext.LbPoolV2Update;
import com.huawei.openstack4j.model.network.ext.SessionPersistence;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolV2UpdateBuilder;

import com.google.common.base.MoreObjects;

/**
 * Used to update lbaas V2 lb pool
 * @author emjburns
 */
@JsonRootName("pool")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronLbPoolV2Update implements LbPoolV2Update {
    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp = true;

    @JsonProperty("lb_algorithm")
    private LbMethod lbMethod;

    @JsonProperty("session_persistence")
    private SessionPersistence sessionPersistence;

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2UpdateBuilder toBuilder() {
        return new NeutronLbPoolV2Update.LbPoolV2UpdateContreteBuilder(this);
    }

    public static LbPoolV2UpdateBuilder builder(){
        return new LbPoolV2UpdateContreteBuilder();
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public boolean isAdminStateUp(){
        return adminStateUp;
    }

    @Override
    public LbMethod getLbMethod(){
        return lbMethod;
    }

    @Override
    public SessionPersistence getSessionPersistence(){
        return sessionPersistence;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("adminStateUp", adminStateUp)
                .add("description", description)
                .add("lbMethod", lbMethod)
                .add("name", name)
                .toString();
    }

    public static class LbPoolV2UpdateContreteBuilder implements LbPoolV2UpdateBuilder {

        private NeutronLbPoolV2Update m;

        public LbPoolV2UpdateContreteBuilder (){
            this(new NeutronLbPoolV2Update());
        }

        public LbPoolV2UpdateContreteBuilder(NeutronLbPoolV2Update m){
            this.m = m;
        }

        @Override
        public LbPoolV2Update build() {

            return m;
        }

        @Override
        public LbPoolV2UpdateContreteBuilder from(LbPoolV2Update in) {
            m = (NeutronLbPoolV2Update)in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2UpdateContreteBuilder lbMethod(LbMethod lbMethod) {
            m.lbMethod = lbMethod;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2UpdateContreteBuilder name(String name) {
            m.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2UpdateContreteBuilder description(String description) {
            m.description = description;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LbPoolV2UpdateBuilder sessionPersistence(SessionPersistence sessionPersistence){
            m.sessionPersistence = sessionPersistence;
            return this;
        }

        @Override
        public LbPoolV2UpdateContreteBuilder adminStateUp(boolean adminStateUp) {
            m.adminStateUp = adminStateUp;
            return this;
        }
    }

}
