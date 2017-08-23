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

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.openstack.common.GenericLink;

public interface BaymodelBuilder extends Builder<BaymodelBuilder, Baymodel> {
    /**
     * @see Baymodel#getInsecureRegistry()
     */
    BaymodelBuilder insecureRegistry(String insecureRegistry);

    /**
     * @see Baymodel#getLinks()
     */
    BaymodelBuilder links(List<GenericLink> links);

    /**
     * @see Baymodel#getHttpProxy()
     */
    BaymodelBuilder httpProxy(String httpProxy);

    /**
     * @see Baymodel#getUpdatedAt()
     */
    BaymodelBuilder updatedAt(String updatedAt);

    /**
     * @see Baymodel#getisFloatingIpEnabled()
     */
    BaymodelBuilder isFloatingIpEnabled(Boolean floatingIpEnabled);

    /**
     * @see Baymodel#getFixedSubnet()
     */
    BaymodelBuilder fixedSubnet(String fixedSubnet);

    /**
     * @see Baymodel#getMasterFlavorId()
     */
    BaymodelBuilder masterFlavorId(String masterFlavorId);

    /**
     * @see Baymodel#getUuid()
     */
    BaymodelBuilder uuid(String uuid);

    /**
     * @see Baymodel#getNoProxy()
     */
    BaymodelBuilder noProxy(String noProxy);

    /**
     * @see Baymodel#getHttpsProxy()
     */
    BaymodelBuilder httpsProxy(String httpsProxy);

    /**
     * @see Baymodel#getTlsDisabled()
     */
    BaymodelBuilder tlsDisabled(Boolean tlsDisabled);

    /**
     * @see Baymodel#getKeypairId()
     */
    BaymodelBuilder keypairId(String keypairId);

    /**
     * @see Baymodel#getPublicBaymodel()
     */
    BaymodelBuilder publicBaymodel(Boolean publicBaymodel);

    /**
     * @see Baymodel#getDockerVolumeSize()
     */
    BaymodelBuilder dockerVolumeSize(String dockerVolumeSize);

    /**
     * @see Baymodel#getServerType()
     */
    BaymodelBuilder serverType(String serverType);

    /**
     * @see Baymodel#getExternalNetworkId()
     */
    BaymodelBuilder externalNetworkId(String externalNetworkId);

    /**
     * @see Baymodel#getClusterDistro()
     */
    BaymodelBuilder clusterDistro(String clusterDistro);

    /**
     * @see Baymodel#getImageId()
     */
    BaymodelBuilder imageId(String imageId);

    /**
     * @see Baymodel#getVolumeDriver()
     */
    BaymodelBuilder volumeDriver(String volumeDriver);

    /**
     * @see Baymodel#getRegistryEnabled()
     */
    BaymodelBuilder registryEnabled(Boolean registryEnabled);

    /**
     * @see Baymodel#getDockerStorageDriver()
     */
    BaymodelBuilder dockerStorageDriver(String dockerStorageDriver);

    /**
     * @see Baymodel#getApiserverPort()
     */
    BaymodelBuilder apiserverPort(String apiserverPort);

    /**
     * @see Baymodel#getName()
     */
    BaymodelBuilder name(String name);

    /**
     * @see Baymodel#getCreatedAt()
     */
    BaymodelBuilder createdAt(String createdAt);

    /**
     * @see Baymodel#getNetworkDriver()
     */
    BaymodelBuilder networkDriver(String networkDriver);

    /**
     * @see Baymodel#getFixedNetwork()
     */
    BaymodelBuilder fixedNetwork(String fixedNetwork);

    /**
     * @see Baymodel#getCoe()
     */
    BaymodelBuilder coe(String coe);

    /**
     * @see Baymodel#getFlavorId()
     */
    BaymodelBuilder flavorId(String flavorId);

    /**
     * @see Baymodel#getMasterLbEnabled()
     */
    BaymodelBuilder masterLbEnabled(Boolean masterLbEnabled);

    /**
     * @see Baymodel#getDnsNameserver()
     */
    BaymodelBuilder dnsNameserver(String dnsNameserver);

}
