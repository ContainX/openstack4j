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
package com.huawei.openstack4j.openstack.identity.v3.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.identity.v3.Credential;
import com.huawei.openstack4j.model.identity.v3.builder.CredentialBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonRootName("credential")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneCredential implements Credential {

    private static final long serialVersionUID = 1L;
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("project_id")
    private String projectId;
    private String type;
    private String blob;
    private Map<String, String> links;

    /**
     * @return the credential builder
     */
    public static CredentialBuilder builder() {
        return new CredentialConcreteBuilder();
    }

    @Override
    public CredentialBuilder toBuilder() {
        return new CredentialConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getBlob() {
        return blob;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("userId", userId)
                .add("projectId", projectId)
                .add("type", type)
                .add("blob", blob)
                .add("links", links)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, userId, projectId, type, blob, links);
    }

    public static class CredentialConcreteBuilder implements CredentialBuilder {

        KeystoneCredential model;

        public CredentialConcreteBuilder() {
            this(new KeystoneCredential());
        }

        CredentialConcreteBuilder(KeystoneCredential model) {
            this.model = model;
        }

        @Override
        public Credential build() {
            return model;
        }

        @Override
        public CredentialBuilder from(Credential in) {
            if (in != null)
                this.model = (KeystoneCredential) in;
            return this;
        }

        @Override
        public CredentialBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public CredentialBuilder userId(String userId) {
            model.userId = userId;
            return this;
        }

        @Override
        public CredentialBuilder projectId(String projectId) {
            model.projectId = projectId;
            return this;
        }

        @Override
        public CredentialBuilder type(String type) {
            model.type = type;
            return this;
        }

        @Override
        public CredentialBuilder blob(String blob) {
            model.blob = blob;
            return this;
        }

        @Override
        public CredentialBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }
    }

    public static class Credentials extends ListResult<KeystoneCredential> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("credentials")
        protected List<KeystoneCredential> list;

        @Override
        protected List<KeystoneCredential> value() {
            return list;
        }

    }

}
