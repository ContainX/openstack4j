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

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Represents a Hypervisor details Entity used within the OSHypervisor extensions API
 *
 * @author Jeremy Unruh
 */
public interface Hypervisor extends ModelEntity {

    /**
     * @return the unique identifier representing this hypervisor
     */
    String getId();

    /**
     * Gets the current workload.
     *
     * @return the current workload
     */
    int getCurrentWorkload();

    /**
     * Gets the least disk available.
     *
     * @return the least disk available
     */
    int getLeastDiskAvailable();

    /**
     * Gets free disk space in GB
     *
     * @return the free disk in GB
     */
    int getFreeDisk();

    /**
     * Gets the free RAM in MB
     *
     * @return the free RAM in MB
     */
    int getFreeRam();

    /**
     * Gets the hostname of the hypervisor node.
     *
     * @return the DNS hostname of the hypervisor
     */
    String getHypervisorHostname();

    /**
     * Gets the host IP address
     *
     * @return the host IP address
     */
    String getHostIP();

    /**
     * Gets the type of the hypervisor.  For example "QEMU"
     *
     * @return the hypervisor implementation type
     */
    String getType();

    /**
     * Gets the version of the hypervisor
     *
     * @return the version
     */
    int getVersion();

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

    /**
     * Gets the local disk.
     *
     * @return the local disk
     */
    int getLocalDisk();

    /**
     * Gets the local disk used.
     *
     * @return the local disk used
     */
    int getLocalDiskUsed();

    /**
     * Gets the local memory.
     *
     * @return the local memory
     */
    int getLocalMemory();

    /**
     * Gets the local memory used.
     *
     * @return the local memory used
     */
    int getLocalMemoryUsed();

    /**
     * Gets the service.
     *
     * @return the service
     */
    Service getService();


    /**
     * Gets the cpu info.
     *
     * @return CPUInfo
     */
    CPUInfo getCPUInfo();

    /**
     * <br/>Description:Gets the host status
     * <p>Author:Wang Ting/王婷</p>
     *
     * @return the host status
     */
    String getStatus();

    /**
     * Gets the host state
     * <p>Author:Wang Ting/王婷</p>
     *
     * @return the host state
     */
    String getState();

    /**
     * The Hypervisor Services Detail
     */
    public interface Service extends ModelEntity {

        /**
         * Gets the host.
         *
         * @return the host
         */
        String getHost();

        /**
         * Gets the id.
         *
         * @return the id
         */
        String getId();

    }

    public interface CPUInfo extends ModelEntity {

        /**
         * @return cpu vendor
         */
        List<Object> getVendor();

        /**
         * @return cpu model
         */
        List<Object> getModel();

        /**
         * @return chipset architecture
         */
        String getArch();

        /**
         * @return cpu feature set
         */
        List<String> getFeatures();


        /**
         * @return cpu topology
         */
        CPUTopology getTopology();
    }

    public interface CPUTopology extends ModelEntity {

        /**
         * @return core count
         */
        int getCores();


        /**
         * @return thread count
         */
        int getThreads();

        /**
         * @return socket count
         */
        int getSockets();
    }
}
