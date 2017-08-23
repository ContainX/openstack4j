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
package com.huawei.openstack4j.model.murano.v1.domain;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * @author Nikolay Mahotkin.
 */
public interface Report extends ModelEntity {
    /**
     *
     * @return entity.
     */
    String getEntity();

    /**
     *
     * @return entity id.
     */
    String getEntityId();

    /**
     *
     * @return report level.
     */
    String getLevel();

    /**
     *
     * @return Created date.
     */
    String getCreated();

    /**
     *
     * @return Updated date.
     */
    String getUpdated();

    /**
     *
     * @return report text.
     */
    String getText();

    /**
     *
     * @return task id which deployment belongs to.
     */
    String getTaskId();

    /**
     *
     * @return id of the report.
     */
    String getId();

    /**
     *
     * @return report details.
     */
    String getDetails();
}
