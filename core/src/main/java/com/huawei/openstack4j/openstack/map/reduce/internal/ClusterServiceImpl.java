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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.api.map.reduce.ClusterService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.Cluster;
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.openstack.map.reduce.constants.BillingType;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceCluster;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceCluster.Clusters;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterInfo;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterCreate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterCreateResult;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterUnwrapped;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExeCreate;
import com.huawei.openstack4j.openstack.map.reduce.domain.actions.MapReduceActions.AddNodeGroupAction;
import com.huawei.openstack4j.openstack.map.reduce.domain.actions.MapReduceActions.ResizeNodeGroupAction;

/**
 * The implementation of manipulation of {@link Cluster}
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public class ClusterServiceImpl extends BaseMapReduceServices implements ClusterService {
	
	/**
	 * Sahara original get - replaced with Huawei get
	@Override
	public Cluster get(String clusterId) {
		checkNotNull(clusterId);
		return get(MapReduceCluster.class, uri("/clusters/%s", clusterId)).execute();
	} */


	/**
	 * {@inheritDoc}
	 */
	public MapReduceClusterInfo get(String clusterId) {
		checkNotNull(!Strings.isNullOrEmpty(clusterId), "parameter `clusterId` should not be null");
		return get(MapReduceClusterInfo.class, uri("/cluster_infos/%s", clusterId)).execute();
	}

	public MapReduceClusterCreateResult createAndRunJob(MapReduceClusterCreate cluster, MapReduceJobExeCreate jobExe) {
		checkNotNull(cluster, "parameter `cluster` should not be null");
		// checkNotNull(jobExe, "parameter `jobExe` should not be null");
		// setup default values
		ArrayList<MapReduceJobExeCreate> jobs = jobExe == null ? new ArrayList<MapReduceJobExeCreate>() : Lists.newArrayList(jobExe);
		MapReduceClusterCreate create = cluster.toBuilder().billingType(BillingType.Metered).masterNodeNum(2).jobs(jobs)
				.build();
		return post(MapReduceClusterCreateResult.class, "/run-job-flow").entity(create).execute();
	}

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
	public Cluster create(Cluster cluster) {
		checkNotNull(cluster);
		MapReduceClusterUnwrapped unwrapped = new MapReduceClusterUnwrapped(cluster);
		return post(MapReduceCluster.class, uri("/clusters")).entity(unwrapped) // setup request
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
		return put(MapReduceCluster.class, uri("/clusters/%s", clusterId))
				.entity(new ResizeNodeGroupAction(groupName, count))
				.execute(ExecutionOptions.<MapReduceCluster> create(PropagateOnStatus.on(404))); // Use respongse
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
		return put(MapReduceCluster.class, uri("/clusters/%s", clusterId)).entity(new AddNodeGroupAction(nodeGroup))
				.execute(ExecutionOptions.<MapReduceCluster> create(PropagateOnStatus.on(404)));
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
