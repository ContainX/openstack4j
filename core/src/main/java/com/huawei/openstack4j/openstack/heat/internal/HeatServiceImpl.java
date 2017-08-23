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
package com.huawei.openstack4j.openstack.heat.internal;


import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.heat.EventsService;
import com.huawei.openstack4j.api.heat.HeatService;
import com.huawei.openstack4j.api.heat.ResourcesService;
import com.huawei.openstack4j.api.heat.SoftwareConfigService;
import com.huawei.openstack4j.api.heat.StackService;
import com.huawei.openstack4j.api.heat.TemplateService;

/**
 * This class contains getters for all implementation of the available Heat services
 * 
 * @author Matthias Reisser
 */
public class HeatServiceImpl extends BaseHeatServices implements HeatService {

	@Override
	public StackService stacks() {
		return Apis.get(StackService.class);
	}

	@Override
	public TemplateService templates() {
		return Apis.get(TemplateService.class);
	}

	@Override
	public EventsService events() {
		return Apis.get(EventsService.class);
	}

	@Override
	public ResourcesService resources() {
		return Apis.get(ResourcesService.class);
	}

    @Override
    public SoftwareConfigService softwareConfig() {
        return Apis.get(SoftwareConfigService.class);
    }
	

}
