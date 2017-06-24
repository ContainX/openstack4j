package org.openstack4j.openstack.cloudeye.internal;

import org.openstack4j.api.cloudeye.AlarmService;
import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeAlarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_ALARMS;
import static org.openstack4j.core.transport.ClientConstants.PATH_ALARMS_ACTION;

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

	@Override
	public List<? extends Alarm> get(String alarmId) {
		checkNotNull(alarmId);
		return get(CloudEyeAlarm.CloudEyeAlarms.class, PATH_ALARMS, "/", alarmId).execute().getList();
	}

	@Override
	public ActionResponse startAlarm(String alarmId) {
		checkNotNull(alarmId);
		Map<String, Boolean> entity = new HashMap<>();
		entity.put("alarm_enabled", true);
		return putWithResponse(PATH_ALARMS, "/", alarmId, PATH_ALARMS_ACTION).entity(entity).execute();
	}

	@Override
	public ActionResponse stopAlarm(String alarmId) {
		checkNotNull(alarmId);
		Map<String, Boolean> entity = new HashMap<>();
		entity.put("alarm_enabled", false);
		return putWithResponse(PATH_ALARMS, "/", alarmId, PATH_ALARMS_ACTION).entity(entity).execute();
	}

	@Override
	public ActionResponse deleteAlarm(String alarmId) {
		return deleteWithResponse(PATH_ALARMS, "/", alarmId).execute();
	}
}
