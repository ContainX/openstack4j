package org.openstack4j.sample;

import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.model.cloudeye.OrderType;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;
import org.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

public class CloudEyeSample extends AbstractSample {
    private static final Logger logger = LoggerFactory.getLogger(CloudEyeSample.class);
    public static final String ALARM_ID = "al1483387711418ZNpR8DX3g";

    @Test
    public void testGetMetrics() {

        List<? extends Metric> list1 = osclient.cloudEye().metrics().getList();
        logger.info("All metrics: {}", list1);
//
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

        List<? extends Alarm> list1 = osclient.cloudEye().alarms().getList();
        logger.info("All alarms: {}", list1);

        AlarmFilterOptions config = AlarmFilterOptions.create();
        AlarmFilterOptions options = config.limit(5);
        options.order(OrderType.ASC);
//        options.start("al1483387711418ZNpR8DX3g");

        List<? extends Alarm> list2 = osclient.cloudEye().alarms().getList(options);
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

}


