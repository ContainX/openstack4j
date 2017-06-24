package org.openstack4j.api.cloudeye;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;

import java.util.List;

public interface AlarmService extends RestService {
    List<? extends Alarm> getList();
    List<? extends Alarm> getList(AlarmFilterOptions options);

    /**
     * @param alarmId The id for the alarm
     * @return
     */
    List<? extends Alarm> get(String alarmId);

    /**
     * @param alarmId The id for the alarm which need to be started
     * @return
     */
    ActionResponse startAlarm(String alarmId);

    /**
     * @param alarmId The id for the alarm which need to be stopped
     * @return
     */
    ActionResponse stopAlarm(String alarmId);

    /**
     * @param alarmId The id for the alarm which need to be deleted
     * @return
     */
    ActionResponse deleteAlarm(String alarmId);


}
