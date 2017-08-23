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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.model.storage.object.SwiftHeaders.*;

import java.util.Map;

import com.huawei.openstack4j.api.storage.ObjectStorageAccountService;
import com.huawei.openstack4j.model.storage.object.SwiftAccount;
import com.huawei.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;
import com.huawei.openstack4j.openstack.storage.object.functions.ParseAccountFunction;

/**
 * The Object Storage Account based services
 * 
 * @author Jeremy Unruh
 */
public class ObjectStorageAccountServiceImpl extends BaseObjectStorageService implements ObjectStorageAccountService {

    /**
     * {@inheritDoc}
     */
    @Override
    public SwiftAccount get() {
        return ParseAccountFunction.INSTANCE.apply(head(Void.class).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateMetadata(Map<String, String> metadata) {
        checkNotNull(metadata);
        return invokeMetadata(ACCOUNT_METADATA_PREFIX, metadata);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteMetadata(Map<String, String> metadata) {
        checkNotNull(metadata);
        return invokeMetadata(ACCOUNT_REMOVE_METADATA_PREFIX, metadata);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateTemporaryUrlKey(String temporaryUrlKey) {
        checkNotNull(temporaryUrlKey);
        return isResponseSuccess(post(Void.class).header(ACCOUNT_TEMPORARY_URL_KEY, temporaryUrlKey).executeWithResponse(), 204);
    }

    private boolean invokeMetadata(String prefix, Map<String, String> metadata) {
        return isResponseSuccess(post(Void.class)
                                  .headers(MetadataToHeadersFunction.create(prefix).apply(metadata))
                                  .executeWithResponse(), 204);
    }
}
