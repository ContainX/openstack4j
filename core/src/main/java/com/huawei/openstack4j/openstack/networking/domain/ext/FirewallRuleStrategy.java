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
package com.huawei.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * Inserts a firewall rule in a firewall policy relative to the position of other rules.
 * 
 * @author Vishvesh Deshmukh
 */
public class FirewallRuleStrategy implements ModelEntity {
	
	/**
	 * Used to dictate insert strategy during Inserting a Firewall Rule in a Firewall Policy.
	 * @author Vishvesh Deshmukh
	 */
	public enum RuleInsertStrategyType {
		BEFORE, AFTER
	}

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("firewall_rule_id")
    private String firewallRuleId;
    
    @JsonProperty("insert_after")
    private String insertAfterRuleId;
    
    @JsonProperty("insert_before")
    private String insertBeforeRuleId;

    public static FirewallRuleStrategy create(String firewallRuleId, RuleInsertStrategyType type, String insertAfterOrBeforeRuleId) {
    	FirewallRuleStrategy action = new FirewallRuleStrategy();
    	action.firewallRuleId = firewallRuleId;
        if (type == RuleInsertStrategyType.BEFORE)
            action.insertBeforeRuleId = insertAfterOrBeforeRuleId;
        else
            action.insertAfterRuleId = insertAfterOrBeforeRuleId;
        return action;
    }
    
    public static FirewallRuleStrategy remove(String firewallRuleId) {
    	FirewallRuleStrategy action = new FirewallRuleStrategy();
    	action.firewallRuleId = firewallRuleId;
    	return action;
    }
    
    @JsonIgnore
    public String getFirewallRuleId() {
		return firewallRuleId;
	}
    
    @JsonIgnore
    public String getInsertAfterRuleId() {
		return insertAfterRuleId;
	}
    
    @JsonIgnore
    public String getInsertBeforeRuleId() {
		return insertBeforeRuleId;
	}
}
