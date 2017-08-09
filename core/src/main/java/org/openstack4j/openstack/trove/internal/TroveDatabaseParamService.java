/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC                                       
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
package org.openstack4j.openstack.trove.internal;

import java.util.List;
import java.util.Map;

import org.openstack4j.openstack.trove.domain.DatabaseParam;
import org.openstack4j.openstack.trove.domain.DatabaseParam.Parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The implementation of manipulation of {@link DatabaseParam}
 *
 * @author QianBiao.NG
 * @date   2017-08-03 11:06:34
 */
public class TroveDatabaseParamService extends BaseTroveServices {

	/**
	 * list all configuration parameters of a specification version	 of datastore
	 * 
	 * @param dataStoreVersionId	datastore version identifier
	 * @return a list of {@link DatabaseParam} instances 
	 */
	public List<DatabaseParam> list(String dataStoreVersionId) {
		return get(Parameters.class, uri("/datastores/versions/%s/parameters", dataStoreVersionId)).execute().getList();
	}

	/**
	 * 
	 * get the details of a configuration parameter
	 * 
	 * @param dataStoreVersionId	datastore version identifier
	 * @param paramName				datastore configuration parameter name
	 * @return an instance of {@link DatabaseParam}
	 */
	public DatabaseParam get(String dataStoreVersionId, String paramName) {
		return get(Parameters.class, uri("/datastores/versions/%s/parameters/%s", dataStoreVersionId, paramName))
				.execute().getList().get(0);
	}

	/**
	 * 
	 * @param instanceId
	 * @return
	 */
	public Map<String, String> getDefaultParamsByInstance(String instanceId) {
		GetDefaultParamsResponse response = get(GetDefaultParamsResponse.class,
				uri("/instances/%s/configuration", instanceId)).execute();
		return response.getParams();
	}

	@JsonRootName("instance")
	static class GetDefaultParamsResponse {

		@JsonProperty("configuration")
		Map<String, String> params;

		public Map<String, String> getParams() {
			return params;
		}

		public void setParams(Map<String, String> params) {
			this.params = params;
		}

	}

}
