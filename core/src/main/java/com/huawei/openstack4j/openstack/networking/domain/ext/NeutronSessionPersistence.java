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
package com.huawei.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.SessionPersistence;
import com.huawei.openstack4j.model.network.ext.SessionPersistenceType;
import com.huawei.openstack4j.model.network.ext.builder.SessionPersistenceBuilder;

import com.google.common.base.MoreObjects;
@JsonRootName("session_persistence")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronSessionPersistence implements SessionPersistence {

	private static final long serialVersionUID = 1L;
	@JsonProperty("cookie_name")
	private String cookieName;
	private SessionPersistenceType type;
	/**
	 * wrap the SessionPersistence to builder
	 * @return SessionPersistenceBuilder
	 */
	@Override
	public SessionPersistenceBuilder toBuilder() {
		return new SessionPersistenceContreteBuilder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCookieName() {
		return cookieName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SessionPersistenceType getType() {
		return type;
	}
	/**
	 * SessionPersistence Builder
	 * @author liujunpeng
	 *
	 */
	public static class SessionPersistenceContreteBuilder implements SessionPersistenceBuilder{

		private NeutronSessionPersistence m;

		public SessionPersistenceContreteBuilder() {
			this(new NeutronSessionPersistence());
		}

		public SessionPersistenceContreteBuilder(NeutronSessionPersistence m) {
			this.m = m;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SessionPersistenceBuilder from(SessionPersistence in) {
			m = (NeutronSessionPersistence)in;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SessionPersistenceBuilder type(SessionPersistenceType type) {
			m.type = type;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SessionPersistenceBuilder cookieName(String cookieName) {
			m.cookieName = cookieName;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SessionPersistence build() {
			return m;
		}

	}

	public static SessionPersistenceBuilder builder(){
		return new SessionPersistenceContreteBuilder();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
			    .add("type",type)
			    .add("cookieName", cookieName)
			    .toString();
	}


}
