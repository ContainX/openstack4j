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
package org.openstack4j.openstack.trove.internal;

import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.ServiceVersion;
import org.openstack4j.openstack.common.ServiceVersion.ServiceVersionWrap;
import org.openstack4j.openstack.common.ServiceVersion.ServiceVersions;
import org.openstack4j.openstack.common.functions.GetRootOfURL;

/**
 * 
 * Trove Version API Implementation
 * 
 * @author QianBiao.NG
 * @date   2017-07-28 16:46:41
 */
public class VersionServiceImpl extends BaseTroveServices {

	public VersionServiceImpl() {
		super(ServiceType.DATABASE, GetRootOfURL.instance());
	}

	/**
	 * list versions of Trove Service
	 */
	public List<ServiceVersion> list() {
		return get(ServiceVersions.class, "/rds/").execute().getList();
	}

	/**
	 * get a special version details 
	 * 
	 * @param versionId the version ID
	 * @return {@link ServiceVersion} instance
	 */
	public ServiceVersion get(String versionId) {
		return get(ServiceVersionWrap.class, "/rds/" + versionId).execute().getVersion();
	}

}
