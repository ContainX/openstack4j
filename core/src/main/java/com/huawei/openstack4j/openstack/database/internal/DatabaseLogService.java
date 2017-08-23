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
package com.huawei.openstack4j.openstack.database.internal;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import com.huawei.openstack4j.openstack.database.constants.StatementType;
import com.huawei.openstack4j.openstack.database.domain.DatabaseErrorLog;
import com.huawei.openstack4j.openstack.database.domain.DatabaseSlowLog;
import com.huawei.openstack4j.openstack.database.domain.DatabaseErrorLog.ErrorLogs;
import com.huawei.openstack4j.openstack.database.domain.DatabaseSlowLog.SlowLogs;
import com.huawei.openstack4j.openstack.database.options.ErrorLogListOptions;

/**
 * The implementation of manipulation of database log
 *
 * @author QianBiao.NG
 * @date   2017-08-03 11:06:34
 */
public class DatabaseLogService extends BaseDatabaseServices {

	/**
	 * list slow logs of a database instance 
	 * 
	 * @param instanceId	database instance identifier
	 * @param statementType	SQL statement type of the slow log
	 * @param top			the amount of slow logs to be returned, the value range is 1-50, 10 by default
	 * @return a list of {@link DatabaseSlowLog} instances 
	 */
	public List<DatabaseSlowLog> listSlowLogs(String instanceId, StatementType statementType, Integer top) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(statementType != null, "parameter `statementType` should not be null");
		Preconditions.checkArgument(top == null || (top >= 1 && top <= 50),
				"parameter `top` should be null or between 1 and 50");
		return get(SlowLogs.class, uri("/instances/%s/slowlog", instanceId)).param("sftype", statementType.name())
				.param("top", top).execute().getList();
	}

	/**
	 * list error logs of a database instance 
	 * 
	 * @param options	filter options
	 * @return a list of {@link DatabaseErrorLog} instances 
	 */
	public List<DatabaseErrorLog> listErrorLogs(ErrorLogListOptions options) {
		Preconditions.checkArgument(options != null, "parameter `options` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(options.getInstanceId()),
				"parameter `options.instanceId` should not be empty");
		Preconditions.checkArgument(options.getOptions().get("startDate") != null,
				"parameter `options.startDate` should not be empty");
		Preconditions.checkArgument(options.getOptions().get("endDate") != null,
				"parameter `options.endDate` should not be empty");
		return get(ErrorLogs.class, uri("/instances/%s/errorlog", options.getInstanceId())).params(options.getOptions())
				.execute().getList();
	}

}
