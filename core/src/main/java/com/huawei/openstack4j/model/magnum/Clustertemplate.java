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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.GenericLink;

public interface Clustertemplate extends ModelEntity, Buildable<ClustertemplateBuilder> {
    /**
     * Gets insecureRegistry
     * 
     * @return insecureRegistry
     */
    String getInsecureRegistry();

    /**
     * Gets links
     * 
     * @return links
     */
    List<GenericLink> getLinks();

    /**
     * Gets httpProxy
     * 
     * @return httpProxy
     */
    String getHttpProxy();

    /**
     * Gets updatedAt
     * 
     * @return updatedAt
     */
    String getUpdatedAt();

    /**
     * Gets floatingIpEnabled
     * 
     * @return floatingIpEnabled
     */
    Boolean getFloatingIpEnabled();

    /**
     * Gets fixedSubnet
     * 
     * @return fixedSubnet
     */
    String getFixedSubnet();

    /**
     * Gets masterFlavorId
     * 
     * @return masterFlavorId
     */
    String getMasterFlavorId();

    /**
     * Gets uuid
     * 
     * @return uuid
     */
    String getUuid();

    /**
     * Gets noProxy
     * 
     * @return noProxy
     */
    String getNoProxy();

    /**
     * Gets httpsProxy
     * 
     * @return httpsProxy
     */
    String getHttpsProxy();

    /**
     * Gets tlsDisabled
     * 
     * @return tlsDisabled
     */
    Boolean getTlsDisabled();

    /**
     * Gets keypairId
     * 
     * @return keypairId
     */
    String getKeypairId();

    /**
     * Gets publicTemplate
     * 
     * @return publicTemplate
     */
    Boolean getPublicTemplate();

    /**
     * Gets labels
     * 
     * @return labels
     */
    Label getLabels();

    /**
     * Gets dockerVolumeSize
     * 
     * @return dockerVolumeSize
     */
    Integer getDockerVolumeSize();

    /**
     * Gets serverType
     * 
     * @return serverType
     */
    String getServerType();

    /**
     * Gets externalNetworkId
     * 
     * @return externalNetworkId
     */
    String getExternalNetworkId();

    /**
     * Gets clusterDistro
     * 
     * @return clusterDistro
     */
    String getClusterDistro();

    /**
     * Gets imageId
     * 
     * @return imageId
     */
    String getImageId();

    /**
     * Gets volumeDriver
     * 
     * @return volumeDriver
     */
    String getVolumeDriver();

    /**
     * Gets registryEnabled
     * 
     * @return registryEnabled
     */
    Boolean getRegistryEnabled();

    /**
     * Gets dockerStorageDriver
     * 
     * @return dockerStorageDriver
     */
    String getDockerStorageDriver();

    /**
     * Gets apiserverPort
     * 
     * @return apiserverPort
     */
    String getApiserverPort();

    /**
     * Gets name
     * 
     * @return name
     */
    String getName();

    /**
     * Gets createdAt
     * 
     * @return createdAt
     */
    String getCreatedAt();

    /**
     * Gets networkDriver
     * 
     * @return networkDriver
     */
    String getNetworkDriver();

    /**
     * Gets fixedNetwork
     * 
     * @return fixedNetwork
     */
    String getFixedNetwork();

    /**
     * Gets coe
     * 
     * @return coe
     */
    String getCoe();

    /**
     * Gets flavorId
     * 
     * @return flavorId
     */
    String getFlavorId();

    /**
     * Gets masterLbEnabled
     * 
     * @return masterLbEnabled
     */
    Boolean getMasterLbEnabled();

    /**
     * Gets dnsNameserver
     * 
     * @return dnsNameserver
     */
    String getDnsNameserver();

}
