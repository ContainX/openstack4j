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
package com.huawei.openstack4j.model.manila;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.builder.ShareManageBuilder;

/**
 * Object to configure Shared File Systems to manage a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareManage extends ModelEntity, Buildable<ShareManageBuilder> {
    /**
     * @return the Shared File Systems protocol of the share to manage
     */
    Share.Protocol getProtocol();

    /**
     * @return the share name
     */
    String getName();

    /**
     * @return the share type name
     */
    String getShareType();

    /**
     * @return a set of one or more key and value pairs, as a dictionary of strings, that describe driver options
     */
    Map<String, String> getDriverOptions();

    /**
     * @return the share export path in the format appropriate for the protocol
     */
    String getExportPath();

    /**
     * @return the manage-share service host in this format: <code>host@backend#POOL</code>
     */
    String getServiceHost();

    /**
     * @return the share description
     */
    String getDescription();
}
