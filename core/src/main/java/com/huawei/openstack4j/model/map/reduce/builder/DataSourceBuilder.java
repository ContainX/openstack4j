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
package com.huawei.openstack4j.model.map.reduce.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.map.reduce.DataSource;
import com.huawei.openstack4j.model.map.reduce.DataSource.DataSourceType;

/**
 * Builder interface used for {@link DataSource} object.
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface DataSourceBuilder extends Builder<DataSourceBuilder, DataSource> {
	
    DataSourceBuilder id(String dataSourceId);

    /**
     * See {@link DataSource#getDescription()}
     * 
     * @param description the description of the data source
     * @return DataSourceBuilder
     */
    DataSourceBuilder description(String description);

    /**
     * See {@link DataSource#getURL()}
     * 
     * @param url the URL of the data source
     * @return DataSourceBuilder
     */
    DataSourceBuilder url(String url);

    /**
     * See {@link DataSource#getType()}
     * 
     * @param type the type of the data source
     * @return DataSourceBuilder
     */
    DataSourceBuilder type(DataSourceType type);

    /**
     * See {@link DataSource#getName()}
     * 
     * @param name the name of the data source
     * @return DataSourceBuilder
     */
    DataSourceBuilder name(String name);
    
    /**
     * See {@link DataSource#isPublic()}
     * 
     * @param isPublic is data source public
     * @return DataSourceBuilder
     */
    DataSourceBuilder isPublic(boolean isPublic);
    
    /**
     * See {@link DataSource#isProtected()}
     * 
     * @param isProtected is data source protected
     * @return DataSourceBuilder
     */
    DataSourceBuilder isProtect(boolean isProtected);

    /**
     * See {@link DataSource#getCredentials()}
     * 
     * @param user username of the credentials
     * @param password password of the credentials
     * @return DataSourceBuilder
     */
    DataSourceBuilder credentials(String user, String password);
    

}
