package com.huawei.openstack4j.sample.cloudeye;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;
import com.huawei.openstack4j.sample.AbstractSample;

public class QuotaSample extends AbstractSample {

	@Test
	public void testGetQuotas() {
		List<Quota> list = osclient.cloudEye().quotas().get();
		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 1);
		Assert.assertEquals(list.get(0).getType(), ResourceType.ALARM);
		Assert.assertNotNull(list.get(0).getUsed());
		Assert.assertNotNull(list.get(0).getQuota());
	}

}
