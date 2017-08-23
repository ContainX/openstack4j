package com.huawei.openstack4j.model.cloudeye;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Created by coa.ke on 6/24/17.
 */
public interface AlarmAction extends ModelEntity {
    AlarmType getType();
    List<String> getNotificationlist();
}
