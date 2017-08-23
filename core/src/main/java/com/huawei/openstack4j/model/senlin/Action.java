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
package com.huawei.openstack4j.model.senlin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Action.
 * All getters map to the possible return values of
 * <code> GET /v1/actions/​{action_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Action extends ResourceEntity {

    /**
     * Returns the action name of the action
     *
     * @return the action name of the action
     */
    String getAction();

    /**
     * Returns the context of the action
     *
     * @return the context of the action
     */
    Map<String, Object> getContext();

    /**
     * Returns the action name of the action
     *
     * @return the action name of the action
     */
    Date getUpdatedAt();

    /**
     * Returns the action name of the action
     *
     * @return the action name of the action
     */
    String getCause();

    /**
     * Returns the action name of the action
     *
     * @return the action name of the action
     */
    Date getCreatedAt();

    /**
     * Returns the depended by of the action
     *
     * @return the depended by of the action
     */
    List<String> getDependedBy();

    /**
     * Returns the depends on of the action
     *
     * @return the depends on of the action
     */
    List<String> getDependedOn();

    /**
     * Returns the end time of the action
     *
     * @return the end time of the action
     */
    Float getEndTime();

    /**
     * Returns the inputs of the action
     *
     * @return the inputs of the action
     */
    Map<String, Object> getInputs();

    /**
     * Returns the interval of the action
     *
     * @return the interval of the action
     */
    Integer getInterval();

    /**
     * Returns the outputs of the action
     *
     * @return the outputs of the action
     */
    Map<String, Object> getOutputs();

    /**
     * Returns the owner of the action
     *
     * @return the owner of the action
     */
    String getOwner();

    /**
     * Returns the start time of the action
     *
     * @return the start time of the action
     */
    Float getStartTime();

    /**
     * Returns the status of the action
     *
     * @return the status of the action
     */
    String getStatus();

    /**
     * Returns the status reason of the action
     *
     * @return the status reason of the action
     */
    String getStatusReason();

    /**
     * Returns the target of the action
     *
     * @return the target name of the action
     */
    String getTarget();

    /**
     * Returns the timeout value of the action
     *
     * @return the timeout value of the action
     */
    Integer getTimeout();
}
