package org.openstack4j.api.cloudeye;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;

import java.util.List;

public interface AlarmService extends RestService {
    List<? extends Alarm> getList();
    List<? extends Alarm> getList(AlarmFilterOptions options);
}
