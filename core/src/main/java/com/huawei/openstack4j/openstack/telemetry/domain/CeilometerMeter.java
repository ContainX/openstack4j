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
package com.huawei.openstack4j.openstack.telemetry.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.telemetry.Meter;

import com.google.common.base.MoreObjects;

/**
 * A Meter is a category of Measurement
 *
 * @author Jeremy Unruh
 */
public class CeilometerMeter implements Meter {

	private static final long serialVersionUID = 1L;

	@JsonProperty("meter_id")
	private String id;
	private String name;
	@JsonProperty("resource_id")
	private String resourceId;
	@JsonProperty("project_id")
	private String projectId;
	private Type type;
	private String unit;

	private String source;

	@JsonProperty("user_id")
  private String userId;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProjectId() {
		return projectId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUnit() {
		return unit;
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUserId() {
    return userId;
  }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				    .add("id", id).add("name", name).add("resource_id", resourceId)
				    .add("project_id", projectId).add("type", type).add("unit", unit)
				    .add("user_id",  userId)
				    .add("source", source)
				    .toString();
	}
}
