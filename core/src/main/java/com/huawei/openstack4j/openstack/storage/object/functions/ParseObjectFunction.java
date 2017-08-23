/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.storage.object.functions;

import static com.huawei.openstack4j.model.storage.object.SwiftHeaders.*;
import static com.huawei.openstack4j.openstack.internal.Parser.*;

import com.google.common.base.Function;

import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.storage.object.SwiftObject;
import com.huawei.openstack4j.model.storage.object.options.ObjectLocation;
import com.huawei.openstack4j.openstack.internal.Parser;
import com.huawei.openstack4j.openstack.storage.object.domain.SwiftObjectImpl;

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
