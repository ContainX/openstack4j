package org.openstack4j.sample;

import java.util.List;

import org.openstack4j.model.scaling.ScalingQuota;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

//TODO need test
public class ASQuotasSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASQuotasSample.class);

	@Test
	public void testListAutoScalingQuotas() {
		String groupId = "";
		List<? extends ScalingQuota> all = osclient.autoScaling().quotas().list();
		logger.info("{}", all);

		List<? extends ScalingQuota> list = osclient.autoScaling().quotas().list(groupId);
		logger.info("{}", list);
	}
}
