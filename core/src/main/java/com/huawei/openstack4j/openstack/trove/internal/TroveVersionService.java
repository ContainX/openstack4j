/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC                                       
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
package com.huawei.openstack4j.openstack.trove.internal;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.openstack.common.ServiceVersion;
import com.huawei.openstack4j.openstack.common.ServiceVersion.ServiceVersionWrap;
import com.huawei.openstack4j.openstack.common.ServiceVersion.ServiceVersions;
import com.huawei.openstack4j.openstack.common.functions.GetRootOfURL;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * 
 * Trove Version API Implementation
 * 
 * @author QianBiao.NG
 * @date   2017-07-28 16:46:41
 */
public class TroveVersionService extends BaseOpenStackService {

	public TroveVersionService() {
		super(ServiceType.DATABASE, GetRootOfURL.instance());
	}
	
	/**
	 * HuaWei Relation DataBase Service(known as Trove) validate the content-type in every request.
	 */
	protected <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
		return super.builder(returnType, path, method).header("Content-Type", CONTENT_JSON);
	}

	/**
	 * list versions of Trove Service
	 */
	public List<ServiceVersion> list() {
		return get(ServiceVersions.class, "/").execute().getList();
	}

	/**
	 * get a special version details 
	 * 
	 * @param versionId the version ID
	 * @return {@link ServiceVersion} instance
	 */
	public ServiceVersion get(String versionId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(versionId), "parameter `versionId` should not be empty");
		return get(ServiceVersionWrap.class, "/" + versionId).execute().getVersion();
	}

}
