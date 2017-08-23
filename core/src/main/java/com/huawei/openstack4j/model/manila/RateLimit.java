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
package com.huawei.openstack4j.model.manila;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.core.transport.HttpMethod;

/**
 * Rate limits control the frequency at which users can issue specific API requests.
 * Administrators use rate limiting to configure limits on the type and number of API calls that can be made in a
 * specific time interval.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface RateLimit {
    enum TimeIntervalUnit {
        SECOND, MINUTE, HOUR, DAY;
    }

    /**
     * @return a human-readable URI of a rate limit
     */
    String getUri();

    /**
     * @return an API regular expression
     */
    String getRegex();

    /**
     * @return the limit object of this rate limit
     */
    List<? extends Limit> getLimit();

    interface Limit {
        /**
         * @return the number of API requests that are allowed during a time interval
         */
        int getValue();

        /**
         * @return the HTTP method for the API request
         */
        HttpMethod getVerb();

        /**
         * @return the remaining number of allowed requests
         */
        int getRemaining();

        /**
         * @return the time interval during which a number of API requests are allowed
         */
        TimeIntervalUnit getUnit();

        /**
         * @return the date and time stamp when next issues are available
         */
        Date getNextAvailable();
    }
}
