package com.huawei.openstack4j.api.cloudeye;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.cloudeye.*;
import com.huawei.openstack4j.model.common.ActionResponse;

/**
 * Created by coa.ke on 6/24/17.
 */
public interface MetricDataService extends RestService {
    /**
     * @param namespace Metric's namespace, such as Elastic Cloud Server (SYS.ECS)
     * @param metric_name Metric's name, such as cpu_util
     * @param from The start time for the data, UNIX timestamp, in milliseconds.
     * @param to The end time for the data, UNIX timestamp, in milliseconds.
     * @param period The granularity of monitoring the data. Ranges: 1, real-time data; 300,5 minutes; 1200, 20 minutes; 3600, 1 hour;14400, 4 hours;86400 1 day
     * @param filter Data aggregation way, support values are average, variance, min, max.
     * @param dimValues The index of dimension, supports up to three dimensions at most, the string format = key, value. For example: instance_id, 6f3c6f91-4b24-4e1b-b7d1-a94ac1cb011d
     * @return
     */
    MetricAggregation get(String namespace, String metric_name, Date from, Date to, Period period, Filter filter, String[] dimValues);
    ActionResponse add(List<? extends MetricData> metrics);
}
