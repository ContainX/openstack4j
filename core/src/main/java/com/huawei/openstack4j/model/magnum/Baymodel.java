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
package com.huawei.openstack4j.model.magnum;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.GenericLink;

public interface Baymodel extends ModelEntity, Buildable<BaymodelBuilder> {
    /**
     * Insecure registry
     * @return InsecureRegistry
     */
    String getInsecureRegistry(); 
    
    /**
     * All links
     * @return links
     */
    List<GenericLink> getLinks();
    
    /**
     * Http proxy
     * @return httpProxy
     */
    String getHttpProxy();
    
    /**
     * Updated time
     * @return updatedAt
     */
    String getUpdatedAt(); 
    
    /**
     * Is Floating IP enabled
     * @return floatingIpEnabled
     */
    Boolean isFloatingIpEnabled();
    
    /**
     * Fixed subnet
     * @return fixedSubnet
     */
    String getFixedSubnet();
    
    /**
     * Master flavor id
     * @return masterFlavorId
     */
    String getMasterFlavorId(); 
    
    /**
     * UUID
     * @return uuid
     */
    String getUuid(); 
    
    /**
     * No proxy
     * @return noProxy
     */
    String getNoProxy(); 
    
    /**
     * Http proxy
     * @return http proxy
     */
    String getHttpsProxy(); 
    
    /**
     * Is TLS disabled
     * @return tlsDisabled boolean value
     */
    Boolean isTlsDisabled(); 
    
    /**
     * Keypair id
     * @return keypairId
     */
    String getKeypairId(); 
    
    /**
     * Is it public
     * @return public boolean vallue
     */
    Boolean isPublicBaymodel(); 
    
    /**
     * Docker volume size
     * @return dockerVolumeSize
     */
    String getDockerVolumeSize();
    
    /**
     * Server type
     * @return serverType
     */
    String getServerType();
    
    /**
     * External network id
     * @return externalNetworkId
     */
    String getExternalNetworkId();
    
    /**
     * Cluster distro
     * @return clusterDistro
     */
    String getClusterDistro();
    
    /**
     * Imdage id
     * @return imageId
     */
    String getImageId(); 
    
    /**
     * Volume driver
     * @return volume driver
     */
    String getVolumeDriver(); 
    
    /**
     * Is registry enabled
     * @return registryEnabled boolean value
     */
    Boolean isRegistryEnabled(); 
    
    /**
     * Docker storage driver
     * @return dockerStorageDriver
     */
    String getDockerStorageDriver();
    
    /**
     * Api server port
     * @return apiserverPort
     */
    String getApiserverPort();
    
    /**
     * Name
     * @return name
     */
    String getName(); 
    
    /**
     * Date of creation
     * @return createdAt
     */
    String getCreatedAt(); 
    
    /**
     * Network driver
     * @return networkDriver
     */
    String getNetworkDriver(); 
    
    /**
     * Fixed network
     * @return fixedNetwork
     */
    String getFixedNetwork(); 
    
    /**
     * Coe
     * @return coe
     */
    String getCoe();
    
    /**
     * Flavor id
     * @return flavorId
     */
    String getFlavorId(); 
    
    /**
     * Is master lb enabled
     * @return masterLbEnabled boolean value
     */
    Boolean isMasterLbEnabled(); 
    
    /**
     * DNS name server
     * @return dnsNameServer
     */
    String getDnsNameserver();   
}
