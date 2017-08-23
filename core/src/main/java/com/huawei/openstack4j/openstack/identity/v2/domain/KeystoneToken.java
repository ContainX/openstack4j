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
package com.huawei.openstack4j.openstack.identity.v2.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.identity.AuthVersion;
import com.huawei.openstack4j.model.identity.v2.Tenant;
import com.huawei.openstack4j.model.identity.v2.TokenV2;

import com.google.common.base.MoreObjects;

public final class KeystoneToken implements TokenV2 {

	private static final long serialVersionUID = 1L;

	private String id;
	private Date expires;
	private KeystoneTenant tenant;

	@JsonProperty("issued_at")
	private Date created;

	public String getId() {
		return id;
	}

	public Date getExpires() {
		return expires;
	}

	public Tenant getTenant() {
		return tenant;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("id", id).add("created", created).add("expires", expires).add("tenant", tenant)
				.toString();
	}

	@JsonIgnore
	@Override
	public AuthVersion getVersion() {
		return AuthVersion.V2;
	}

}
