package com.huawei.openstack4j.functional.cloudeye;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.cloudeye.DataPoint;
import com.huawei.openstack4j.model.cloudeye.Filter;
import com.huawei.openstack4j.model.cloudeye.MetricAggregation;
import com.huawei.openstack4j.model.cloudeye.MetricData.ValueType;
import com.huawei.openstack4j.model.cloudeye.Period;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetric;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricData;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricDemension;

public class MetricDataTest extends AbstractTest {

	Date now = null;
	Date fiveMinAgo = null;
	Date sixMinAgo= null;

	@BeforeClass
	public void prepare() {
		now = new Date();
		fiveMinAgo = new Date(now.getTime() - 1000 * 60 * 5);
		sixMinAgo = new Date(now.getTime() - 1000 * 60 * 5 - 1000);
	}

	@Test
	public void testAddMetricsDatas() {

		CloudEyeMetricDemension dimension = CloudEyeMetricDemension.builder().name("instance_id")
				.value("33328f02-3814-422e-b688-bfdba93d4050").build();
		List<CloudEyeMetricDemension> dimensions = Lists.newArrayList(dimension);

		CloudEyeMetric metric = CloudEyeMetric.builder().namespace("SDK.unittests").metricName("cpu_util")
				.dimensions(dimensions).build();

		CloudEyeMetricData data1 = CloudEyeMetricData.builder().metric(metric).ttl(604800).collectTime(fiveMinAgo)
				.value(60).unit("%").type(ValueType.Integer).build();

		CloudEyeMetricData data2 = CloudEyeMetricData.builder().metric(metric).ttl(604800).collectTime(now).value(60)
				.unit("%").type(ValueType.Integer).build();

		ActionResponse response = osclient.cloudEye().metricsDatas().add(Lists.newArrayList(data1, data2));
		Assert.assertTrue(response.isSuccess());
	}

	@Test(dependsOnMethods = { "testAddMetricsDatas" })
	public void testGetMetricDatas() {
		MetricAggregation aggregations = osclient.cloudEye().metricsDatas().get("SDK.unittests", "cpu_util", sixMinAgo,
				now, Period.FIVE_MINS, Filter.AVERAGE,
				new String[] { "instance_id,33328f02-3814-422e-b688-bfdba93d4050" });

		List<? extends DataPoint> datapoints = aggregations.getDatapoints();
		Assert.assertTrue(datapoints.size() == 2);
		Assert.assertEquals(datapoints.get(0).getAverage(), 60);
		Assert.assertEquals(datapoints.get(1).getAverage(), 60);
	}

}
