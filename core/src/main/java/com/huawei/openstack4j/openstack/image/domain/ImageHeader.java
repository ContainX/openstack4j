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
package com.huawei.openstack4j.openstack.image.domain;

/**
 * Used to decode and encode header values into corresponding image mutators
 * 
 * @author Jeremy Unruh
 */
public enum ImageHeader {
	 ID, NAME, CHECKSUM, MIN_DISK, MIN_RAM, IS_PUBLIC, PROTECTED, CREATED_AT, UPDATED_AT, DELETED_AT,COPY_FROM,
   OWNER, LOCATION, STATUS, DISK_FORMAT, CONTAINER_FORMAT, SIZE, SIZE_MIN, SIZE_MAX, STORE, PROPERTY, DELETED;

   public static final String HEADER_PREFIX = "X-Image-Meta-";
   public static final String GLANCE_HEADER_PREFIX = "x-glance-api-";

   public String asParam() {
      return name().toLowerCase();
   }

   public String asHeader() {
      return HEADER_PREFIX + name().charAt(0) + name().substring(1).toLowerCase();
   }

   public String asGlanceHeader() {
        return GLANCE_HEADER_PREFIX + name().charAt(0) + name().substring(1).toLowerCase();
   }

}