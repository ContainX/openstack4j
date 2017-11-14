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
package com.huawei.openstack4j.openstack.map.reduce.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import com.huawei.openstack4j.api.map.reduce.MapReducePluginService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.map.reduce.ClusterTemplate;
import com.huawei.openstack4j.model.map.reduce.Plugin;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReducePlugin;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReducePlugin.MapReducePlugins;

/**
 * The implementation of manipulation of {@link Plugin}
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public class MapReducePluginServiceImpl extends BaseMapReduceServices implements MapReducePluginService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Plugin> list() {
		return get(MapReducePlugins.class, uri("/plugins")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Plugin get(String name) {
		checkNotNull(name);
		return get(MapReducePlugin.class, uri("/plugins/%s", name)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Plugin get(String name, String version) {
		checkNotNull(name);
		checkNotNull(version);
		return get(MapReducePlugin.class, uri("/plugins/%s/%s", name, version)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClusterTemplate convertConfig(String name, String version, String templateName, Payload<?> payload) {
		checkNotNull(name);
		checkNotNull(version);
		checkNotNull(templateName);
		return post(MapReduceClusterTemplate.class,
				uri("/plugins/%s/%s/convert-config/%s", name, version, templateName)).entity(payload) // setup request
						.execute(ExecutionOptions.<MapReduceClusterTemplate> create(PropagateOnStatus.on(404))); // Use
																													// respongse
																													// progagation
																													// for
																													// "Not
																													// found"
																													// status
																													// to
																													// throw
																													// exception
																													// instead
																													// of
																													// return
																													// null

	}

}
