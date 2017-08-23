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
package com.huawei.openstack4j.openstack.artifact.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.artifact.ArtifactService;
import com.huawei.openstack4j.api.artifact.ToscaTemplatesArtifactService;
import com.huawei.openstack4j.model.artifact.ArtifactType;
import com.huawei.openstack4j.model.artifact.Artifacts;
import com.huawei.openstack4j.model.artifact.ToscaTemplatesArtifacts;

/**
 * Created by vadavi on 18-01-2017.
 */
public class ArtifactServiceImpl implements ArtifactService {


    @Override
    public ToscaTemplatesArtifactService toscaTemplatesArtifact() {
        return Apis.get(ToscaTemplatesArtifactService.class);
    }
}
