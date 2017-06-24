package org.openstack4j.openstack.cloudeye.internal;

import com.google.common.collect.Maps;
import org.openstack4j.model.cloudeye.OrderType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coa.ke on 6/24/17.
 */
public class AlarmFilterOptions {
    private Map<String, Object> queryParams = Maps.newHashMap();

    private AlarmFilterOptions() {
    }

    public static AlarmFilterOptions create() {
        return new AlarmFilterOptions();
    }

    public AlarmFilterOptions limit(Integer limit) {
        return add("limit", limit);
    }


    /**
     * @param start The paging start value in the format: namespace.metric_name.key: value
     * @return
     */
    public AlarmFilterOptions start(String start) {
        return add("start", start);
    }

    public AlarmFilterOptions order(OrderType orderType) {
        return add("order", orderType.value());
    }

    private AlarmFilterOptions add(String param, Object value) {
        if (value != null)
            this.queryParams.put(param, value);
        return this;
    }

    public Map<String, Object> getOptions() {
        return queryParams;
    }
}