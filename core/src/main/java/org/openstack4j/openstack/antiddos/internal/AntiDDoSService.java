/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package org.openstack4j.openstack.antiddos.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;
import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.antiddos.domain.AntiDDoS;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSConfig;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSDailyData;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSDailyData.AntiDDoSDailyDatas;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSLog;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSLog.AntiDDoSLogs;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSStatus;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSStatus.AntiDDoSStatuses;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSStatusDetail;
import org.openstack4j.openstack.antiddos.domain.Task;
import org.openstack4j.openstack.antiddos.options.AntiDDoSLogListOptions;
import org.openstack4j.openstack.antiddos.options.AntiDDoSStatusListOptions;

import com.google.common.base.Strings;

public class AntiDDoSService extends BaseAntiDDoSService implements RestService {

	public AntiDDoSConfig listConfig() {
		return get(AntiDDoSConfig.class, uri("/antiddos/query_config_list")).execute();
	}

	public Task create(AntiDDoS entity, String floatingIpId) {
		checkArgument(entity !=null, "entity is required");
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		checkArgument(entity.getEnableL7() != null, "enableL7 is required");
		checkArgument(entity.getTrafficPos() != null, "trafficPos is required");
		checkArgument(entity.getHttpRequestPos() != null, "httpRequestPos is required");
		checkArgument(entity.getCleaningAccessPos() != null, "cleaningAccessPos is required");
		checkArgument(entity.getAppType() != null, "appType is required");
		
		return post(Task.class, uri("/antiddos/%s", floatingIpId)).entity(entity).execute();
	}
	
	public Task delete(String floatingIpId) {
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		return delete(Task.class, uri("/antiddos/%s", floatingIpId)).execute();
	}
	
	public AntiDDoS get(String floatingIpId) {
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		return get(AntiDDoS.class, uri("/antiddos/%s", floatingIpId)).execute();
	}
	
	public Task update(AntiDDoS entity, String floatingIpId) {
		checkArgument(entity !=null, "entity is required");
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		checkArgument(entity.getEnableL7() != null, "enableL7 is required");
		checkArgument(entity.getTrafficPos() != null, "trafficPos is required");
		checkArgument(entity.getHttpRequestPos() != null, "httpRequestPos is required");
		checkArgument(entity.getCleaningAccessPos() != null, "cleaningAccessPos is required");
		checkArgument(entity.getAppType() != null, "appType is required");
		
		return post(Task.class, uri("/antiddos/%s", floatingIpId)).entity(entity).execute();
	}
	
	public Task task(String taskId) {
		checkArgument(!Strings.isNullOrEmpty(taskId), "taskId is required");
		return get(Task.class, uri("/query_task_status")).param("task_id", taskId).execute();
	}
	
	public List<? extends AntiDDoSStatus> statuses() {
		return get(AntiDDoSStatuses.class, uri("/antiddos")).execute().getList();
	}

	public List<? extends AntiDDoSStatus> statuses(AntiDDoSStatusListOptions options) {
		checkArgument(options != null, "options is required");
		return get(AntiDDoSStatuses.class, uri("/antiddos")).params(options.getOptions()).execute().getList();
	}
	
	public AntiDDoSStatusDetail status(String floatingIpId) {
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		return get(AntiDDoSStatusDetail.class, uri("/antiddos/%s/status", floatingIpId)).execute();
	}
	
	public List<? extends AntiDDoSDailyData> daily(String floatingIpId) {
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		return get(AntiDDoSDailyDatas.class, uri("/antiddos/%s/daily", floatingIpId)).execute().getList();
	}
	
	public List<? extends AntiDDoSLog> logs(String floatingIpId) {
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		return get(AntiDDoSLogs.class, uri("/antiddos/%s/logs", floatingIpId)).execute().getList();
	}
	
	public List<? extends AntiDDoSLog> logs(String floatingIpId, AntiDDoSLogListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(floatingIpId), "floatingIpId is required");
		checkArgument(options != null, "options is required");
		return get(AntiDDoSLogs.class, uri("/antiddos/%s/logs", floatingIpId)).params(options.getOptions()).execute().getList();
	}
	
	public AntiDDoSWeeklyData weekly() {
		return get(AntiDDoSWeeklyData.class, uri("/antiddos/weekly")).execute();
	}
	
	public AntiDDoSWeeklyData weekly(Date periodStartDate) {
		checkArgument(periodStartDate != null, "periodStartDate is required");
		return get(AntiDDoSWeeklyData.class, uri("/antiddos/weekly")).param("period_start_date", periodStartDate.getTime()).execute();
	}
}
