package org.openstack4j.openstack.storage.object.functions;

import java.util.Map;

import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.openstack.storage.object.domain.MetaHeaderRequestWrapper;

import com.google.common.base.Function;

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
        Map<String, String> metadata = input.getMetadata();
        
        for (String key : metadata.keySet()) {
            String keyLower = key.toLowerCase();
            String value = metadata.get(key);
            if (keyLower.startsWith(input.getPrefix()))
                input.getRequest().toBuilder().header(keyLower, value);
            else
                input.getRequest().toBuilder().header(String.format("%s%s", input.getPrefix(), keyLower), value);
        }
        return input.getRequest();
    }

}
