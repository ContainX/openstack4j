package com.huawei.openstack4j.functional.cloudeye;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.cloudeye.Metric;
import com.huawei.openstack4j.model.cloudeye.OrderType;
import com.huawei.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;

public class MetricTest extends AbstractTest {

	private Alarm alarm = null;

	@Test
	public void testGetMetrics() {

		List<? extends Metric> list1 = osclient.cloudEye().metrics().getList();
		Assert.assertTrue(list1.size() > 0);

		MetricFilterOptions config = MetricFilterOptions.create().limit(50).order(OrderType.ASC).namespace("SYS.ECS")
				.metricName("cpu_util");
		// MetricFilterOptions options = config.dim(new String[] { "instance_id,5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba"
		// });
		// options.start("SYS.ECS.network_outgoing_bytes_aggregate_rate.instance_id:5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba");
		List<? extends Metric> metrics = osclient.cloudEye().metrics().getList(config);

		List<String> collect = metrics.stream().map(metric -> metric.getMetricName()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains("cpu_util"));
		
		Metric metric = metrics.get(0);
		Assert.assertEquals(metric.getMetricName(), "cpu_util");
		Assert.assertEquals(metric.getNamespace(), "SYS.ECS");
	}

	@Test
	public void testGetFavoriteMetrics() {
		List<? extends Metric> list = osclient.cloudEye().metrics().getFavoriteList();
		if (list.size() > 0) {
			Metric metric = list.get(0);
			Assert.assertNotNull(metric.getMetricName());
			Assert.assertNotNull(metric.getNamespace());
		}
	}

}
