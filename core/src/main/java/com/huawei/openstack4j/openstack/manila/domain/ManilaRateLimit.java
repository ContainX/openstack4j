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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.model.manila.RateLimit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Rate limits control the frequency at which users can issue specific API requests.
 * Administrators use rate limiting to configure limits on the type and number of API calls that can be made in a
 * specific time interval.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ManilaRateLimit implements RateLimit {
    private static final long serialVersionUID = 1L;

    private String uri;
    private String regex;
    private List<ManilaLimit> limit;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUri() {
        return uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRegex() {
        return regex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Limit> getLimit() {
        return limit;
    }

    public static class ManilaLimit implements Limit, Serializable {
        private static final long serialVersionUID = 1L;

        private int value;
        private HttpMethod verb;
        private int remaining;
        private TimeIntervalUnit unit;
        @JsonProperty("next-available")
        private Date nextAvailable;

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public HttpMethod getVerb() {
            return verb;
        }

        @Override
        public int getRemaining() {
            return remaining;
        }

        @Override
        public TimeIntervalUnit getUnit() {
            return unit;
        }

        @Override
        public Date getNextAvailable() {
            return nextAvailable;
        }
    }
}
