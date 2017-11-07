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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.Date;
import java.util.List;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.map.reduce.JobExecutionAction;
import com.huawei.openstack4j.model.map.reduce.JobExecutionInfo;

/**
 * Model represent attributes of JobExecutionInfo
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapReduceJobExecutionInfo implements JobExecutionInfo {

	private static final long serialVersionUID = 1L;

	@JsonProperty("status")
	private String status;
	@JsonProperty("externalId")
	private String externalId;
	@JsonProperty("run")
	private int run;
	@JsonProperty("startTime")
	@JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale = "en")
	private Date startTime;
	@JsonProperty("appName")
	private String appName;
	@JsonProperty("lastModTime")
	@JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale = "en")
	private Date lastModTime;
	@JsonProperty("actions")
	private List<MapReduceJobExecutionAction> actions;
	@JsonProperty("acl")
	private String acl;
	@JsonProperty("consoleUrl")
	private String consoleUrl;
	@JsonProperty("appPath")
	private String appPath;
	@JsonProperty("toString")
	private String toString;
	@JsonProperty("user")
	private String user;
	@JsonProperty("conf")
	private String conf;
	@JsonProperty("parentId")
	private String parentId;
	@JsonProperty("createdTime")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale = "en")
    private Date createdTime;
	@JsonProperty("group")
	private String group;
	@JsonProperty("endTime")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale = "en")
    private Date endTime;
	@JsonProperty("id")
	private String id;

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public String getExternalId() {
		return externalId;
	}

	@Override
	public int getRun() {
		return run;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public String getAppName() {
		return appName;
	}

	@Override
	public Date getLastModTime() {
		return lastModTime;
	}

	@Override
	public List<? extends JobExecutionAction> getActions() {
		return actions;
	}

	@Override
	public String getAcl() {
		return acl;
	}

	@Override
	public String getConsoleUrl() {
		return consoleUrl;
	}

	@Override
	public String getAppPath() {
		return appPath;
	}

	@Override
	public String getToString() {
		return toString;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getConf() {
		return conf;
	}

	@Override
	public String getParentId() {
		return parentId;
	}

	@Override
	public Date getCreatedTime() {
		return createdTime;
	}

	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("status", status).add("externalId", externalId)
				.add("run", run).add("startTime", startTime).add("appName", appName).add("lastModTime", lastModTime)
				.add("actions", actions).add("acl", acl).add("consoleUrl", consoleUrl).add("appPath", appPath)
				.add("toString", toString).add("user", user).add("conf", conf).add("parentId", parentId)
				.add("createdTime", createdTime).add("group", group).add("group", group).add("id", id).toString();
	}

}
