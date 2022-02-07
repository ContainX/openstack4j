package org.openstack4j.model.network.options;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author bboyHan
 */
public class BaseListOptions<T> {

    protected Map<String, List<Object>> queryParams = Maps.newHashMap();

    protected void putParams(String key, Object value) {
        checkNotNull(key);

        if (value != null) {
            List<Object> list;
            if (queryParams.containsKey(key)) {
                list = queryParams.get(key);
            } else {
                list = new ArrayList<>();
            }
            list.add(value);
            queryParams.put(key, list);
        }
    }

    public Map<String, List<Object>> getOptions() {
        return queryParams;
    }
}
