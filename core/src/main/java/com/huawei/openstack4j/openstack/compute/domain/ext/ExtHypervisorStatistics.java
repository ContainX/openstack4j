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
package com.huawei.openstack4j.openstack.compute.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.ext.HypervisorStatistics;

import com.google.common.base.MoreObjects;

@JsonRootName("hypervisor_statistics")
public class ExtHypervisorStatistics implements HypervisorStatistics {

	private static final long serialVersionUID = 1L;

	@JsonProperty("count")
	int count;
	@JsonProperty("current_workload")
	int currentWorkload;
	@JsonProperty("disk_available_least")
	int leastDiskAvail;
	@JsonProperty("free_disk_gb")
	int freeDisk;
	@JsonProperty("free_ram_mb")
	int freeRam;
	@JsonProperty("local_gb")
	int local;
	@JsonProperty("local_gb_used")
	int localUsed;
	@JsonProperty("memory_mb")
	int memory;
	@JsonProperty("memory_mb_used")
	int memoryUsed;
	@JsonProperty("running_vms")
	int running;
	@JsonProperty("vcpus")
	int vcpus;
	@JsonProperty("vcpus_used")
	int vcpusUsed;

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int getCurrentWorkload() {
		return currentWorkload;
	}

	@Override
	public int getLeastAvailableDisk() {
		return leastDiskAvail;
	}

	@Override
	public int getFreeDisk() {
		return freeDisk;
	}

	@Override
	public int getFreeRam() {
		return freeRam;
	}

	@Override
	public int getLocal() {
		return local;
	}

	@Override
	public int getLocalUsed() {
		return localUsed;
	}

	@Override
	public int getMemory() {
		return memory;
	}

	@Override
	public int getMemoryUsed() {
		return memoryUsed;
	}

	@Override
	public int getRunningVM() {
		return running;
	}

	@Override
	public int getVirtualCPU() {
		return vcpus;
	}

	@Override
	public int getVirtualUsedCPU() {
		return vcpusUsed;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(HypervisorStatistics.class)
				.add("count", count).add("current_workload", currentWorkload).add("least_disk_avail", leastDiskAvail)
				.add("freeRam", freeRam).add("freeDisk", freeDisk).add("local", local).add("local_used", localUsed)
				.add("memory", memory).add("memory_used", memoryUsed).add("running_vms", running).add("vcspus", vcpus)
				.add("vcpus_used", vcpusUsed)
				.toString();
	}

}
