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
package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.ListenerV2UpdateBuilder;

/**
 * An entity used to update an lbaas v2 listener
 * @author emjburns
 */
public interface ListenerV2Update extends ModelEntity, Buildable<ListenerV2UpdateBuilder> {
    /**
     * Optional
     * @see ListenerV2#isAdminStateUp()
     */
    public boolean isAdminStateUp();

    /**
     * Optional
     * @see ListenerV2#getDescription()
     */
    public String getDescription();

    /**
     * Optional
     * @see ListenerV2#getName()
     */
    public String getName();

    /**
     * Optional
     * @see ListenerV2#getConnectionLimit()
     */
    public Integer getConnectionLimit();

    /**
     * Optional
     * @see ListenerV2#getDefaultTlsContainerRef()
     */
    public String getDefaultTlsContainerRef();
}
