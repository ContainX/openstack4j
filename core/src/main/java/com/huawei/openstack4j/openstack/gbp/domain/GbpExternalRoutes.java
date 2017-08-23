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
import com.huawei.openstack4j.model.gbp.ExternalRoutes;
import com.huawei.openstack4j.model.gbp.builder.ExternalRoutesBuilder;

import com.google.common.base.MoreObjects;
/**
 * Model implementation for External Routes
 *
 * @author vinod borole
 */
@JsonRootName("external_routes")
public class GbpExternalRoutes implements ExternalRoutes {
    private static final long serialVersionUID = 1L;

    private String destination;
    private String nexthop;

    public GbpExternalRoutes() {
    }

    public GbpExternalRoutes(String destination, String nexthop){
        this.destination=destination;
        this.nexthop=nexthop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNexthop() {
        return nexthop;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                     .add("destination", destination).add("nexthop", nexthop).toString();
    }

    @Override
    public ExternalRoutesBuilder toBuilder() {
        return new ExternalRoutesConcreteBuilder(this);
    }

    public static class ExternalRoutesConcreteBuilder implements ExternalRoutesBuilder{
        private GbpExternalRoutes extRoutes;

        public ExternalRoutesConcreteBuilder(GbpExternalRoutes gbpExternalRoutes) {
            this.extRoutes=gbpExternalRoutes;
        }

        public ExternalRoutesConcreteBuilder() {
            this(new GbpExternalRoutes());
        }

        @Override
        public ExternalRoutes build() {
            return extRoutes;
        }

        @Override
        public ExternalRoutesBuilder from(ExternalRoutes in) {
            extRoutes=(GbpExternalRoutes) in;
            return this;
        }

        @Override
        public ExternalRoutesBuilder destination(String destination) {
            extRoutes.destination=destination;
            return this;
        }

        @Override
        public ExternalRoutesBuilder nextHop(String nextHop) {
            extRoutes.nexthop=nextHop;
            return this;
        }

    }

    public static ExternalRoutesBuilder builder() {
        return new ExternalRoutesConcreteBuilder();
    }
}
