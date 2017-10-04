package com.huawei.openstack4j.functional.cloudeye;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.cloudeye.OrderType;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;

public class AlarmTest extends AbstractTest {

	private Alarm alarm = null;

	@BeforeClass
	public void testListAlarms() {

		List<? extends Alarm> all = osclient.cloudEye().alarms().list();
		List<String> collect = all.stream().map(alarm -> alarm.getAlarmId()).collect(Collectors.toList());

		AlarmFilterOptions config = AlarmFilterOptions.create();
		AlarmFilterOptions options = config.limit(5);
		options.order(OrderType.ASC);
		// options.start("al1483387711418ZNpR8DX3g");
		List<? extends Alarm> limit = osclient.cloudEye().alarms().list(options);
		for (Alarm alarm : limit) {
			Assert.assertTrue(collect.contains(alarm.getAlarmId()));
		}

		this.alarm = limit.get(0);
	}

	@Test
	public void testGetAlarm() {
		Alarm get = osclient.cloudEye().alarms().get(alarm.getAlarmId());
		Assert.assertEquals(get.getAlarmId(), alarm.getAlarmId());
		Assert.assertEquals(get.getAlarmDescription(), alarm.getAlarmDescription());
		Assert.assertEquals(get.getAlarmName(), alarm.getAlarmName());
		Assert.assertEquals(get.getAlarmEnabled(), alarm.getAlarmEnabled());
		Assert.assertEquals(get.getAlarmState(), alarm.getAlarmState());
	}

	@Test
	public void testStartStopAlarm() {
		// 假如是启用状态，先暂停
		if (this.alarm.getAlarmEnabled()) {
			ActionResponse response = osclient.cloudEye().alarms().stopAlarm(alarm.getAlarmId());
			Assert.assertTrue(response.isSuccess());
		}
		
		ActionResponse response = osclient.cloudEye().alarms().startAlarm(alarm.getAlarmId());
		Assert.assertTrue(response.isSuccess());
		
		ActionResponse response2 = osclient.cloudEye().alarms().stopAlarm(alarm.getAlarmId());
		Assert.assertTrue(response2.isSuccess());
		
		if (this.alarm.getAlarmEnabled()) {
			ActionResponse response3 = osclient.cloudEye().alarms().startAlarm(alarm.getAlarmId());
			Assert.assertTrue(response3.isSuccess());
		}
	}


	/** delete 接口不能自动执行，不然会删除环境中的数据，如需测试，需要手动执行
	@Test
	public void testDeleteAlarm() {
		ActionResponse response = osclient.cloudEye().alarms().deleteAlarm(alarm.getAlarmId());
		Assert.assertTrue(response.isSuccess());
	}
	*/

}
