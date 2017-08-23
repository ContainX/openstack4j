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
package com.huawei.openstack4j.openstack.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.ByteStreams;

import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.common.DLPayload;

/**
 * A Payload which encapsulates downstream data
 * 
 * @author Jeremy Unruh
 */
public class DLPayloadEntity implements DLPayload {

    private final HttpResponse response;

    private DLPayloadEntity(HttpResponse response) {
        this.response = response;
    }

    public static DLPayloadEntity create(HttpResponse response) {
        return new DLPayloadEntity(response);
    }

	@Override
	public HttpResponse getHttpResponse() {
		return response;
	}
	
    @Override
    public InputStream getInputStream() {
        return response.getInputStream();
    }

    @Override
    public void writeToFile(File file) throws IOException {
        checkNotNull(file);
        try(InputStream inputStream = response.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(file) ){
            ByteStreams.copy(inputStream, fileOutputStream);
        }
    }

}
