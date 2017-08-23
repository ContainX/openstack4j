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
package com.huawei.openstack4j.model.storage.block.options;

import java.util.List;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.model.common.functions.RangesToHeaderNameValue;
import com.huawei.openstack4j.model.common.header.HeaderNameValue;
import com.huawei.openstack4j.model.common.header.IfCondition;
import com.huawei.openstack4j.model.common.header.Range;

/**
 * Download options used to determine how the data is returned or if it is returned depending on various conditional
 * options
 * 
 * @author Jeremy Unruh
 */
public class DownloadOptions {

    List<HeaderNameValue> headers = Lists.newArrayList();
    
    private DownloadOptions() { 
    }
    
    public static DownloadOptions create() {
        return new DownloadOptions();
    }
    
    /**
     * Download select ranges of bytes 
     * 
     * @param ranges one or more Range objects
     * @return DownloadOptions for method chaining
     */
    public DownloadOptions range(Range... ranges) {
        HeaderNameValue h = RangesToHeaderNameValue.INSTANCE.apply(ranges);
        if (h != null)
            headers.add(h);
        return this;
    }
    
    /**
     * Adds a user specified header name and value
     * 
     * @param name the header name
     * @param value the header value
     * @return DownloadOptions for method chaining
     */
    public DownloadOptions header(String name, Object value) {
        if (name != null) {
            headers.add(new HeaderNameValue(name, value));
        }
        return this;
    }
    
    /**
     * Adds one or more If based conditions to the header chain to offer conditional matching 
     * before the data is streamed
     * 
     * @param condition one or more IfCondition objects
     * @return DownloadOptions for method chaining
     * @see http://www.ietf.org/rfc/rfc2616.txt
     */
    public DownloadOptions conditions(IfCondition... condition) {
        if (condition != null) {
            for (IfCondition c : condition)
                headers.add(c.toHeader());
        }
        return this;
    }
    
    /**
     * @return all headers configured from this options object
     */
    public List<HeaderNameValue> getHeaders() {
        return headers;
    }
}
