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
package com.huawei.openstack4j.model.artifact.builder;

import java.util.List;

import com.huawei.openstack4j.model.artifact.Metadata;

/**
 * A Builder which creates a Artifact
 *
 * @author Pavan Vadavi
 */
public interface ArtifactBuilder {

    ArtifactBuilder description(String description);

    ArtifactBuilder tags(List<Object> tags);

    ArtifactBuilder id(String id);

    ArtifactBuilder name(String name);

    ArtifactBuilder metadata(Metadata metadata);

    ArtifactBuilder release(List<Object> release);

    ArtifactBuilder owner(String owner);

    ArtifactBuilder supportedBy(Object supportedBy);

    ArtifactBuilder licenseUrl(Object licenseUrl);

    ArtifactBuilder version(String version);

    ArtifactBuilder providedBy(Object providedBy);

    ArtifactBuilder visibility(String visibility);

    ArtifactBuilder updatedAt(String updatedAt);

    ArtifactBuilder activatedAt(String activatedAt);

    ArtifactBuilder createdAt(String createdAt);

    ArtifactBuilder license(Object license);

    ArtifactBuilder icon(Object icon);

    ArtifactBuilder status(String status);

}
