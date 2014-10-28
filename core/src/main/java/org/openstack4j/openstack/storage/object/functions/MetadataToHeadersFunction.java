package org.openstack4j.openstack.storage.object.functions;

import java.util.Map;

import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.openstack.storage.object.domain.MetaHeaderRequestWrapper;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * Transforms a MetaHeaderRequestWrapper which applies headers based on a prefix to the outbound
 * HttpRequest
 * 
 * @author Jeremy Unruh
 */
public class MetadataToHeadersFunction<R> implements Function<MetaHeaderRequestWrapper<R>, HttpRequest<R>> {

    public static <R> MetadataToHeadersFunction<R> create() {
        return new MetadataToHeadersFunction<R>();
    }
    
    @Override
    public HttpRequest<R> apply(MetaHeaderRequestWrapper<R> input) {
        Map<String, String> headers = apply(input.getPrefix(), input.getMetadata());
        return input.getRequest().toBuilder().headers(headers).build();
    }
    
    /**
     * Transforms metadata raw values into header form values
     * 
     * @param prefix the prefix used for headers
     * @param metadata the map of metadata 
     * @return map of metadata in header format
     */
    public Map<String, String> apply(String prefix, Map<String, String> metadata) {
        
        Map<String, String> headers = Maps.newHashMap();
        
        for (String key : metadata.keySet()) {
            String keyLower = key.toLowerCase();
            String value = metadata.get(key);
            if (keyLower.startsWith(prefix))
                headers.put(keyLower, value);
            else
                headers.put(String.format("%s%s", prefix, keyLower), value);
        }
        return headers;
    }

}
