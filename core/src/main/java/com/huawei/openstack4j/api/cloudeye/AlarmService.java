package com.huawei.openstack4j.api.cloudeye;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;

public interface AlarmService extends RestService {
	
	/**
	 * list alarm
	 * @return
	 */
    List<? extends Alarm> list();
    
    /**
     * list alarm with filter options
     * 
     * @param options filter options
     * @return
     */
    List<? extends Alarm> list(AlarmFilterOptions options);

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
