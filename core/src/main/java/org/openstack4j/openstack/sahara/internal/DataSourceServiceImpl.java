/*******************************************************************************
 * 	Copyright 2016 HuaWei and OTC                                 
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
package org.openstack4j.openstack.sahara.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.openstack4j.api.sahara.DataSourceService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.DataSource;
import org.openstack4j.model.sahara.options.DataSourceListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaDataSource;
import org.openstack4j.openstack.sahara.domain.SaharaDataSource.DataSources;
import org.openstack4j.openstack.sahara.domain.SaharaDataSourceUnwrapped;

/**
 * Sahara Data Processing Operations
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class DataSourceServiceImpl extends BaseSaharaServices implements DataSourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends DataSource> list(DataSourceListOptions options) {
        return get(DataSources.class, uri("/data-sources")).params(options.getOptions()).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource get(String datasourceId) {
        checkNotNull(datasourceId);
        return get(SaharaDataSource.class, uri("/data-sources/%s", datasourceId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource create(DataSource datasource) {
        checkNotNull(datasource);
        SaharaDataSourceUnwrapped unwrapped = new SaharaDataSourceUnwrapped(datasource);
        return post(SaharaDataSource.class, uri("/data-sources"))
                     .entity(unwrapped)  // setup request
                     .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String datasourceId) {
        checkNotNull(datasourceId);
        return deleteWithResponse(uri("/data-sources/%s", datasourceId)).execute();
    }

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public DataSource update(DataSource datasource) {
        checkNotNull(datasource);
        checkNotNull(datasource.getId(), "data-source `id` attribute is required");
//        checkNotNull(datasource.getType(), "data-source `type` attribute is required");
        checkNotNull(datasource.getURL(), "data-source `URL` attribute is required");
        SaharaDataSourceUnwrapped unwrapped = new SaharaDataSourceUnwrapped(datasource);
        return put(SaharaDataSource.class, uri("/data-sources/%s", datasource.getId())).entity(unwrapped).execute();
	}

}
