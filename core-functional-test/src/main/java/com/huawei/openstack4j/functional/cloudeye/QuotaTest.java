package com.huawei.openstack4j.functional.cloudeye;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;

public class QuotaTest extends AbstractTest {

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
