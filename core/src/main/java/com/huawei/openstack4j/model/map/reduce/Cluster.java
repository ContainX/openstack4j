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
package com.huawei.openstack4j.model.map.reduce;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.map.reduce.builder.ClusterBuilder;

/**
 * An OpenStack Cluster
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface Cluster extends ModelEntity, Buildable<ClusterBuilder> {

    enum Status {
        /* 
         * Since it is being developed, this list is not stable yet. Note 
         *  also that MapReduce cluster status may appear in 2 words, e.g. 
         *  "Adding Instances", but we match only the first word for simplicity 
         *  until the list is stable. 
         *  See http://docs.openstack.org/developer/sahara/userdoc/statuses.html for more info.
         */
        UNRECOGNIZED, VALIDATING, INFRAUPDATING, SPAWNING, WAITING, PREPARING, CONFIGURING, STARTING, ACTIVE, SCALING, ADDING, DECOMMISSIONING, DELETING, ERROR; 
        @JsonCreator
        public static Status forValue(String value) {
            if (value != null)
            {
                for (Status s : Status.values()) {
                    if (value.toUpperCase().startsWith(s.name()))
                        return s;
                }
            }
            return Status.UNRECOGNIZED;
        }
    }


    /**
     * @return the status of the cluster
     */
    Status getStatus();

    /**
     * @return the information of the cluster
     */
    Map<String, ? extends ServiceInfo> getInfos();

    /**
     * @return the template id of the cluster
     */
    String getClusterTemplateId();

    /**
     * @return the if the cluster is transient 
     */
    Boolean isTransient();

    /**
     * @return the description of the cluster
     */
    String getDescription();

    /**
     * @return the configurations of the cluster
     */
    Map<String,? extends ServiceConfig> getClusterConfigs();

    /**
     * @return the created date of the cluster
     */
    Date getCreatedAt();

    /**
     * @return the default image id of the cluster
     */
    String getDefaultImageId();

    /**
     * @return the user keypair id of the cluster
     */
    String getUserKeypairId();

    /**
     * @return the updated date of the cluster
     */
    Date getUpdatedAt();

    /**
     * @return the plugin name of the cluster
     */
    String getPluginName();

    /**
     * @return the management network of the cluster
     */
    String getManagementNetworkId();

    /**
     * @return the anti-affinity of the cluster
     */
    List<String> getAntiAffinity();

    /**
     * @return the tenant id of the cluster
     */
    String getTenantId();

    /**
     * @return the node groups of the cluster
     */
    List<? extends NodeGroup> getNodeGroups();

    /**
     * @return the management public key of the cluster
     */
    String getManagementPublicKey();

    /**
     * @return the status description of the cluster
     */
    String getStatusDescription();

    /**
     * @return the hadoop version of the cluster
     */
    String getHadoopVersion();

    /**
     * @return the identifier of the cluster
     */
    String getId();

    /**
     * @return the trust id of the cluster
     */
    String getTrustId();

    /**
     * @return the name of the cluster
     */
    String getName();

}
