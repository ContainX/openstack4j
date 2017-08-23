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
package com.huawei.openstack4j.model.tacker.builder;

/**
 * NFV Builders..
 * 
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
public interface NfvBuilders {
	
	/**
     * The builder to create a Vnfd
     *
     * @return the vnfd builder
     */
    VnfdBuilder vnfd();
    
    /**
     * The builder to create a Vnf
     *
     * @return the vnf builder
     */
    VnfBuilder vnf();

    /**
     * The builder to update a vnf
     *
     * @return VnfUpdateBuilder
     */
    VnfUpdateBuilder vnfUpdate();
    
    /**
     * The builder to create a Vim
     *
     * @return the vim builder
     */
    VimBuilder vim();
}
