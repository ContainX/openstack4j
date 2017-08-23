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

/**
 * an v3 auth object
 * 
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#authenticate">API reference</a>
 */
public abstract class Auth implements com.huawei.openstack4j.openstack.common.Auth {

    private static final long serialVersionUID = 1L;

    private String projectId;
    private String projectName;
    private String domainId;
    private String domainName;

    private Type type;

    protected Auth(Type type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Type getType() {
        return type;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
