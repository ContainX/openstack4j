package org.openstack4j.sample;

import java.util.List;

import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

//TODO need test
public class ASActivityLogSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASActivityLogSample.class);

	@Test
	public void testListAutoScalingActivityLog() {
		String groupId = "";
		List<? extends ScalingActivityLog> all = osclient.autoScaling().activityLogs().list(groupId);
		logger.info("{}", all);

		ScalingActivityLogListOptions options = ScalingActivityLogListOptions.create().startNumber(5).limit(5);
		List<? extends ScalingActivityLog> list = osclient.autoScaling().activityLogs().list(groupId, options);
		logger.info("{}", list);
	}
}
