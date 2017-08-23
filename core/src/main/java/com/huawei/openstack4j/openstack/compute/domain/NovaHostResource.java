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
package com.huawei.openstack4j.openstack.compute.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.HostResource;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * Nova Host Resource describes the capacity and capability of a compute host that provides compute service
 *
 * @author Qin An
 * @author Elina Meier
 */
@JsonRootName("resource")
public class NovaHostResource implements HostResource {

    public static final long serialVersionUID = 1L;

    private int cpu;
	@JsonProperty("disk_gb")
    private int diskGb;
    private String host;
	@JsonProperty("memory_mb")
    private int memoryMb;
    private String project;
    @JsonProperty("host_name")
	public String hostName;
	public String service;
	public String zone;

    /**
	 * {@inheritDoc}
	 */
	@Override
    public int getCpu() {
        return cpu;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getDiskInGb() {
        return diskGb;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public String getHost() {
        return host;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getMemoryInMb() {
        return memoryMb;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public String getProject() {
        return project;
    }

	/**
	 * <p>Author:Wang Ting/王婷</p>
	 * @Title: getService
	 * @return
	 * @see com.huawei.openstack4j.model.compute.Host#getService()
	 */
	@Override
	public String getService() {
		return service;
	}

	/**
	 * <p>Author:Wang Ting/王婷</p>
	 * @Title: getZone
	 * @return
	 * @see com.huawei.openstack4j.model.compute.Host#getZone()
	 */
	@Override
	public String getZone() {
		return zone;
	}

	/**
	 * <p>Author:Wang Ting/王婷</p>
	 * @Title: getHostName
	 * @return
	 * @see com.huawei.openstack4j.model.compute.Host#getHostName()
	 */
	@Override
	public String getHostName() {
		return hostName;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("cpu", cpu)
				.add("diskGb", diskGb)
				.add("host", host)
				.add("memoryMb", memoryMb)
				.add("project", project)
				.add("hostName", hostName)
				.add("service", service)
				.add("zone", zone).toString();
	}

	/**
	 * host aggregates
	 * @author Wang Ting/王婷
	 *
	 */
	public static class NovaHostResources extends ListResult<NovaHostResource> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("hosts")
		private List<NovaHostResource> hosts;
		/**
		 * NovaHosts
		 */
		public List<NovaHostResource> value() {
			return hosts;
		}

		@Override
		public String toString() {
			return "NovaHostResources [hosts=" + hosts + "]";
		}

	}

}
