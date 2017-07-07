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
package org.openstack4j.openstack.sahara.internal;

import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openstack4j.api.sahara.ClusterService;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.propagation.PropagateOnStatus;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.model.sahara.NodeGroup;
import org.openstack4j.openstack.sahara.domain.SaharaCluster;
import org.openstack4j.openstack.sahara.domain.SaharaCluster.Clusters;
import org.openstack4j.openstack.sahara.domain.SaharaClusterUnwrapped;
import org.openstack4j.openstack.sahara.domain.actions.SaharaActions.AddNodeGroupAction;
import org.openstack4j.openstack.sahara.domain.actions.SaharaActions.ResizeNodeGroupAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Sahara Data Processing Operations
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public class ClusterServiceImpl extends BaseSaharaServices implements ClusterService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Cluster> list() {
		return get(Clusters.class, uri("/clusters")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cluster get(String clusterId) {
		checkNotNull(clusterId);
		return get(SaharaCluster.class, uri("/clusters/%s", clusterId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cluster create(Cluster cluster) {
		checkNotNull(cluster);
		SaharaClusterUnwrapped unwrapped = new SaharaClusterUnwrapped(cluster);
		return post(SaharaCluster.class, uri("/clusters")).entity(unwrapped) // setup request
				.execute();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String clusterId) {
		checkNotNull(clusterId);
		return deleteWithResponse(uri("/clusters/%s", clusterId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cluster resizeNodeGroup(String clusterId, String groupName, int count) {
		checkNotNull(clusterId);
		checkNotNull(groupName);
		return put(SaharaCluster.class, uri("/clusters/%s", clusterId))
				.entity(new ResizeNodeGroupAction(groupName, count))
				.execute(ExecutionOptions.<SaharaCluster> create(PropagateOnStatus.on(404))); // Use respongse
																								// progagation for "Not
																								// found" status to
																								// throw exception
																								// instead of return
																								// null
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cluster addNodeGroup(String clusterId, NodeGroup nodeGroup) {
		checkNotNull(clusterId);
		checkNotNull(nodeGroup);
		// Use respongse progagation for "Not found" status to throw exception instead of return null
		return put(SaharaCluster.class, uri("/clusters/%s", clusterId)).entity(new AddNodeGroupAction(nodeGroup))
				.execute(ExecutionOptions.<SaharaCluster> create(PropagateOnStatus.on(404)));
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse expand(String clusterId, int amount) {
		checkNotNull(clusterId, "the `clusterId` parameter should not be null");
		checkState(amount > 0, "the `amount` parameter should be greater than zero");
		ScaleClusterAction expand = ScaleClusterAction.create().expand(amount);
		return putWithResponse(uri("/cluster_infos/%s", clusterId)).entity(expand).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse reduce(String clusterId, int amount, List<String> includes, List<String> excludes) {
		checkNotNull(clusterId, "the `clusterId` parameter should not be null");
		checkState(amount > 0, "the `amount` parameter should be greater than zero");
		ScaleClusterAction reduce = ScaleClusterAction.create().reduce(amount).includes(includes).excludes(excludes);
		return putWithResponse(uri("/cluster_infos/%s", clusterId)).entity(reduce).execute();
	}
	

	private static class ScaleClusterAction {

		@JsonProperty("service_id")
		String serviceId = "";

		@JsonProperty("plan_id")
		String planId = "";

		@JsonProperty("parameters")
		HashMap<String, Object> parameters = Maps.newHashMap();

		@JsonProperty("previous_values")
		HashMap<String, Object> previousValues = Maps.newHashMap();

		@JsonIgnore
		ArrayList<String> includes = Lists.newArrayList();

		@JsonIgnore
		ArrayList<String> excludes = Lists.newArrayList();

		public ScaleClusterAction() {
			previousValues.put("plan_id", "");

			parameters.put("order_id", "");
			parameters.put("node_id", "node_orderadd");
			parameters.put("include_instances", includes);
			parameters.put("exclude_instances", excludes);
		}

		public static ScaleClusterAction create() {
			return new ScaleClusterAction();
		}

		public ScaleClusterAction expand(int amount) {
			parameters.put("instances", amount);
			parameters.put("scale_type", "scale_out");
			return this;
		}

		public ScaleClusterAction reduce(int amount) {
			parameters.put("instances", amount);
			parameters.put("scale_type", "scale_in");
			return this;
		}

		public ScaleClusterAction includes(List<String> instanceIds) {
			if (instanceIds != null && instanceIds.size() > 0) {
				includes.addAll(instanceIds);
			}
			return this;
		}

		public ScaleClusterAction excludes(List<String> instanceIds) {
			if (instanceIds != null && instanceIds.size() > 0) {
				excludes.addAll(instanceIds);
			}
			return this;
		}

	}

}
