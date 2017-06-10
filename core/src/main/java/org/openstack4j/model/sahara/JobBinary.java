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

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.sahara.builder.JobBinaryBuilder;

/**
 * An Openstack Sahara Job Binary
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface JobBinary extends ModelEntity, Buildable<JobBinaryBuilder>  {

    /**
     * @return the description of the job binary
     */
    String getDescription();

    /**
     * @return the URL of the job binary
     */
    String getURL();

    /**
     * @return the tenant id of the job binary
     */
    String getTenantId();

    /**
     * @return the created date of the job binary
     */
    Date getCreatedAt();

    /**
     * @return the updated date of the job binary
     */
    Date getUpdatedAt();

    /**
     * @return the identifier of the job binary
     */
    String getId();

    /**
     * @return the name of the job binary
     */
    String getName();

    /**
     * @return the credentials of the job binary
     */
    JobBinaryCredentials getCredentials();
}
