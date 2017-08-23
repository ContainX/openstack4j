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
package com.huawei.openstack4j.model.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.huawei.openstack4j.core.transport.HttpResponse;

/**
 * A Payload which encapsulates downstream data
 * 
 * @author Jeremy Unruh
 */
public interface DLPayload {

    /**
     * The HttpResponse
     * 
     * @return the HttpResponse
     */
	HttpResponse getHttpResponse();
	
    /**
     * The raw inputstream
     * 
     * @return the inputstream
     */
    InputStream getInputStream();
    
    /**
     * Writes the current stream to the specified {@code file}
     * 
     * @param file the file to write to
     * @throws IOException
     */
    void writeToFile(File file) throws IOException;
}
