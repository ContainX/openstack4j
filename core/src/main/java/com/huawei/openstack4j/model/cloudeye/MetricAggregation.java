package com.huawei.openstack4j.model.cloudeye;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Created by coa.ke on 6/24/17.
 */
public interface MetricAggregation extends ModelEntity {
    List<? extends DataPoint> getDatapoints();
    String getMetricName();
}
