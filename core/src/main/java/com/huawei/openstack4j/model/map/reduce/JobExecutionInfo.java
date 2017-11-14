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

import com.huawei.openstack4j.model.ModelEntity;

/**
 * An Info of map reduce Job Execution
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface JobExecutionInfo extends ModelEntity {

    String getStatus();

    String getExternalId();

    int getRun();

    Date getStartTime();

    Date getEndTime();

    String getAppName();

    Date getLastModTime();

    List<? extends JobExecutionAction> getActions();

    String getAcl();

    String getConsoleUrl();

    String getAppPath();

    String getToString();

    String getUser();

    String getConf();

    String getParentId();

    Date getCreatedTime();

    String getGroup();

    String getId();
}
