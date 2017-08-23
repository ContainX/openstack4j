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
package com.huawei.openstack4j.openstack.manila.internal;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.internal.MicroVersion;
import com.huawei.openstack4j.openstack.internal.MicroVersionedOpenStackService;

public class BaseShareServices extends MicroVersionedOpenStackService {
    private final static String API_VERSION_HEADER = "X-Openstack-Manila-Api-Version";

    private static final MicroVersion LIBERTY_VERSION = new MicroVersion(2, 6);
    private static final MicroVersion MITAKA_VERSION = new MicroVersion(2, 15);
    // At the moment, 2.6 is the latest micro-version supported by OpenStack4j. So set it as default.
    private static final MicroVersion DEFAULT_VERSION = LIBERTY_VERSION;

    protected BaseShareServices(MicroVersion version) {
        super(ServiceType.SHARE, version);
    }

    protected BaseShareServices() {
        this(DEFAULT_VERSION);
    }

    @Override
    protected String getApiVersionHeader() {
        return API_VERSION_HEADER;
    }
}
