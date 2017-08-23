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
package com.huawei.openstack4j.openstack.compute.internal.ext;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSDomainService;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSEntryService;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSService;

/**
 * API Service that manages the 'os-floating-ip-dns' extension
 * 
 * @author Jeremy Unruh
 */
public class FloatingIPDNSServiceImpl implements FloatingIPDNSService {

    @Override
    public FloatingIPDNSDomainService domains() {
        return Apis.get(FloatingIPDNSDomainService.class);
    }

    @Override
    public FloatingIPDNSEntryService entries() {
        return Apis.get(FloatingIPDNSEntryService.class);
    }

}
