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
package com.huawei.openstack4j.model.compute.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.compute.QuotaSetUpdate;

/**
 * A builder which creates a QuotaSetUpdate object
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSetUpdateBuilder extends Builder<QuotaSetUpdateBuilder, QuotaSetUpdate> {
    
    /**
     * Metadata items permitted
     */
    QuotaSetUpdateBuilder metadataItems(int metadataitems);

    /**
     * Injected file maximum length
     */
    QuotaSetUpdateBuilder injectedFileContentBytes(int injectedFileContentBytes);

    /**
     * Number of inject-able files
     */
    QuotaSetUpdateBuilder injectedFiles(int injectedFiles);

    /**
     * Quantity of instanceable RAM (MBytes)
     */
    QuotaSetUpdateBuilder ram(int ram);

    /**
     * Number of floating IP
     */
    QuotaSetUpdateBuilder floatingIps(int floatingIps);

    /**
     * Number of permitted instances
     */
    QuotaSetUpdateBuilder instances(int instances);

    /**
     * Number of instanceable cores
     */
    QuotaSetUpdateBuilder cores(int cores);

    /**
     * Number of security groups permitted
     */
    QuotaSetUpdateBuilder securityGroups(int securityGroups);

    /**
     * Number of rules per security group permitted
     */
    QuotaSetUpdateBuilder securityGroupRules(int securityGroupRules);

    /**
     * Injected file path name maximum length
     */
    QuotaSetUpdateBuilder injectedFilePathBytes(int injectedFilePathBytes);

    /**
     * Number of keypairs
     */
    QuotaSetUpdateBuilder keyPairs(int keyPairs);

}
