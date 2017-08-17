package org.openstack4j.sample.cloudeye;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.model.cloudeye.Filter;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.model.cloudeye.MetricAggregation;
import org.openstack4j.model.cloudeye.MetricData.ValueType;
import org.openstack4j.model.cloudeye.OrderType;
import org.openstack4j.model.cloudeye.Period;
import org.openstack4j.model.cloudeye.Quota;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeMetric;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeMetricData;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeMetricDemension;
import org.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;
import org.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class CloudEyeSample extends AbstractSample {
    private static final Logger logger = LoggerFactory.getLogger(CloudEyeSample.class);
    public static final String ALARM_ID = "al1483387711418ZNpR8DX3g";

    @Test
    public void testGetMetrics() {

        List<? extends Metric> list1 = osclient.cloudEye().metrics().getList();
        logger.info("All metrics: {}", list1);
        
        list1.get(0).getId();

        MetricFilterOptions config = MetricFilterOptions.create();
        MetricFilterOptions options = config.dim(new String[]{"instance_id,5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba"});
        options.limit(50);
        options.order(OrderType.ASC);
        options.namespace("SYS.ECS");
        options.metricName("network_outgoing_bytes_aggregate_rate");
//        options.start("SYS.ECS.network_outgoing_bytes_aggregate_rate.instance_id:5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba");

        List<? extends Metric> list2 = osclient.cloudEye().metrics().getList(options);
        logger.info("All metrics with filter: {}", list2);
    }

    @Test
    public void testGetFavoriteMetrics() {
        List<? extends Metric> list = osclient.cloudEye().metrics().getFavoriteList();
        logger.info("All favorite metrics: {}", list);
    }

    @Test
    public void testListAlarms() {

        List<? extends Alarm> list1 = osclient.cloudEye().alarms().list();
        logger.info("All alarms: {}", list1);

        AlarmFilterOptions config = AlarmFilterOptions.create();
        AlarmFilterOptions options = config.limit(5);
        options.order(OrderType.ASC);
//        options.start("al1483387711418ZNpR8DX3g");

        List<? extends Alarm> list2 = osclient.cloudEye().alarms().list(options);
        logger.info("All alarms with filter: {}", list2);
    }

    @Test
    public void testGetAlarm() {
        List<? extends Alarm> alarm = osclient.cloudEye().alarms().get(ALARM_ID);
        logger.info("Alarm: {}", alarm);
    }

    @Test
    public void testStartAlarm() {
        ActionResponse actionResponse = osclient.cloudEye().alarms().startAlarm(ALARM_ID);
        logger.info("Start alarm: {}", actionResponse);
    }

    @Test
    public void testStopAlarm() {
        ActionResponse actionResponse = osclient.cloudEye().alarms().stopAlarm(ALARM_ID);
        logger.info("Stop alarm: {}", actionResponse);
    }

    @Test
    public void testDeleteAlarm() {
        ActionResponse actionResponse = osclient.cloudEye().alarms().deleteAlarm(ALARM_ID);
        logger.info("Delete alarm: {}", actionResponse);
    }

    @Test
    public void testGetMetricDatas() {
        MetricAggregation metricAggregation = osclient.cloudEye().metricsDatas().get("SYS.ECS", "network_incoming_bytes_aggregate_rate",
                new Date(1498321875058l), new Date(1498321875058l), Period.FIVE_MINS, Filter.AVERAGE, new String[]{"instance_id,33328f02-3814-422e-b688-bfdba93d4050"});
        logger.info("Add metric aggregation: {}", metricAggregation);
    }

    @Test
    public void testAddMetricsDatas() {
        List<CloudEyeMetricData> metrics = new ArrayList<>();
        CloudEyeMetricDemension.CloudEyeMetricDemensionBuilder dimBuilder = CloudEyeMetricDemension.builder().name("instance_id").value("33328f02-3814-422e-b688-bfdba93d4050");
        CloudEyeMetricDemension dim1 = dimBuilder.build();
        List<CloudEyeMetricDemension> dimList = new ArrayList<>();
        dimList.add(dim1);

        CloudEyeMetric.CloudEyeMetricBuilder metricBuilder = CloudEyeMetric.builder().namespace("MINE.APP")
                .metricName("test_add_metric_data_1")
                .dimensions(dimList);
        CloudEyeMetricData.CloudEyeMetricDataBuilder builder1 = CloudEyeMetricData.builder()
                .metric(metricBuilder.build())
                .ttl(172800)
                .collectTime(new Date())
                .value(60)
                .unit("%")
                .type(ValueType.Integer);

        CloudEyeMetric.CloudEyeMetricBuilder metricBuilder2 = CloudEyeMetric.builder().namespace("MINE.APP")
                .metricName("cpu_util")
                .dimensions(dimList);
        CloudEyeMetricData.CloudEyeMetricDataBuilder builder2 = CloudEyeMetricData.builder()
                .metric(metricBuilder2.build())
                .ttl(172800)
                .collectTime(new Date())
                .value(70)
                .unit("%")
                .type(ValueType.Integer);
        metrics.add(builder1.build());
        metrics.add(builder2.build());

        ActionResponse actionResponse = osclient.cloudEye().metricsDatas().add(metrics);
        logger.info("Add metric datas: {}", actionResponse);
    }

    @Test
    public void testGetQuotas() {
        Quota quotas = osclient.cloudEye().quotas().get();
        logger.info("Alarm: {}", quotas);
    }

}


