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
package com.huawei.openstack4j.openstack.barbican.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.barbican.ContainerSecret;
import com.huawei.openstack4j.model.barbican.builder.ContainerSecretBuilder;

import com.google.common.base.MoreObjects;

public class BarbicanContainerSecret implements ContainerSecret {
    private String name;
    @JsonProperty("secret_ref")
    private String reference;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getReference() {
        return reference;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("name", name).add("reference", reference)
                .toString();
    }

    @Override
    public ContainerSecretBuilder toBuilder() {
        return new SecretConcreteBuilder();
    }

    public static class SecretConcreteBuilder implements ContainerSecretBuilder {
        private BarbicanContainerSecret internalSecret;

        public SecretConcreteBuilder() {
            this(new BarbicanContainerSecret());
        }

        public SecretConcreteBuilder(BarbicanContainerSecret secret) {
            this.internalSecret = secret;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerSecret build() {
            return internalSecret;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerSecretBuilder from(ContainerSecret in){
            internalSecret = (BarbicanContainerSecret) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerSecretBuilder name(String name){
            internalSecret.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerSecretBuilder reference(String ref){
            internalSecret.reference = ref;
            return this;
        }
    }

    public static ContainerSecretBuilder builder() {
        return new SecretConcreteBuilder();
    }

}
