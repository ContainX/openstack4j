package org.openstack4j.openstack.storage.object.functions;

import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_LENGTH;
import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_TYPE;
import static org.openstack4j.model.storage.object.SwiftHeaders.ETAG;
import static org.openstack4j.model.storage.object.SwiftHeaders.LAST_MODIFIED;
import static org.openstack4j.openstack.internal.Parser.asLong;

import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.openstack4j.openstack.internal.Parser;
import org.openstack4j.openstack.storage.object.domain.SwiftObjectImpl;

import com.google.common.base.Function;

/**
 * Transforms an HttpResponse into a SwiftObject
 * 
 * @author Jeremy Unruh
 */
public class ParseObjectFunction implements Function<HttpResponse, SwiftObject> {

    private ObjectLocation location;
    
    private ParseObjectFunction(ObjectLocation location) {
        this.location = location;
    }
    
    public static ParseObjectFunction create(ObjectLocation location) {
        return new ParseObjectFunction(location);
    }
    
    
    @Override
    public SwiftObject apply(HttpResponse resp) {
        return SwiftObjectImpl.builder()
                  .name(location.getObjectName())
                  .containerName(location.getContainerName())
                  .mimeType(resp.header(CONTENT_TYPE))
                  .sizeBytes(asLong(resp.header(CONTENT_LENGTH)))
                  .eTag(resp.header(ETAG))
                  .metadata(MapWithoutMetaPrefixFunction.INSTANCE.apply(resp.headers()))
                  .lastModified(Parser.toRFC822DateParse(resp.header(LAST_MODIFIED)))
                  .build();
    }
    

}
