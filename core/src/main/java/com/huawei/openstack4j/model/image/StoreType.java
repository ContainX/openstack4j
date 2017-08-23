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
package com.huawei.openstack4j.model.image;

/**
 * Backing store types for glance images
 * 
 * @author Jeremy Unruh
 */
public enum StoreType {
	
  /**
   * File system store
   */
  FILE,
  /**
   * S3 store
   */
  S3,
  /**
   * OpenStack swift store
   */
  SWIFT;
  
  public String value() {
  	return name().toLowerCase();
  }
  
  @Override
  public String toString() {
  	return value();
  }
}
