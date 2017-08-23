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
package com.huawei.openstack4j.openstack.sahara.domain.actions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.sahara.NodeGroup;

import com.google.common.collect.Lists;

/**
 * Simple Actions Classes used for Sahara Action 
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
//@SuppressWarnings("serial")
public final class SaharaActions {
    
    public static class ResizeNodeGroupAction implements ModelEntity {

        private static final long serialVersionUID = 1L;
        
        @JsonProperty("resize_node_groups")
        List<NodeGroup> nodeGroups; 
        
        public ResizeNodeGroupAction(String groupName,int count) { 
            NodeGroup nodeGroup = Builders.nodeGroup().name(groupName)
                                     .count(count).build();
            nodeGroups = Lists.newArrayList();
            nodeGroups.add(nodeGroup);
        }
    }
    
    public static class AddNodeGroupAction implements ModelEntity {

        private static final long serialVersionUID = 1L;
        
        @JsonProperty("add_node_groups")
        List<NodeGroup> nodeGroups; 
        
        public AddNodeGroupAction(NodeGroup nodeGroup) { 
            nodeGroups = Lists.newArrayList();
            nodeGroups.add(nodeGroup);
        }
    }
}
