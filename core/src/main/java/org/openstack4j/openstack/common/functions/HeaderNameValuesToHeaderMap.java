package org.openstack4j.openstack.common.functions;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.header.HeaderNameValue;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * Transforms a List of HeaderNameValue objects into a Header compatible Map
 * 
 * @author Jeremy Unruh
 */
public class HeaderNameValuesToHeaderMap implements Function<List<HeaderNameValue>, Map<String, Object>> {

    public static HeaderNameValuesToHeaderMap INSTANCE = new HeaderNameValuesToHeaderMap();
    
    @Override
    public Map<String, Object> apply(List<HeaderNameValue> input) {
        Map<String, Object> result = Maps.newHashMap();
        
        for (HeaderNameValue nv : input) {
            result.put(nv.getName(), nv.getValue());
        }
        return result;
    }

}
