package com.huawei.openstack4j.api.maas;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.api.AbstractTest;
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

@Test(suiteName = "MaaS/MaaS")
public class MaaSTest extends AbstractTest {

	private static final String srcRegion = "srcRegion";
	private static final String srcAk = "srcAk";
	private static final String srcSk = "srcSk";
	private static final String srcBucket = "srcBucket";
	private static final String srcObjectKey = "srcObjectKey";

	private static final String dstRegion = "dstRegion";
	private static final String dstAk = "dstAk";
	private static final String dstSk = "dstSk";
	private static final String dstBucket = "dstBucket";
	private static final String dstObjectKey = "dstObjectKey";

	private static final String JSON_TASK_CREATE = "/maas/task_create.json";
	private static final String JSON_VERSION = "/maas/version.json";
	private static final String JSON_TASK_COUNT = "/maas/task_count.json";
	private static final String JSON_TASK_LIST = "/maas/task_list.json";
	private static final String JSON_TASK = "/maas/task.json";

	private String topicUrn = "";

	private Long taskId;

	public void testVersionGet() throws IOException {
		respondWith(JSON_VERSION);

		Version[] version = osv3().maas().version().get();
		assertTrue(version != null);
	}

	public void testTaskCreate() throws IOException {
		respondWith(JSON_TASK_CREATE);

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
		TaskCreateResp resp = osv3().maas().task().create(create);
		assertTrue(resp.getId().equals(170770419072587l), "task create failed");
		taskId = resp.getId();
	}

	public void testTaskStop() {
		respondWith(200);

		ActionResponse resp = osv3().maas().task().stop(taskId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testTaskList() throws IOException {
		respondWith(JSON_TASK_LIST);

		TaskListOptions options = TaskListOptions.create().start(0).limit(10);
		Task[] list = osv3().maas().task().list(options);
		assertTrue(list.length == 5);
	}

	public void testTaskCount() throws IOException {
		respondWith(JSON_TASK_COUNT);
		respondWith(JSON_TASK_COUNT);

		long count = osv3().maas().task().count();
		assertTrue(count == 5l);
		
		count = osv3().maas().task().count(State.SUCCESS);
		assertTrue(count == 5l);
	}

	public void testTaskStart() {
		respondWith(200);

		TaskStart task = TaskStart.builder().sourceAk(srcAk).sourceSk(srcSk).targetAk(dstAk).targetSk(dstSk).build();
		ActionResponse resp = osv3().maas().task().start(taskId, task);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testTaskGet() throws IOException {
		respondWith(JSON_TASK);

		Task task = osv3().maas().task().get(taskId);
		assertTrue("test-4-bill-2-sdk-test-20170728-20170728105721639_406".equals(task.getName()), "task get failed");
	}

	public void testTaskDelete() {
		respondWith(200);

		ActionResponse resp = osv3().maas().task().delete(taskId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Override
	protected Service service() {
		return Service.MAAS;
	}

}
