/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package org.openstack4j.openstack.cloud.trace.v1.internal;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.cloud.trace.v1.domain.Trace;
import org.openstack4j.openstack.cloud.trace.v1.domain.Trace.Traces;
import org.openstack4j.openstack.cloud.trace.v1.options.TraceListOptions;
import org.testng.util.Strings;

import com.google.common.base.Preconditions;

/**
 * 
 *
 * @author QianBiao.NG
 * @date   2017-07-13 09:31:10
 */
public class TraceService extends BaseCloudTraceServices implements RestService {

	/**
	 * List traces
	 * 
	 * @return
	 */
	public List<Trace> list(String trackerName, TraceListOptions options) {
		Preconditions.checkNotNull(!Strings.isNullOrEmpty(trackerName), "parameter `trackerName` should not be empty");
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(Traces.class, uri("/%s/trace", trackerName)).params(params).execute().getList();
	}

}
