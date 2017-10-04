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
package com.huawei.openstack4j.functional.maas;

import static com.google.common.base.Preconditions.checkArgument;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.maas.constants.State;
import com.huawei.openstack4j.openstack.maas.constants.TriggerCondition;
import com.huawei.openstack4j.openstack.maas.domain.Task;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreateResp;
import com.huawei.openstack4j.openstack.maas.domain.TaskStart;
import com.huawei.openstack4j.openstack.maas.domain.Version;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate.Node;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate.SmnInfo;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate.TaskCreateBuilder;
import com.huawei.openstack4j.openstack.maas.options.TaskListOptions;

public class MaaSTest extends AbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MaaSTest.class);

	// 源桶数据（需要是其他服务商的），由测试者提供
	private static final String srcRegion = "";
	private static final String srcAk = "";
	private static final String srcSk = "";
	private static final String srcBucket = "";
	private static final String srcObjectKey = "";

	// 目标桶数据，由测试者提供
	private static final String dstRegion = "";
	private static final String dstAk = "";
	private static final String dstSk = "";
	private static final String dstBucket = "";
	private static final String dstObjectKey = "";

	// 主题urn，若不为空，则会订阅该主题，由测试者设置
	private String topicUrn = "";

	private Long taskId;

	@BeforeClass
	public void check() {
		checkArgument(!Strings.isNullOrEmpty(srcRegion), "srcRegion is required");
		checkArgument(!Strings.isNullOrEmpty(srcAk), "srcAk is required");
		checkArgument(!Strings.isNullOrEmpty(srcSk), "srcSk is required");
		checkArgument(!Strings.isNullOrEmpty(srcBucket), "srcBucket is required");
		checkArgument(!Strings.isNullOrEmpty(srcObjectKey), "srcObjectKey is required");

		checkArgument(!Strings.isNullOrEmpty(dstRegion), "dstRegion is required");
		checkArgument(!Strings.isNullOrEmpty(dstAk), "dstAk is required");
		checkArgument(!Strings.isNullOrEmpty(dstSk), "dstSk is required");
		checkArgument(!Strings.isNullOrEmpty(dstBucket), "dstBucket is required");
		checkArgument(!Strings.isNullOrEmpty(dstObjectKey), "dstObjecyKey is required");
	}

	@Test(priority = 0)
	public void testVersionGet() {
		Version[] version = osclient.maas().version().get();
		LOGGER.info("{}", version);
		assertTrue(version != null);
	}

	@Test(priority = 1)
	public void testTaskCreate() {
		Node srcNode = Node.builder().region(srcRegion).ak(srcAk).sk(srcSk).objectKey(srcObjectKey).bucket(srcBucket)
				.build();
		Node dstNode = Node.builder().region(dstRegion).ak(dstAk).sk(dstSk).objectKey(dstObjectKey).bucket(dstBucket)
				.build();

		TaskCreateBuilder taskBuilder = TaskCreate.builder().srcNode(srcNode).dstNode(dstNode).enableKMS(false)
				.threadNum(5).description("description");

		if (!Strings.isNullOrEmpty(topicUrn)) {
			SmnInfo smnInfo = SmnInfo.builder().topicUrn(topicUrn).language("en-us")
					.triggerConditions(Lists.newArrayList(TriggerCondition.SUCCESS, TriggerCondition.FAIL)).build();
			taskBuilder.smnInfo(smnInfo);
		}
		TaskCreate create = taskBuilder.build();
		TaskCreateResp resp = osclient.maas().task().create(create);
		assertTrue(resp.getId() != null, "task create failed");
		taskId = resp.getId();
		LOGGER.info("{}", resp);

	}

	@Test(priority = 2)
	public void testTaskStop() {
		waitTaskToState(State.EXECUTE);
		
		ActionResponse resp = osclient.maas().task().stop(taskId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test(priority = 3)
	public void testTaskList() {
		TaskListOptions options = TaskListOptions.create().start(0).limit(10);
		Task[] list = osclient.maas().task().list(options);
		LOGGER.info("{}", list);
	}

	@Test(priority = 4)
	public void testTaskCount() {
		long count = osclient.maas().task().count();
		LOGGER.info("{}", count);
		count = osclient.maas().task().count(State.SUCCESS);
		LOGGER.info("{}", count);
	}

	@Test(priority = 5)
	public void testTaskStart() {
		waitTaskToState(State.STOP);
		
		TaskStart task = TaskStart.builder().sourceAk(srcAk).sourceSk(srcSk).targetAk(dstAk).targetSk(dstSk).build();
		ActionResponse resp = osclient.maas().task().start(taskId, task);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test(priority = 6)
	public void testTaskGet() {
		Task task = osclient.maas().task().get(taskId);
		LOGGER.info("{}", task);
		assertTrue(task.getId() != null, "task get failed");
	}

	@Test(priority = 7)
	public void testTaskDelete() {
		testTaskStop();
		waitTaskToState(State.STOP);
		ActionResponse resp = osclient.maas().task().delete(taskId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	/**
	 * wait task to state
	 * @param state
	 */
	private void waitTaskToState(State state) {
		Retry retry = new Retry() {
			@Override
			public Object run() {
				Task task = osclient.maas().task().get(taskId);
				if (state.equals(task.getStatus()))
					return task.getStatus();
				else
					return null;
			}

			@Override
			public Integer maxRetryTimes() {
				return 6;
			}

			@Override
			public Integer delay() {
				return 10;
			}
		};
		this.retry(retry);
	}
}
