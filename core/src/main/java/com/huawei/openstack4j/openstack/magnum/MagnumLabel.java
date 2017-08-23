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
package com.huawei.openstack4j.openstack.magnum;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.magnum.Label;
import com.huawei.openstack4j.model.magnum.LabelBuilder;

import com.google.common.base.MoreObjects;

public class MagnumLabel implements Label {
    private static final long serialVersionUID = 1L;
    @JsonProperty("key")
    private String key;

    public static LabelBuilder builder() {
        return new LabelConcreteBuilder();
    }

    @Override
    public LabelBuilder toBuilder() {
        return new LabelConcreteBuilder(this);
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("key", key).toString();
    }

    /**
     * Concrete builder containing MagnumLabel as model
     *
     */
    public static class LabelConcreteBuilder implements LabelBuilder {
        MagnumLabel model;

        public LabelConcreteBuilder() {
            this(new MagnumLabel());
        }

        public LabelConcreteBuilder(MagnumLabel model) {
            this.model = model;
        }

        @Override
        public Label build() {
            return model;
        }

        @Override
        public LabelBuilder from(Label in) {
            if (in != null)
                this.model = (MagnumLabel) in;
            return this;
        }

        @Override
        public LabelBuilder key(String key) {
            model.key = key;
            return this;
        }
    }

}
