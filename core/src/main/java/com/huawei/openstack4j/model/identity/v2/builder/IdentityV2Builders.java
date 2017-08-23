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
package com.huawei.openstack4j.model.identity.v2.builder;

/**
 * The Identity V2 builders
 */
public interface IdentityV2Builders {

    /**
     * The builder to create an Endpoint.
     *
     * @return the endpoint builder
     */
    public EndpointBuilder endpoint();

    /**
     * The builder to create a Role.
     *
     * @return the role builder
     */
    public RoleBuilder role();

    /**
     * The builder to create a service.
     *
     * @return the service builder
     */
    public ServiceBuilder service();

    /**
     * The builder to create a Service Endpoint.
     *
     * @return the service endpoint builder
     */
    public ServiceEndpointBuilder serviceEndpoint();

    /**
     * The builder to create a tenant
     *
     * @return the tenant builder
     */
    public TenantBuilder tenant();

    /**
     * The builder to create a user
     *
     * @return the user builder
     */
    public UserBuilder user();

}
