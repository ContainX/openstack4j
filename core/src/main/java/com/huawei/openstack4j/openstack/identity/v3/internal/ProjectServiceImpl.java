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
package com.huawei.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.identity.v3.ProjectService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Project;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneProject;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneProject.Projects;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class ProjectServiceImpl extends BaseOpenStackService implements ProjectService  {

	@Override
	public Project create(Project project) {
		checkNotNull(project);
		return post(KeystoneProject.class, PATH_PROJECTS).entity(project).execute();
	}

    @Override
    public Project create(String domainId, String name, String description, boolean enabled) {
        checkNotNull(domainId);
        checkNotNull(name);
        checkNotNull(description);
        checkNotNull(enabled);
        return create(KeystoneProject.builder().domainId(domainId).name(name).description(description).enabled(enabled).build());
    }

    @Override
    public Project get(String projectId) {
        checkNotNull(projectId);
        return get(KeystoneProject.class, PATH_PROJECTS, "/", projectId).execute();
    }

    @Override
    public List<? extends Project> getByName(String projectName) {
        checkNotNull(projectName);
        return get(Projects.class, uri(PATH_PROJECTS)).param("name", projectName).execute().getList();
    }

    @Override
    public Project getByName(String projectName, String domainId) {
        checkNotNull(projectName);
        checkNotNull(domainId);
        return get(Projects.class, uri(PATH_PROJECTS)).param("name", projectName).param("domain_id", domainId).execute().first();
    }

    @Override
    public Project update(Project project) {
        checkNotNull(project);
        return patch(KeystoneProject.class, PATH_PROJECTS, "/", project.getId()).entity(project).execute();
    }

    @Override
    public ActionResponse delete(String projectId) {
        checkNotNull(projectId);
        return deleteWithResponse(PATH_PROJECTS, "/", projectId).execute();
    }

    @Override
    public List<? extends Project> list() {
        return get(Projects.class, uri(PATH_PROJECTS)).execute().getList();
    }

}
