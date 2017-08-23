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
package com.huawei.openstack4j.openstack.sahara.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.sahara.ClusterTemplate;


/**
 * An OpenStack Sahara
 * Unwrap the root name of ClusterTemplate when serialized into json request 
 * 
 * @author Ekasit Kijsipongse
 */
public class SaharaClusterTemplateUnwrapped implements ModelEntity {

	private static final long serialVersionUID = 1L;
	
        @JsonUnwrapped
        //@JsonProperty("cluster_template")
	private ClusterTemplate template;

        public SaharaClusterTemplateUnwrapped(ClusterTemplate template) {
           this.template = template;
        }

        public ClusterTemplate getTemplate() { // need for serialization
           return template;
        }
}
