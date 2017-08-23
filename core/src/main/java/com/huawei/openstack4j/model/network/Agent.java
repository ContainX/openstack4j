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
package com.huawei.openstack4j.model.network;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.builder.AgentBuilder;

/**
 * This class represents a neutron agent
 *
 * @author Yin Zhang
 */
public interface Agent extends ModelEntity, Buildable<AgentBuilder> {
    /**
     * neutron agent type
     */
    public enum Type {
        DHCP("DHCP agent"), L3("L3 agent"), OPEN_VSWITCH("Open vSwitch agent"), METADATA("Metadata agent"), UNRECOGNIZED(
                "");

        private String mValue;

        Type(String value) {
            mValue = value;
        }

        @JsonCreator
        public static Type forValue(String value) {
            if (value != null) {
                for (Type s : Type.values()) {
                    if (s.value().equalsIgnoreCase(value)) {
                        return s;
                    }
                }
            }
            return Type.UNRECOGNIZED;
        }

        public String value() {
            return mValue;
        }
    }

    /**
     * @return the admin state up for the agent
     */
    boolean getAdminStateUp();

    /**
     * @return the type of the agent
     */
    Type getAgentType();

    /**
     * @return true of the agent is alive, otherwise return false
     */
    boolean getAlive();

    /**
     * @return the binary for the agent
     */
    String getBinary();

    /**
     * @return the created time stamp of the agent
     */
    Date getCreatedAt();

    /**
     * @return the description for the agent
     */
    String getDescription();

    /**
     * @return the heartbeat timestamp for the agent
     */
    Date getHeartbeatTimestamp();

    /**
     * @return the host of the agent
     */
    String getHost();

    /**
     * @return the ID of the agent
     */
    String getId();

    /**
     * @return the start time stamp of the agent
     */
    Date getStartedAt();

    /**
     * @return the topic of the agent
     */
    String getTopic();
}
