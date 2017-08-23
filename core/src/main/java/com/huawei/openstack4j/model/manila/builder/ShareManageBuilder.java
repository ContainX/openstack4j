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
package com.huawei.openstack4j.model.manila.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.manila.Share;
import com.huawei.openstack4j.model.manila.ShareCreate;
import com.huawei.openstack4j.model.manila.ShareManage;

/**
 * Builds a {@link ShareCreate}.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareManageBuilder extends Buildable.Builder<ShareManageBuilder, ShareManage> {
    /**
     * Set the Shared File Systems protocol of the share to manage. A valid value is NFS, CIFS, GlusterFS, or HDFS.
     *
     * @param protocol the share protocol
     * @return ShareManageBuilder
     */
    ShareManageBuilder protocol(Share.Protocol protocol);

    /**
     * Set the share name.
     *
     * @param name the share name
     * @return ShareManageBuilder
     */
    ShareManageBuilder name(String name);

    /**
     * Set the share type name.
     * @param shareType the share type
     * @return ShareManageBuilder
     */
    ShareManageBuilder shareType(String shareType);

    /**
     * Add a driver option.
     *
     * @param key the key of the driver option
     * @param value the value of the driver option
     * @return ShareManageBuilder
     */
    ShareManageBuilder addDriverOption(String key, String value);

    /**
     * Set the driver options.
     *
     * @param driverOptions the driver options
     * @return ShareManageBuilder
     */
    ShareManageBuilder driverOptions(Map<String, String> driverOptions);

    /**
     * Set the share export path.
     *
     * @param exportPath the share export path
     * @return ShareManageBuilder
     */
    ShareManageBuilder exportPath(String exportPath);

    /**
     * Set the manage-share service host in this format: <code>host@backend#POOL</code>.
     *
     * @param serviceHost the service host
     * @return ShareManageBuilder
     */
    ShareManageBuilder serviceHost(String serviceHost);

    /**
     * Set the share description
     *
     * @param desciption the share description
     * @return ShareManageBuilder
     */
    ShareManageBuilder description(String desciption);
}
