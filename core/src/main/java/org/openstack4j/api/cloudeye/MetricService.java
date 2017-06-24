package org.openstack4j.api.cloudeye;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;

import java.util.List;

public interface MetricService extends RestService {

    List<? extends Metric> getList();
    List<? extends Metric> getList(MetricFilterOptions options);
    List<? extends Metric> getFavoriteList();
	
}
