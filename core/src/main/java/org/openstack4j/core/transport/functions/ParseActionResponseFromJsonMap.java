package org.openstack4j.core.transport.functions;

import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;

import com.google.common.base.Function;

/**
 * Attempts to Parse a JSON Map created from an error response and map the message to an ActionResponse.  
 * 
 * @author Jeremy Unruh
 */
public class ParseActionResponseFromJsonMap implements Function<Map<String, Object>, ActionResponse>{

    public static final ParseActionResponseFromJsonMap INSTANCE = new ParseActionResponseFromJsonMap();
    private static final String KEY_MESSAGE = "message";
    
    /**
     * Parses the JSON Map for an Error message.  An OpenStack error response typically is a Map of Map containing a single key
     * which is "error", "badRequest", etc which contains a value of another Map containing the underlying message
     * 
     * @param map the JSON Map 
     * @return ActionResponse or null if the map could not be parsed
     */
    @SuppressWarnings("unchecked")
    @Override
    public ActionResponse apply(Map<String, Object> map) {
        if (map == null || map.isEmpty())
            return null;
        
        for (String key : map.keySet()) {
            if (Map.class.isAssignableFrom(map.get(key).getClass())) {
                Map<String, Object> inner = (Map<String, Object>) map.get(key);
                if (inner.containsKey(KEY_MESSAGE)) {
                    String msg = String.valueOf(inner.get(KEY_MESSAGE));
                    return ActionResponse.actionFailed(msg);
                }
            }
        }
        return null;
    }

}
