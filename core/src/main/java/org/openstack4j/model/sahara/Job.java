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
package org.openstack4j.model.sahara;

import java.util.Date;
import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.sahara.builder.JobBuilder;

/**
 * An Openstack Sahara Job
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface Job extends ModelEntity, Buildable<JobBuilder>   {

    /**
     * @return the description of the job
     */
    String getDescription();

    /**
     * @return the tenant id of the job
     */
    String getTenantId();

    /**
     * @return the created date of the job
     */
    Date getCreatedAt();

    /**
     * @return the updated date of the job
     */
    Date getUpdatedAt();

    /**
     * @return the identifier of the job
     */
    String getId();

    /**
     * @return the name of the job
     */
    String getName();

    /**
     * @return the type of the job
     */
    String getType();
    
    /**
     * @return mains
     */
    List<? extends JobBinary> getFullMains();

    /**
     * @return libs
     */
    List<? extends JobBinary> getFullLibs();

    /**
     * @return main ids
     */
    List<String> getMains();

    /**
     * @return lib ids
     */
    List<String> getLibs();
    
    
    /**
     * reserved attribute, not support for now
     * 
     * @return is job protected
     */
    Boolean isProtected();
    
    /**
     * reserved attribute, not support for now
     * 
     * @return is job public
     */
    Boolean isPublic();
}
