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
package com.huawei.openstack4j.model.storage.object.options;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * List options used for Object based queries
 * 
 * @author Jeremy Unruh
 */
public final class ObjectListOptions {

    private Map<String,String> queryParams = Maps.newHashMap();
    
    private ObjectListOptions() { }
    
    public static ObjectListOptions create() {
        return new ObjectListOptions();
    }
    
    /** 
     * list operation returns no more than this amount. 
     */
    public ObjectListOptions limit(int limit) {
       checkState(limit >= 0, "limit must be >= 0");
       checkState(limit <= 10000, "limit must be <= 10000");
       queryParams.put("limit", Integer.toString(limit));
       return this;
    }

    /** 
     * Objects greater in value than the specified marker are returned.
     */
    public ObjectListOptions marker(String marker) {
       queryParams.put("marker", checkNotNull(marker, "marker"));
       return this;
    }

    /** 
     * Objects less in value than the specified marker are returned.
     */
    public ObjectListOptions endMarker(String endMarker) {
       queryParams.put("end_marker", checkNotNull(endMarker, "endMarker"));
       return this;
    }

    /** 
     * Objects beginning with this substring are returned.
     */
    public ObjectListOptions startsWith(String prefix) {
       queryParams.put("prefix", checkNotNull(prefix, "prefix"));
       return this;
    }

    /** 
     * Objects nested in the container are returned.
     */
    public ObjectListOptions delimiter(char delimiter) {
       queryParams.put("delimiter", Character.toString(delimiter));
       return this;
    }

    /** 
     * Objects nested in the pseudo path are returned.
     */
    public ObjectListOptions path(String path) {
       queryParams.put("path", checkNotNull(path, "path"));
       return this;
    }
    
    public Map<String, String> getOptions() {
        return queryParams;
    }

}
