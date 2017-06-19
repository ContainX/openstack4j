package org.openstack4j.api.scaling;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch.Action;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

//TODO need test
@Test(suiteName = "AutoScaling/AutoScalingGroupInstanceV1", enabled = true)
public class AutoScalingGroupInstanceV1Tests extends AbstractTest {

	private static final String JSON_SCALING_GROUP_INSTANCE_LIST = null;
	private static final String JSON_SCALING_GROUP_INSTANCE_LIST2 = null;

	public void testListAutoScalingGroupInstance() throws IOException {
		respondWith(JSON_SCALING_GROUP_INSTANCE_LIST);

		String groupId = "";
		List<? extends ScalingGroupInstance> all = osv3().autoScaling().groupInstances().list(groupId);
		assertTrue(all != null && all.size() == 10);

		respondWith(JSON_SCALING_GROUP_INSTANCE_LIST2);
		ScalingGroupInstanceListOptions options = ScalingGroupInstanceListOptions.create().heathStatus("NORMAL")
				.lifeCycleState("INSERVICE");
		List<? extends ScalingGroupInstance> list = osv3().autoScaling().groupInstances().list(groupId, options);
		assertTrue(list != null && list.size() == 2);
		for (ScalingGroupInstance instance : list) {
			assertTrue("INSERVICE".equalsIgnoreCase(instance.getLifeCycleState()));
			assertTrue("NORMAL".equalsIgnoreCase(instance.getHealthStatus()));
		}
	}

	public void testDeleteAutoScalingGroupInstance() {
		respondWith(204);
		String instanceId = "";
		ActionResponse resp = osv3().autoScaling().groupInstances().delete(instanceId, false);
		assertTrue(resp.isSuccess() && resp.getCode() == 204, resp.getFault());
	}

	public void testBatchOperateAutoScalingGroupInstance() {
		String groupId = "";
		List<String> instanceIds = Lists.newArrayList();
		ActionResponse resp = osv3().autoScaling().groupInstances().batchOperate(groupId, instanceIds, false,
				Action.ADD);
		assertTrue(resp.isSuccess() && resp.getCode() == 204, resp.getFault());

		respondWith(204);
		resp = osv3().autoScaling().groupInstances().batchOperate(groupId, instanceIds, false, Action.REMOVE);
		assertTrue(resp.isSuccess() && resp.getCode() == 204, resp.getFault());
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}

}
