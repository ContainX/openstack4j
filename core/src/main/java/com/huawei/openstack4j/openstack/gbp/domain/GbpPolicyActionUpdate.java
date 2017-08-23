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
package com.huawei.openstack4j.openstack.gbp.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.gbp.PolicyActionUpdate;
import com.huawei.openstack4j.model.gbp.builder.PolicyActionUpdateBuilder;

import com.google.common.base.MoreObjects;

/**
 * Model implementation for Policy Action
 *
 * @author vinod borole
 */
@JsonRootName("policy_action")
public class GbpPolicyActionUpdate implements PolicyActionUpdate {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Boolean shared;


    @Override
    public PolicyActionUpdateBuilder toBuilder() {
        return new PolicyActionUpdateConcreteBuilder(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public boolean isShared() {
        return this.shared == null ? false : shared;
    }
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("name", name).add("desription", description)
               .add("shared", shared).toString();
    }

    public static class PolicyActionUpdateConcreteBuilder implements PolicyActionUpdateBuilder{

        private GbpPolicyActionUpdate policyAction;

        public PolicyActionUpdateConcreteBuilder(GbpPolicyActionUpdate gbpPolicyAction) {
            this.policyAction=gbpPolicyAction;
        }

        public PolicyActionUpdateConcreteBuilder() {
            this(new GbpPolicyActionUpdate());
        }

        @Override
        public PolicyActionUpdate build() {
            return policyAction;
        }

        @Override
        public PolicyActionUpdateBuilder name(String name) {
            this.policyAction.name=name;
            return this;
        }

        @Override
        public PolicyActionUpdateBuilder description(String description) {
            this.policyAction.description=description;
            return this;
        }


        @Override
        public PolicyActionUpdateBuilder shared(boolean shared) {
            this.policyAction.shared=shared;
            return this;
        }

        @Override
        public PolicyActionUpdateBuilder from(PolicyActionUpdate in) {
            this.policyAction=(GbpPolicyActionUpdate) in;
            return this;
        }

    }

    public static PolicyActionUpdateBuilder builder() {
        return new PolicyActionUpdateConcreteBuilder();
    }


}
