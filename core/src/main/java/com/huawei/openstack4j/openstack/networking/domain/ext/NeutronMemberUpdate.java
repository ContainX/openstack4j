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
import com.huawei.openstack4j.model.network.ext.MemberUpdate;
import com.huawei.openstack4j.model.network.ext.builder.MemberUpdateBuilder;

import com.google.common.base.MoreObjects;

/**
 * A updated member of a Lbaas pool
 * @author liujunpeng
 */
@JsonRootName("member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronMemberUpdate implements MemberUpdate {

	private static final long serialVersionUID = 1L;
   /**
    * 1~100
    */
    private Integer weight;
    @JsonProperty("admin_state_up")
    private boolean adminStateUp;
	@JsonProperty("pool_id")
	private String poolId;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAdminStateUp() {
		return adminStateUp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
			    .add("adminStateUp", adminStateUp)
			    .add("weight",weight)
			    .add("poolId", poolId)
			    .toString();
	}

	/**
	 * Member create builder
	 * @author liujunpeng
	 *
	 */
	public static class MemberUpdateConcreteBuilder implements MemberUpdateBuilder{

		private NeutronMemberUpdate m;

		public MemberUpdateConcreteBuilder() {
			this(new NeutronMemberUpdate());
		}

		public MemberUpdateConcreteBuilder(NeutronMemberUpdate m) {
			this.m = m;
		}

		@Override
		public MemberUpdateBuilder from(MemberUpdate in) {
			m = (NeutronMemberUpdate) in;
			return this;
		}

		@Override
		public MemberUpdate build() {
			return m;
		}

		@Override
		public MemberUpdateBuilder adminStateUp(boolean adminStateUp) {
			m.adminStateUp = adminStateUp;
			return this;
		}

		@Override
		public MemberUpdateBuilder weight(Integer weight) {
			m.weight = weight;
			return this;
		}

		/**
		 *
		 * {@inheritDoc}
		 */
		@Override
		public MemberUpdateBuilder poolId(String poolId) {
			m.poolId = poolId;
			return this;
		}
	}

	/**
	 * Wraps this MemberUpdate into a Builder
	 * @return the network builder
	 */
	public MemberUpdateBuilder toBuilder() {
		return new MemberUpdateConcreteBuilder(this);
	}

	public static MemberUpdateBuilder builder(){
		return new MemberUpdateConcreteBuilder();
	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public String getPoolId() {

		return poolId;
	}

}
