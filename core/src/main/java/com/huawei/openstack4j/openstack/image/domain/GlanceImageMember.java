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
package com.huawei.openstack4j.openstack.image.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.image.ImageMember;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * Represents a system tenant who has access to another tenants Image
 *
 * @author Jeremy Unruh
 */
@JsonRootName("member")
public class GlanceImageMember implements ImageMember {

	private static final long serialVersionUID = 1L;

	@JsonProperty("member_id")
	private String memberId;
	@JsonProperty("can_share")
	private boolean canShare;

	public GlanceImageMember() { }

	public GlanceImageMember(String memberId, boolean canShare) {
		this.memberId = memberId;
		this.canShare = canShare;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMemberId() {
		return memberId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCanShare() {
		return canShare;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("memberId", memberId).add("canShare", canShare).toString();
	}

	public static class Members extends ListResult<GlanceImageMember> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("members")
		private List<GlanceImageMember> members;

		@Override
		protected List<GlanceImageMember> value() {
			return members;
		}
	}

}
