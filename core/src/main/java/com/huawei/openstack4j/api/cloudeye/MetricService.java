package com.huawei.openstack4j.api.cloudeye;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.cloudeye.Metric;
import com.huawei.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;

public interface MetricService extends RestService {

    List<? extends Metric> getList();
    List<? extends Metric> getList(MetricFilterOptions options);
    List<? extends Metric> getFavoriteList();
	
}
