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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.DataSourceCredentials;

/**
 * Model represent attributes of DataSourceCredentials
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
@JsonRootName("credentials")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MapReduceDataSourceCredentials implements DataSourceCredentials {

    private static final long serialVersionUID = 1L;

    private String password;
    private String user;

    MapReduceDataSourceCredentials(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                   .add("password", password)
                   .add("user", user)
                   .toString();
    }

}
