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
package com.huawei.openstack4j.model.compute.ext;

import com.huawei.openstack4j.model.common.BaseFilter;
import com.huawei.openstack4j.model.compute.ext.Migration.Status;

/**
 * Filter options used for Migration results
 * 
 * @author Jeremy Unruh
 */
public class MigrationsFilter extends BaseFilter {

    private MigrationsFilter() { 
    }
    
    public static MigrationsFilter create() {
        return new MigrationsFilter();
    }
    
    /**
     * Filters the response by host name 
     * 
     * @param host the host name
     * @return MigrationsFilter
     */
    public MigrationsFilter host(String host) {
        filter("host", host);
        return this;
    }
    
    /**
     * Filters the response by status.
     * 
     * @param status the status to filter by
     * @return MigrationsFilter
     */
    public MigrationsFilter status(Status status) {
        filter("status", status.name());
        return this;
    }
    
    /**
     * Filters the response by cell name 
     * 
     * @param cellName the cell name
     * @return MigrationsFilter
     */
    public MigrationsFilter cellName(String cellName) {
        filter("cell_name", cellName);
        return this;
    }
    
}
