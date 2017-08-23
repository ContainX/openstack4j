package com.huawei.openstack4j.model.cloudeye;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;

public interface DataPoint extends ModelEntity{
    Number getAverage();
    Number getVariance();
    Number getMin();
    Number getMax();
    Date getTimestamp();
    String getUnit();
}
