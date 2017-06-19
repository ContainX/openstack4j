package org.openstack4j.api.scaling;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;
import org.testng.annotations.Test;

//TODO need test
@Test(suiteName = "AutoScaling/AutoScalingActivityLogV1", enabled = true)
public class AutoScalingActivityLogV1Tests3 extends AbstractTest {

	private static final String JSON_SCALING_ACTIVITY_LOG_LIST = "";
	private static final String JSON_SCALING_ACTIVITY_LOG_LIST2 = "";

	public void testListAutoScalingActivityLog() throws IOException {
		respondWith(JSON_SCALING_ACTIVITY_LOG_LIST);
		String groupId = "";
		List<? extends ScalingActivityLog> all = osv3().autoScaling().activityLogs().list(groupId);
		assertTrue(all != null && all.size() == 5);

		respondWith(JSON_SCALING_ACTIVITY_LOG_LIST2);
		ScalingActivityLogListOptions options = ScalingActivityLogListOptions.create().startNumber(5).limit(5);
		List<? extends ScalingActivityLog> list = osv3().autoScaling().activityLogs().list(groupId, options);
		assertTrue(list != null && list.size() == 2);
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}

}
