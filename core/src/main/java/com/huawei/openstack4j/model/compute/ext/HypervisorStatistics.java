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
package com.huawei.openstack4j.model.compute.ext;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Represents a Hypervisor Statistics Entity used within the OSHypervisor extensions API
 * 
 * @author Jeremy Unruh
 */
public interface HypervisorStatistics extends ModelEntity {

	 /**
 	 * Gets the count.
 	 *
 	 * @return the count
 	 */
 	int getCount();
	 
	 /**
 	 * Gets the current workload.
 	 *
 	 * @return the current workload
 	 */
 	int getCurrentWorkload();
	 
	 /**
 	 * Gets the least available disk.
 	 *
 	 * @return the least available disk
 	 */
 	int getLeastAvailableDisk();
	 
	 /**
 	 * Gets the free disk.
 	 *
 	 * @return the free disk
 	 */
 	int getFreeDisk();
	 
	 /**
 	 * Gets the free ram.
 	 *
 	 * @return the free ram
 	 */
 	int getFreeRam();
	 
	 /**
 	 * Gets the local.
 	 *
 	 * @return the local
 	 */
 	int getLocal();
	 
	 /**
 	 * Gets the local used.
 	 *
 	 * @return the local used
 	 */
 	int getLocalUsed();
	 
	 /**
 	 * Gets the memory.
 	 *
 	 * @return the memory
 	 */
 	int getMemory();
	 
	 /**
 	 * Gets the memory used.
 	 *
 	 * @return the memory used
 	 */
 	int getMemoryUsed();
	 
	 /**
 	 * Gets the running vm.
 	 *
 	 * @return the running vm
 	 */
 	int getRunningVM();
	 
	 /**
 	 * Gets the virtual cpu.
 	 *
 	 * @return the virtual cpu
 	 */
 	int getVirtualCPU();
	 
	 /**
 	 * Gets the virtual used cpu.
 	 *
 	 * @return the virtual used cpu
 	 */
 	int getVirtualUsedCPU();
}
