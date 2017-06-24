package org.openstack4j.openstack.cloudeye.internal;

import org.openstack4j.api.cloudeye.AlarmService;
import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeAlarm;

import java.util.List;

import static org.openstack4j.core.transport.ClientConstants.PATH_ALARMS;

public class CloudEyeAlarmServiceImpl extends BaseCloudEyeServices
		implements AlarmService {


	@Override
	public List<? extends Alarm> getList() {
		return get(CloudEyeAlarm.CloudEyeAlarms.class, uri(PATH_ALARMS)).execute().getList();
	}

	@Override
	public List<? extends Alarm> getList(AlarmFilterOptions options) {
		return get(CloudEyeAlarm.CloudEyeAlarms.class, uri(PATH_ALARMS)).params(options.getOptions()).execute().getList();
	}
}
