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
package com.huawei.openstack4j.model.network.ext.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.network.ext.ListenerV2Update;

/**
 * A Builder to update a lbaas v2 listener
 * @author emjburns
 */
public interface ListenerV2UpdateBuilder extends Buildable.Builder<ListenerV2UpdateBuilder, ListenerV2Update> {

    /**
     * Optional
     *
     * @param name
     *            Human-readable name for the listener. Does not have to be unique.
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Human-readable description for the listener.
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder description(String description);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the listener. A valid value is true
     *            (UP) or false (DOWN).
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder adminStateUp(boolean adminStateUp);

    /**
     * Optional
     * The maximum number of connections allowed for the listener. Default is -1, meaning no limit.
     * @param connectionLimit
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder connectionLimit(Integer connectionLimit);

    /**
     * Optional
     *
     * The tls container reference
     * @param defaultTlsContainerRef
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder defaultTlsContainerRef(String defaultTlsContainerRef);
}
