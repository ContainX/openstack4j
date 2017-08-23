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
package com.huawei.openstack4j.openstack.compute.internal;

import java.util.List;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.compute.ComputeFloatingIPService;
import com.huawei.openstack4j.api.compute.ComputeImageService;
import com.huawei.openstack4j.api.compute.ComputeSecurityGroupService;
import com.huawei.openstack4j.api.compute.ComputeService;
import com.huawei.openstack4j.api.compute.FlavorService;
import com.huawei.openstack4j.api.compute.HostAggregateService;
import com.huawei.openstack4j.api.compute.HostService;
import com.huawei.openstack4j.api.compute.KeypairService;
import com.huawei.openstack4j.api.compute.QuotaSetService;
import com.huawei.openstack4j.api.compute.ServerGroupService;
import com.huawei.openstack4j.api.compute.ServerService;
import com.huawei.openstack4j.api.compute.ServerTagService;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSService;
import com.huawei.openstack4j.api.compute.ext.HypervisorService;
import com.huawei.openstack4j.api.compute.ext.MigrationService;
import com.huawei.openstack4j.api.compute.ext.ServicesService;
import com.huawei.openstack4j.api.compute.ext.ZoneService;
import com.huawei.openstack4j.model.common.Extension;
import com.huawei.openstack4j.openstack.common.ExtensionValue.Extensions;

/**
 * Compute (Nova) Operations API implementation
 * 
 * @author Jeremy Unruh
 */
public class ComputeServiceImpl extends BaseComputeServices implements ComputeService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FlavorService flavors() {
		return Apis.get(FlavorService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeImageService images() {
		return Apis.get(ComputeImageService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServerService servers() {
		return Apis.get(ServerService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuotaSetService quotaSets() {
		return Apis.get(QuotaSetService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostService host() {
		return Apis.get(HostService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Extension> listExtensions() {
		return get(Extensions.class, uri("/extensions")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeFloatingIPService floatingIps() {
		return Apis.get(ComputeFloatingIPService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeSecurityGroupService securityGroups() {
		return Apis.get(ComputeSecurityGroupService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KeypairService keypairs() {
		return Apis.get(KeypairService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HypervisorService hypervisors() {
		return Apis.get(HypervisorService.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ZoneService zones() {
		return Apis.get(ZoneService.class);
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public MigrationService migrations() {
        return Apis.get(MigrationService.class);
    }

	@Override
	public ServerGroupService serverGroups() {
		 return Apis.get(ServerGroupService.class);
	}

    @Override
    public FloatingIPDNSService floatingIPDNS() {
        return Apis.get(FloatingIPDNSService.class);
    }

	@Override
	public HostAggregateService hostAggregates() {
		
		return Apis.get(HostAggregateService.class);
	}

	@Override
	public ServerTagService serverTags() {
		return Apis.get(ServerTagService.class);
	}
	
    @Override
    public ServicesService services() {
        return Apis.get(ServicesService.class);
    }
    
}
