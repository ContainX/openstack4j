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

import static com.huawei.openstack4j.core.transport.HttpEntityHandler.*;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Base OpenStack Storage Service
 * 
 * @author Jeremy Unruh
 */
public class BaseObjectStorageService extends BaseOpenStackService {

    public BaseObjectStorageService() {
        super(ServiceType.OBJECT_STORAGE);
    }
    
    protected boolean isResponseSuccess(HttpResponse res, int status) {
        return isResponseSuccess(res, status, true);
    }
    
    protected boolean isResponseSuccess(HttpResponse res, int status, boolean closeResponse) {
        boolean result = res.getStatus() == status;
        if (closeResponse) {
            closeQuietly(res);
        }
        return result;
    }
}
