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
package com.huawei.openstack4j.model.identity;

import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.common.resolvers.LatestServiceVersionResolver;
import com.huawei.openstack4j.model.common.resolvers.ServiceVersionResolver;
import com.huawei.openstack4j.model.common.resolvers.StableServiceVersionResolver;
import com.huawei.openstack4j.model.identity.v2.Access;
import com.huawei.openstack4j.model.identity.v3.Token;

/**
 * Dynamic parameters used for URL resolution with Endpoints
 *
 * @author Jeremy Unruh
 */
public class URLResolverParams {

    public Token token;
    public ServiceType type;
    public String region;
    public Facing perspective;
    private ServiceVersionResolver resolver;
    public Access access;

    private URLResolverParams(Token token, ServiceType type) {
        this.token = token;
        this.type = (type == null) ? ServiceType.IDENTITY : type;
    }

    public static URLResolverParams create(Token token, ServiceType type) {
        return new URLResolverParams(token, type);
    }

    public static URLResolverParams create(Access access, ServiceType type) {
        return new URLResolverParams(access, type);
    }

    private URLResolverParams(Access access, ServiceType type) {
        this.access = access;
        this.type = (type == null) ? ServiceType.IDENTITY : type;
    }

    public URLResolverParams region(String region) {
        this.region = region;
        return this;
    }

    public URLResolverParams perspective(Facing perspective) {
        this.perspective = perspective;
        return this;
    }

    public URLResolverParams resolver(ServiceVersionResolver resolver) {
        this.resolver = resolver;
        return this;
    }

    public ServiceVersionResolver getResolver() {
        return (resolver != null) ? resolver : LatestServiceVersionResolver.INSTANCE;
    }

    public ServiceVersionResolver getV2Resolver() {
        return (resolver != null) ? resolver : StableServiceVersionResolver.INSTANCE;
    }

}
