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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

import com.google.common.base.MoreObjects;

/**
 * Representation of a json patch operation for an openstack image update
 * @author emjburns
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchOperation implements ModelEntity {
    public enum OperationType {
        ADD,
        REMOVE,
        REPLACE,
        UNKNOWN;

        @JsonCreator
        public static OperationType value(String v)
        {
            if (v == null) return UNKNOWN;
            try {
                return valueOf(v.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    private OperationType op;
    private String path;
    private Object value;

    public PatchOperation() {}

    public PatchOperation(OperationType op, String path, Object value) {
        this.op = op;
        this.path = path;
        this.value = value;
    }

    public PatchOperation(OperationType op, String path) {
        this.op = op;
        this.path = path;
    }

    public OperationType getOp() {
        return op;
    }

    public String getPath() {
        return path;
    }

    public Object getValue() {
        return value;
    }

    public void setOp(OperationType op) {
        this.op = op;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("op", op)
                .add("path", path)
                .add("value", value)
                .toString();
    }
}
