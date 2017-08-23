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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.magnum.Carequest;
import com.huawei.openstack4j.model.magnum.CarequestBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MagnumCarequest implements Carequest {
    private static final long serialVersionUID = 1L;
    @JsonProperty("bay_uuid")
    private String bayUuid;
    @JsonProperty("csr")
    private String csr;

    public static CarequestBuilder builder() {
        return new CarequestConcreteBuilder();
    }

    @Override
    public CarequestBuilder toBuilder() {
        return new CarequestConcreteBuilder(this);
    }

    public String getBayUuid() {
        return bayUuid;
    }

    public String getCsr() {
        return csr;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("bayUuid", bayUuid).add("csr", csr).toString();
    }

    /**
     * Concrete builder containing MagnumCarequest as model
     *
     */
    public static class CarequestConcreteBuilder implements CarequestBuilder {
        MagnumCarequest model;

        public CarequestConcreteBuilder() {
            this(new MagnumCarequest());
        }

        public CarequestConcreteBuilder(MagnumCarequest model) {
            this.model = model;
        }

        @Override
        public Carequest build() {
            return model;
        }

        @Override
        public CarequestBuilder from(Carequest in) {
            if (in != null)
                this.model = (MagnumCarequest) in;
            return this;
        }

        @Override
        public CarequestBuilder bayUuid(String bayUuid) {
            model.bayUuid = bayUuid;
            return this;
        }

        @Override
        public CarequestBuilder csr(String csr) {
            model.csr = csr;
            return this;
        }
    }

    public static class Carequests extends ListResult<MagnumCarequest> {
        private static final long serialVersionUID = 1L;
        @JsonProperty("carequests")
        private List<MagnumCarequest> list;

        @Override
        public List<MagnumCarequest> value() {
            return list;
        }
    }
}
