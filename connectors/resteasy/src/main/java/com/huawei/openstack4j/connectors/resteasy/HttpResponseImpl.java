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
package com.huawei.openstack4j.connectors.resteasy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.client.ClientResponse;

import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.HttpEntityHandler;
import com.huawei.openstack4j.core.transport.HttpResponse;

public class HttpResponseImpl implements HttpResponse {

    private final ClientResponse<?> response;

    private HttpResponseImpl(ClientResponse<?> response) {
        this.response = response;
    }

    /**
     * Wrap the given Response
     *
     * @param response the response
     * @return the HttpResponse
     */
    public static HttpResponseImpl wrap(ClientResponse<?> response) {
        return new HttpResponseImpl(response);
    }

    /**
     * Unwrap and return the original Response
     *
     * @return the response
     */
    public ClientResponse<?> unwrap() {
        return response;
    }

    /**
     * Gets the entity and Maps any errors which will result in a ResponseException
     *
     * @param <T> the generic type
     * @param returnType the return type
     * @return the entity
     */
    public <T> T getEntity(Class<T> returnType) {
        return getEntity(returnType, null);
    }

    /**
     * Gets the entity and Maps any errors which will result in a ResponseException
     *
     * @param <T> the generic type
     * @param returnType the return type
     * @param options execution based options
     * @return the entity
     */
    @Override
    public <T> T getEntity(Class<T> returnType, ExecutionOptions<T> options) {
        return HttpEntityHandler.handle(this, returnType, options, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStatus() {
        return response.getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatusMessage() {
        return response.getResponseStatus().getReasonPhrase();
    }

    /**
     * @return the input stream
     */
    public InputStream getInputStream() {
        return response.getEntity(InputStream.class);
    }

    /**
     * Returns a Header value from the specified name key
     *
     * @param name the name of the header to query for
     * @return the header as a String or null if not found
     */
    public String header(String name) {
        return response.getHeaders().getFirst(name);
    }

    /**
     * @return the a Map of Header Name to Header Value
     */
    public Map<String, String> headers() {
        Map<String, String> headers = new HashMap<String, String>();
        MultivaluedMap<String, String> responseHeaders = response.getHeaders();

        for (String key : responseHeaders.keySet()) {
            headers.put(key, responseHeaders.getFirst(key).toString());
        }

        return headers;
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {
        return response.getEntity(typeToReadAs);
    }

    @Override
    public void close() throws IOException {
        response.releaseConnection();
    }
    
    @Override
    public String getContentType() {
        return header(ClientConstants.HEADER_CONTENT_TYPE);
    }
}
