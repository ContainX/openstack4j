package com.huawei.openstack4j.model.cloudeye;

import com.huawei.openstack4j.model.ModelEntity;

public interface Resource extends ModelEntity{
    QuotaType getType();
    Integer getUsed();
    String getUnit();
    Integer getQuota();
}
