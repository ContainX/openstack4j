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

/**
 * The Shared File System builders
 */
public interface SharedFileSystemBuilders {

    /**
     * The builder which creates manila security services
     *
     * @return the security service builder
     */
    public SecurityServiceCreateBuilder securityService();

    /**
     * The builder which creates manila share networks.
     *
     * @return the share network builder
     */
    public ShareNetworkCreateBuilder shareNetwork();

    /**
     * The builder which creates manila shares.
     *
     * @return the share builder
     */
    public ShareCreateBuilder share();

    /**
     * The builder which creates share types.
     *
     * @return the shae type builder
     */
    public ShareTypeCreateBuilder shareType();

    /**
     * The builder which creates manila share snapshots.
     *
     * @return the share builder
     */
    public ShareSnapshotCreateBuilder shareSnapshot();

    /**
     * The builder which creates manila share manages
     *
     * @return the share manage builder
     */
    public ShareManageBuilder shareManage();

}
