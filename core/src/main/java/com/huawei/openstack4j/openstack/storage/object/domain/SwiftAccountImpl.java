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
package com.huawei.openstack4j.openstack.storage.object.domain;

import java.util.Map;

import com.google.common.base.MoreObjects;

import com.huawei.openstack4j.model.storage.object.SwiftAccount;

/**
 * An Account representation for OpenStack Object Storage (Swift)
 *
 * @author Jeremy Unruh
 */
public class SwiftAccountImpl implements SwiftAccount {

    private static final long serialVersionUID = -5316007949264717108L;

    private long containerCount;
    private long objectCount;
    private long bytesUsed;
    private String temporaryUrlKey;
    private Map<String, String> metadata;

    protected SwiftAccountImpl() {
    }

    @Override
    public long getContainerCount() {
        return containerCount;
    }

    @Override
    public long getObjectCount() {
        return objectCount;
    }

    @Override
    public long getBytesUsed() {
        return bytesUsed;
    }

    @Override
    public String getTemporaryUrlKey() {
        return temporaryUrlKey;
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                  .add("containerCount", containerCount).add("objectCount", objectCount)
                  .add("bytesUsed", bytesUsed).add("temporaryUrlKey", temporaryUrlKey)
                  .add("metadata", metadata)
                  .toString();
    }

    public static class AccountBuilder {
        private SwiftAccountImpl account = new SwiftAccountImpl();

        public AccountBuilder containerCount(long containerCount) {
            account.containerCount = containerCount;
            return this;
        }

        public AccountBuilder objectCount(long objectCount) {
            account.objectCount = objectCount;
            return this;
        }

        public AccountBuilder bytesUsed(long bytesUsed) {
            account.bytesUsed = bytesUsed;
            return this;
        }

        public AccountBuilder temporaryUrlKey(String temporaryUrlKey) {
            account.temporaryUrlKey = temporaryUrlKey;
            return this;
        }

        public AccountBuilder metadata(Map<String, String> metadata) {
            account.metadata = metadata;
            return this;
        }

        public SwiftAccountImpl build() {
            return account;
        }
    }

}


