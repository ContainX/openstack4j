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
package com.huawei.openstack4j.model.compute;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents a Port State
 * 
 * @author Jeremy Unruh
 */
public enum PortState {
    ACTIVE,
    DOWN,
    BUILD,
    ERROR,
    UNRECOGNIZED;
    
      @JsonCreator
      public static PortState forValue(String value) {
          if (value != null)
          {
              for (PortState s : PortState.values()) {
                  if (s.name().equalsIgnoreCase(value))
                      return s;
              }
          }
          return PortState.UNRECOGNIZED;
      }
    
}
