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

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.artifact.ToscaTemplatesArtifactService;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.artifact.ArtifactType;
import com.huawei.openstack4j.model.artifact.ArtifactUpdate;
import com.huawei.openstack4j.model.artifact.ToscaTemplatesArtifact;
import com.huawei.openstack4j.model.artifact.ToscaTemplatesArtifacts;
import com.huawei.openstack4j.model.artifact.builder.ArtifactUpdateBuilder;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.common.payloads.FilePayload;
import com.huawei.openstack4j.openstack.artifact.domain.ArtifactUpdateModel;
import com.huawei.openstack4j.openstack.artifact.domain.ToscaTemplates;
import com.huawei.openstack4j.openstack.artifact.domain.ToscaTemplatesList;
import com.huawei.openstack4j.openstack.common.ListEntity;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadavi on 19-01-2017.
 */
public class ToscaTemplatesArtifactServiceImpl extends BaseArtifactServiceImpl implements ToscaTemplatesArtifactService {

    public ToscaTemplatesArtifactServiceImpl() {
        super(ArtifactType.TOSCA_TEMPLATES);
    }

    @Override
    public ToscaTemplatesArtifacts list() {

        return super.list(ToscaTemplatesList.class);

    }

    @Override
    public ToscaTemplatesArtifact get(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return super.get(artifactId, ToscaTemplates.class);
    }

    @Override
    public ToscaTemplatesArtifact create(ToscaTemplatesArtifact toscaTemplatesArtifact) {
        Preconditions.checkNotNull(toscaTemplatesArtifact);
        return super.create(toscaTemplatesArtifact, ToscaTemplates.class);
    }

    @Override
    public ToscaTemplatesArtifact upload(String artifactId, File file) {
        Preconditions.checkNotNull(artifactId);
        return super.upload(artifactId, file, ToscaTemplates.class, "template");
    }

    @Override
    public InputStream download(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return super.download(artifactId, "template");
    }

    @Override
    public ActionResponse delete(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return super.delete(artifactId);
    }

    @Override
    public ToscaTemplatesArtifact update(String artifactId, List<ArtifactUpdate> artifactUpdates) {
        Preconditions.checkNotNull(artifactId);
        Preconditions.checkNotNull(artifactUpdates);
        return super.update(artifactId, artifactUpdates, ToscaTemplates.class);
    }

    @Override
    public ToscaTemplatesArtifact activate(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return update(artifactId, "replace", "/status", "active", ToscaTemplates.class);
    }

    @Override
    public ToscaTemplatesArtifact deactivate(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return update(artifactId, "replace", "/status", "deactivated", ToscaTemplates.class);
    }

    @Override
    public ToscaTemplatesArtifact reactivate(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return update(artifactId, "replace", "/status", "active", ToscaTemplates.class);
    }

    @Override
    public ToscaTemplatesArtifact publish(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return update(artifactId, "replace", "/visibility", "public", ToscaTemplates.class);
    }

}
