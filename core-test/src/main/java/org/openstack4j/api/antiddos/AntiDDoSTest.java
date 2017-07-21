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
package org.openstack4j.api.antiddos;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.antiddos.constants.AppType;
import org.openstack4j.openstack.antiddos.constants.CleaningAccessPos;
import org.openstack4j.openstack.antiddos.constants.HttpRequestPos;
import org.openstack4j.openstack.antiddos.constants.TrafficPos;
import org.openstack4j.openstack.antiddos.domain.AntiDDoS;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSConfig;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSDailyData;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSLog;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSStatus;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSStatus.DDoSStatus;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSStatusDetail;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSWarn;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSWeeklyData;
import org.openstack4j.openstack.antiddos.domain.Task;
import org.openstack4j.openstack.antiddos.options.AntiDDoSLogListOptions;
import org.openstack4j.openstack.antiddos.options.AntiDDoSStatusListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Test(suiteName = "antiddos/antiddos", enabled = true)
public class AntiDDoSTest extends AbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AntiDDoSTest.class);
	private static final String TASK_ACCEPTED = "10000000";
	private static final String floatingIpId = "11ee0ec8-2b4f-438d-8235-dd22a3effa46";
	private static final String JSON_TASK = "/antiddos/task.json";
	private static final String JSON_CONFIGS = "/antiddos/configs.json";
	private static final String JSON_GET_ANTIDDOS = "/antiddos/get_antiddos.json";
	private static final String JSON_GET_STATUS = "/antiddos/get_antiddos_status.json";
	private static final String JSON_LIST_STATUS = "/antiddos/list_antiddos_status.json";
	private static final String JSON_LIST_STATUS2 = "/antiddos/list_antiddos_status2.json";
	private static final String JSON_DAILY_REPORT = "/antiddos/daily_report.json";
	private static final String JSON_LIST_LOGS = "/antiddos/list_logs.json";
	private static final String JSON_WEEKLY_REPORT = "/antiddos/weekly_report.json";
	private static final String JSON_WEEKLY_REPORT2 = "/antiddos/weekly_report2.json";
	private static final String JSON_WARN_ALERT = "/antiddos/warnalert.json";

	public void testCreateAntiDDoS() throws IOException {
		respondWith(JSON_TASK);
		AntiDDoS entity = AntiDDoS.builder().enableL7(true).trafficPos(TrafficPos.POS_1)
				.httpRequestPos(HttpRequestPos.POS_1).cleaningAccessPos(CleaningAccessPos.POS_1).appType(AppType.Type_0)
				.build();
		Task task = osv3().antiDDoS().antiddos().create(entity, floatingIpId);
		LOGGER.info("{}", task);

		assertTrue(task.getErrorCode().equals(TASK_ACCEPTED));
	}
	
	public void testDeleteAntiDDoS() throws IOException {
		respondWith(JSON_TASK);
		Task task = osv3().antiDDoS().antiddos().delete(floatingIpId);
		LOGGER.info("{}", task);

		assertTrue(task.getErrorCode().equals(TASK_ACCEPTED));
	}

	public void testListConfigs() throws IOException {
		respondWith(JSON_CONFIGS);
		AntiDDoSConfig configs = osv3().antiDDoS().antiddos().listConfigs();
		LOGGER.info("{}", configs);
	}

	public void testGetAntiDDoS() throws IOException {
		respondWith(JSON_GET_ANTIDDOS);
		AntiDDoS antiDDoS = osv3().antiDDoS().antiddos().get(floatingIpId);
		LOGGER.info("{}", antiDDoS);
	}

	public void testUpdateAntiDDoS() throws IOException {
		respondWith(JSON_GET_ANTIDDOS);
		respondWith(JSON_TASK);
		AntiDDoS entity = osv3().antiDDoS().antiddos().get(floatingIpId);
		entity = entity.toBuilder().appType(AppType.Type_1).build();
		
		Task task = osv3().antiDDoS().antiddos().update(entity, floatingIpId);
		LOGGER.info("{}", task);

		assertTrue(task.getErrorCode().equals(TASK_ACCEPTED));
	}

	public void testGetTask() throws IOException {
		respondWith(JSON_TASK);
		Task task = osv3().antiDDoS().antiddos().getTask("taskId");
		LOGGER.info("{}", task);
	}

	public void testGetStatus() throws IOException {
		respondWith(JSON_GET_STATUS);
		AntiDDoSStatusDetail status = osv3().antiDDoS().antiddos().getStatus(floatingIpId);
		LOGGER.info("{}", status);
		assertTrue(status.getStatus() != null);
	}

	public void testListStatuses() throws IOException {
		respondWith(JSON_LIST_STATUS);
		respondWith(JSON_LIST_STATUS2);
		AntiDDoSStatus statuses = osv3().antiDDoS().antiddos().listStatus();
		LOGGER.info("{}", statuses);
		DDoSStatus status = statuses.getDdosStatus().get(0);
		assertTrue(statuses.getTotal() >= 1 && status.getFloatingIpId().equals(floatingIpId));

		AntiDDoSStatusListOptions options = AntiDDoSStatusListOptions.create().status(status.getStatus());
		AntiDDoSStatus statuses2 = osv3().antiDDoS().antiddos().listStatus(options);
		LOGGER.info("{}", statuses2);
		assertTrue(statuses2.getTotal() >= 1);
	}

	public void testDailyReport() throws IOException {
		respondWith(JSON_DAILY_REPORT);
		List<? extends AntiDDoSDailyData> dailyReport = osv3().antiDDoS().antiddos().dailyReport(floatingIpId);
		LOGGER.info("{}", dailyReport);
	}

	public void testListLog() throws IOException {
		respondWith(JSON_LIST_LOGS);
		respondWith(JSON_LIST_LOGS);
		List<? extends AntiDDoSLog> logs = osv3().antiDDoS().antiddos().listLogs(floatingIpId);
		LOGGER.info("{}", logs);

		AntiDDoSLogListOptions options = AntiDDoSLogListOptions.create().limit(1).offset(1);
		List<? extends AntiDDoSLog> logs2 = osv3().antiDDoS().antiddos().listLogs(floatingIpId, options);
		LOGGER.info("{}", logs2);
	}

	public void testWeeklyReport() throws IOException {
		respondWith(JSON_WEEKLY_REPORT);
		respondWith(JSON_WEEKLY_REPORT2);
		AntiDDoSWeeklyData weekly = osv3().antiDDoS().antiddos().weeklyReport();
		LOGGER.info("{}", weekly);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -10);
		AntiDDoSWeeklyData weekly2 = osv3().antiDDoS().antiddos().weeklyReport(cal.getTime());
		LOGGER.info("{}", weekly2);
	}

	public void testQueryWarn() throws IOException {
		respondWith(JSON_WARN_ALERT);
		AntiDDoSWarn query = osv3().antiDDoS().warnalert().query();
		LOGGER.info("{}", query);
	}

	@Override
	protected Service service() {
		return Service.ANTI_DDOS;
	}

}
