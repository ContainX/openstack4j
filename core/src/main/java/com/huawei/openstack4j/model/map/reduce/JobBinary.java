/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC tld                        
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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.map.reduce.builder.JobBinaryBuilder;

/**
 * An Openstack map reduce Job Binary (X)
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
    
    /**
     * reserved attribute, not support for now
     * 
     * @return is data source protected
     */
    Boolean isProtected();
    
    /**
     * reserved attribute, not support for now
     * 
     * @return is data source public
     */
    Boolean isPublic();
}
