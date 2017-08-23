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
package com.huawei.openstack4j.openstack.image.v2.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.model.image.v2.ImageUpdate;
import com.huawei.openstack4j.model.image.v2.builder.ImageUpdateBuilder;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of updating a glance image
 * @author emjburns
 */
public class GlanceImageUpdate implements ImageUpdate {

    List<PatchOperation> ops = new ArrayList<>();

    public GlanceImageUpdate() {
    }

    public GlanceImageUpdate(JsonNode value) {
        if (value.isArray()){
            for(Iterator<JsonNode> iterator = value.iterator(); iterator.hasNext();) {
                JsonNode next = iterator.next();
                iterator.remove();
                PatchOperation p = new PatchOperation(
                        PatchOperation.OperationType.value(next.get("op").textValue()),
                        next.get("path").textValue(),
                        next.get("value")
                );
                ops.add(p);
            }
        }
    }

    public GlanceImageUpdate(List<PatchOperation> ops) {
        this.ops = ops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonValue
    public List<PatchOperation> getOps() {
        return ops;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ops", ops)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageUpdateBuilder toBuilder() {
        return new ImageUpdateConcreteBuilder(this);
    }

    public static ImageUpdateBuilder builder() {
        return new ImageUpdateConcreteBuilder();
    }

    public static class ImageUpdateConcreteBuilder implements ImageUpdateBuilder {
        private GlanceImageUpdate m;

        public ImageUpdateConcreteBuilder() {
            this(new GlanceImageUpdate());
        }

        public ImageUpdateConcreteBuilder(GlanceImageUpdate m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageUpdateBuilder ops(List<PatchOperation> ops) {
            m.ops = ops;
            return this;
        }

        @Override
        public ImageUpdateBuilder ops(PatchOperation op) {
            if (m.ops == null) m.ops = new ArrayList<>();
            m.ops.add(op);
            return this;
        }

        @Override
        public ImageUpdate build() {
            return m;
        }

        @Override
        public ImageUpdateBuilder from(ImageUpdate in) {
            m = (GlanceImageUpdate) in;
            return this;
        }
    }
}
