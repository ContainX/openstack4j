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
import com.huawei.openstack4j.model.barbican.Container;
import com.huawei.openstack4j.model.barbican.ContainerConsumer;
import com.huawei.openstack4j.model.barbican.ContainerSecret;
import com.huawei.openstack4j.model.barbican.builder.ContainerCreateBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

import java.util.Date;
import java.util.List;

public class BarbicanContainer implements Container {
    private static final long serialVersionUID = 1L;
    private String status;
    @JsonProperty("updated")
    private Date updatedTime;
    @JsonProperty("created")
    private Date createdTime;
    private String name;
    private List<BarbicanContainerConsumer> consumers;
    @JsonProperty("container_ref")
    private String containerReference;
    @JsonProperty("creator_id")
    private String creatorId;
    @JsonProperty("secret_refs")
    private List<BarbicanContainerSecret> secretReferences;
    private String type;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getUpdatedTime() {
        return updatedTime;
    }

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
    public List<? extends ContainerConsumer> getConsumers() {
        return consumers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContainerReference() {
        return containerReference;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ContainerSecret> getSecretReferences() {
        return secretReferences;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("status", status).add("name", name).add("updated", updatedTime)
                .add("created", createdTime).add("consumers", consumers).add("reference", containerReference)
                .add("creatorId", creatorId).add("secrets", secretReferences).add("type", type)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerCreateBuilder toBuilder() {
        return new ContainerCreateConcreteBuilder();
    }

    public static class Containers extends ListResult<BarbicanContainer> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("total")
        private int count;

        @JsonProperty("containers")
        private List<BarbicanContainer> list;

        protected List<BarbicanContainer> value() {
            return list;
        }

        public int getCount() {
            return count;
        }
    }

    public static class ContainerCreateConcreteBuilder implements ContainerCreateBuilder {
        private BarbicanContainer internalContainer;

        public ContainerCreateConcreteBuilder() {
            this(new BarbicanContainer());
        }

        public ContainerCreateConcreteBuilder(BarbicanContainer container) {
            this.internalContainer = container;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Container build() {
            return internalContainer;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerCreateBuilder from(Container in){
            internalContainer = (BarbicanContainer) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerCreateBuilder name(String name){
            internalContainer.name = name;
            return this;
        }

        /**
         * {@inheritDoc}
         * @param type
         */
        @Override
        public ContainerCreateBuilder type(String type){
            internalContainer.type = type;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ContainerCreateBuilder secretReferences(List<? extends ContainerSecret> references){
            internalContainer.secretReferences = (List<BarbicanContainerSecret>) references;
            return this;
        }
    }

    public static ContainerCreateBuilder builder() {
        return new ContainerCreateConcreteBuilder();
    }
}
