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
package com.huawei.openstack4j.functional.antiddos;

import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.openstack.antiddos.constants.AppType;
import com.huawei.openstack4j.openstack.antiddos.constants.CleaningAccessPos;
import com.huawei.openstack4j.openstack.antiddos.constants.HttpRequestPos;
import com.huawei.openstack4j.openstack.antiddos.constants.TaskStatus;
import com.huawei.openstack4j.openstack.antiddos.constants.TrafficPos;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoS;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSConfig;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSDailyData;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSLog;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSStatus;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSStatusDetail;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSWarn;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSWeeklyData;
import com.huawei.openstack4j.openstack.antiddos.domain.Task;
import com.huawei.openstack4j.openstack.antiddos.domain.AntiDDoSStatus.DDoSStatus;
import com.huawei.openstack4j.openstack.antiddos.options.AntiDDoSLogListOptions;
import com.huawei.openstack4j.openstack.antiddos.options.AntiDDoSStatusListOptions;

public class AntiDDoSTest extends AbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AntiDDoSTest.class);
	private static final Object TASK_ACCEPTED = "10000000";
	private static final String floatingIpId = "11ee0ec8-2b4f-438d-8235-dd22a3effa46";
	private static String taskId;

	@BeforeClass
	public void testCreateAntiDDoS() throws InterruptedException {
		AntiDDoS entity = AntiDDoS.builder().enableL7(true).trafficPos(TrafficPos.POS_1)
				.httpRequestPos(HttpRequestPos.POS_1).cleaningAccessPos(CleaningAccessPos.POS_1).appType(AppType.Type_0)
				.build();
		Task task = osclient.antiDDoS().antiddos().create(entity, floatingIpId);
		LOGGER.info("{}", task);
		taskId = task.getTaskId();
		waitTaskFinish(taskId);

		assertTrue(task.getErrorCode().equals(TASK_ACCEPTED));
	}

	@AfterClass
	public void testDeleteAntiDDoS() throws InterruptedException {
		Task task = osclient.antiDDoS().antiddos().delete(floatingIpId);
		LOGGER.info("{}", task);
		waitTaskFinish(task.getTaskId());

		assertTrue(task.getErrorCode().equals(TASK_ACCEPTED));
	}

	@Test
	public void testListConfigs() {
		AntiDDoSConfig configs = osclient.antiDDoS().antiddos().listConfigs();
		LOGGER.info("{}", configs);
	}

	@Test
	public void testGetAntiDDoS() {
		AntiDDoS antiDDoS = osclient.antiDDoS().antiddos().get(floatingIpId);
		LOGGER.info("{}", antiDDoS);
	}

	@Test
	public void testUpdateAntiDDoS() throws InterruptedException {
		AntiDDoS entity = osclient.antiDDoS().antiddos().get(floatingIpId);
		entity = entity.toBuilder().appType(AppType.Type_1).build();
		Task task = osclient.antiDDoS().antiddos().update(entity, floatingIpId);
		LOGGER.info("{}", task);
		waitTaskFinish(task.getTaskId());

		assertTrue(task.getErrorCode().equals(TASK_ACCEPTED));
	}

	@Test
	public void testGetTask() {
		Task task = osclient.antiDDoS().antiddos().getTask(taskId);
		LOGGER.info("{}", task);
	}

	@Test
	public void testGetStatus() {
		AntiDDoSStatusDetail status = osclient.antiDDoS().antiddos().getStatus(floatingIpId);
		LOGGER.info("{}", status);
		assertTrue(status.getStatus() != null);
	}

	@Test
	public void testListStatuses() {
		AntiDDoSStatus statuses = osclient.antiDDoS().antiddos().listStatus();
		LOGGER.info("{}", statuses);
		DDoSStatus status = statuses.getDdosStatus().get(0);
		assertTrue(statuses.getTotal() >= 1 && status.getFloatingIpId().equals(floatingIpId));

		AntiDDoSStatusListOptions options = AntiDDoSStatusListOptions.create().status(status.getStatus());
		AntiDDoSStatus statuses2 = osclient.antiDDoS().antiddos().listStatus(options);
		LOGGER.info("{}", statuses2);
		assertTrue(statuses2.getTotal() >= 1);
	}

	@Test
	public void testDailyReport() {
		List<? extends AntiDDoSDailyData> dailyReport = osclient.antiDDoS().antiddos().dailyReport(floatingIpId);
		LOGGER.info("{}", dailyReport);
	}

	@Test
	public void testListLog() {
		List<? extends AntiDDoSLog> logs = osclient.antiDDoS().antiddos().listLogs(floatingIpId);
		LOGGER.info("{}", logs);

		AntiDDoSLogListOptions options = AntiDDoSLogListOptions.create().limit(1).offset(1);
		List<? extends AntiDDoSLog> logs2 = osclient.antiDDoS().antiddos().listLogs(floatingIpId, options);
		LOGGER.info("{}", logs2);
	}

	@Test
	public void testWeeklyReport() {
		AntiDDoSWeeklyData weekly = osclient.antiDDoS().antiddos().weeklyReport();
		LOGGER.info("{}", weekly);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -10);
		AntiDDoSWeeklyData weekly2 = osclient.antiDDoS().antiddos().weeklyReport(cal.getTime());
		LOGGER.info("{}", weekly2);
	}

	@Test
	public void testQueryWarn() {
		AntiDDoSWarn query = osclient.antiDDoS().warnalert().query();
		LOGGER.info("{}", query);
	}

	/**
	 * wait until task finished
	 * 
	 * @param taskId
	 * @throws InterruptedException
	 */
	private void waitTaskFinish(String taskId) throws InterruptedException {
		Retry retry = new Retry() {

			@Override
			public Object run() {
				LOGGER.info("--------- PENDING TASK [{}]------------", taskId);
				Task task2 = osclient.antiDDoS().antiddos().getTask(taskId);
				List<TaskStatus> expected = Lists.newArrayList(TaskStatus.SUCCESS, TaskStatus.FAILED);
				if (expected.contains(task2.getStatus())) {
					return task2.getStatus();
				} else
					return null;
			}

			@Override
			public Integer maxRetryTimes() {
				return 5;
			}

			@Override
			public Integer delay() {
				return 60;
			}
		};
		
		TaskStatus status = (TaskStatus) this.retry(retry);
		if (status != null && TaskStatus.FAILED.equals(status)) {
			throw new RuntimeException(String.format("[%s]Task failed", taskId));
		}
	}
}
