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
package com.huawei.openstack4j.openstack.storage.object.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.storage.ObjectStorageAccountService;
import com.huawei.openstack4j.api.storage.ObjectStorageContainerService;
import com.huawei.openstack4j.api.storage.ObjectStorageObjectService;
import com.huawei.openstack4j.api.storage.ObjectStorageService;

/**
 * OpenStack Object Storage service implementation
 * 
 * @author Jeremy Unruh
 */
public class ObjectStorageServiceImpl implements ObjectStorageService {

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectStorageAccountService account() {
        return Apis.get(ObjectStorageAccountService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectStorageContainerService containers() {
        return Apis.get(ObjectStorageContainerService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectStorageObjectService objects() {
        return Apis.get(ObjectStorageObjectService.class);
    }

}
