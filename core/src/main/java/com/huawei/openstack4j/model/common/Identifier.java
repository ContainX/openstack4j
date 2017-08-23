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
package com.huawei.openstack4j.model.common;

/** 
 * Represents an identifier which can either be an ID or Name
 * 
 * @author Jeremy Unruh
 */
public class Identifier {

    public enum Type { ID, NAME }
    
    private final Type type;
    private final String id;
    
    private Identifier(Type type, String id) {
        this.type = type;
        this.id = id;
    }
    
    /**
     * Creates a new identifier which is an ID based value
     * 
     * @param id the ID value
     * @return identifier object
     */
    public static Identifier byId(String id) {
        return new Identifier(Type.ID, id);
    }
    
    /**
     * Creates an identifier which is NAME based identification
     * @param name the name value
     * @return the identifier
     */
    public static Identifier byName(String name) {
        return new Identifier(Type.NAME, name);
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }
    
    public boolean isTypeID() {
        return type == Type.ID;
    }
    
    
}
