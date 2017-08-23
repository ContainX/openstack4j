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
import com.huawei.openstack4j.model.network.ext.MemberV2Update;
import com.huawei.openstack4j.model.network.ext.builder.MemberV2UpdateBuilder;

import com.google.common.base.MoreObjects;

/**
 * Entity for updating lbaas v2 members
 * @author emjburns
 */
@JsonRootName("member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronMemberV2Update implements MemberV2Update {

    private static final long serialVersionUID = 1L;

    /**
     * 1~100
     */
    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp = true;

    @Override
    public boolean isAdminStateUp(){
        return adminStateUp;
    }

    @Override
    public Integer getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("weight", weight)
                .add("adminStateUp",adminStateUp)
                .toString();
    }

    public static class MemberV2UpdateConcreteBuilder implements MemberV2UpdateBuilder {
        private NeutronMemberV2Update m;

        public MemberV2UpdateConcreteBuilder() {
            this(new NeutronMemberV2Update());
        }

        public MemberV2UpdateConcreteBuilder(NeutronMemberV2Update m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2Update build(){
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2UpdateBuilder from(MemberV2Update in){
            m = (NeutronMemberV2Update) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2UpdateBuilder adminStateUp(boolean adminStateUp){
            m.adminStateUp = adminStateUp;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberV2UpdateBuilder weight(Integer weight){
            m.weight = weight;
            return this;
        }
    }

    @Override
    public MemberV2UpdateBuilder toBuilder(){
        return new MemberV2UpdateConcreteBuilder(this);
    }

    public static MemberV2UpdateBuilder builder(){
        return new MemberV2UpdateConcreteBuilder();
    }
}
