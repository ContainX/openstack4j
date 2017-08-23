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
package com.huawei.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.network.ExtraDhcpOptCreate;
import com.huawei.openstack4j.model.network.builder.ExtraDhcpOptBuilder;

/**
 *
 * @author Ales Kemr
 */
public class NeutronExtraDhcpOptCreate implements ExtraDhcpOptCreate {

    // {"opt_value": "testfile.1", "opt_name": "bootfile-name"}
    
    @JsonProperty("opt_value")
    public String opt_value;
    @JsonProperty("opt_name")
    public String opt_name;
    
    @Override
    public String getOptValue() {
        return opt_value;
    }

    @Override
    public String getOptName() {
        return opt_name;
    }

    public static NeutronExtraDhcpOptBuilder builder() {
        return new NeutronExtraDhcpOptBuilder(new NeutronExtraDhcpOptCreate());
    }

    @Override
    public ExtraDhcpOptBuilder toBuilder() {
        return new NeutronExtraDhcpOptBuilder(this);
    }
    
    public static class NeutronExtraDhcpOptBuilder implements ExtraDhcpOptBuilder {

        NeutronExtraDhcpOptCreate create;

        public NeutronExtraDhcpOptBuilder(NeutronExtraDhcpOptCreate create) {
            this.create = create;
        }
        
        @Override
        public ExtraDhcpOptBuilder optValue(String optValue) {
            create.opt_value = optValue;
            return this;
        }

        @Override
        public ExtraDhcpOptBuilder optName(String optName) {
            create.opt_name = optName;
            return this;
        }

        @Override
        public ExtraDhcpOptCreate build() {
            return create;
        }

        @Override
        public ExtraDhcpOptBuilder from(ExtraDhcpOptCreate in) {
            return new NeutronExtraDhcpOptBuilder((NeutronExtraDhcpOptCreate) in);
        }
        
    }
}
