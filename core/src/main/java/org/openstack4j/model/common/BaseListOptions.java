package org.openstack4j.model.common;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base Filter class for building Filter Request options
 *
 * @author bboyHan
 */
public class BaseListOptions<T> {

    /**
     * filter the values (Prototype)
     */
    protected Map<String, List<Object>> repeatableQueryParams = Maps.newHashMap();

    /**
     *filter the values (Singleton)
     */
    protected Map<String, Object> singleQueryParams = Maps.newHashMap();

    protected void filter(String key, Object value) {
        checkNotNull(key);

        if (value != null) {
            List<Object> list;
            if (repeatableQueryParams.containsKey(key)) {
                list = repeatableQueryParams.get(key);
            } else {
                list = new ArrayList<>();
            }
            list.add(value);
            repeatableQueryParams.put(key, list);

            singleQueryParams.put(key, value);
        }
    }

    public Map<String, List<Object>> getRepeatableOptions() {
        return repeatableQueryParams;
    }

    public Map<String, Object> getSingleOptions() {
        return singleQueryParams;
    }
}
