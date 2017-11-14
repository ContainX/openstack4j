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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.ClusterTemplate;
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;
import com.huawei.openstack4j.model.map.reduce.builder.ClusterTemplateBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of ClusterTemplate
 *
 * @author Ekasit Kijsipongse
 */
@JsonRootName("cluster_template")
public class MapReduceClusterTemplate implements ClusterTemplate {

	private static final long serialVersionUID = 1L;

	@JsonProperty("hadoop_version")
	private String hadoopVersion;
	@JsonProperty("default_image_id")
	private String defaultImageId;
	private String name;
	@JsonProperty("updated_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private Date updatedAt;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("plugin_name")
	private String pluginName;
        @JsonProperty("anti_affinity")
        private List<String> antiAffinity;
	private String description;
	private String id;
        @JsonProperty("node_groups")
        private List<MapReduceNodeGroup> nodeGroups;
        @JsonProperty("neutron_management_network")
	private String managementNetworkId;
	@JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private Date createdAt;
	@JsonProperty("cluster_configs")
	private Map<String, MapReduceServiceConfig> clusterConfigs;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClusterTemplateBuilder toBuilder() {
		return new ConcreteClusterTemplateBuilder(this);
	}

	/**
	 * @return the cluster template Builder
	 */
	public static ClusterTemplateBuilder builder() {
		return new ConcreteClusterTemplateBuilder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHadoopVersion() {
		return hadoopVersion;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDefaultImageId() {
		return defaultImageId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPluginName() {
		return pluginName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAntiAffinity() {
		return antiAffinity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends NodeGroup> getNodeGroups() {
		return nodeGroups;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getManagementNetworkId() {
		return managementNetworkId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, ? extends ServiceConfig> getClusterConfigs() {
		return clusterConfigs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
	             .add("id", id)
                     .add("name", name)
                     .add("description", description)
                     .add("hadoop_version", hadoopVersion)
                     .add("tenant_id",tenantId)
                     .add("created_at",createdAt)
                     .add("updated_at",updatedAt)
                     .add("plugin_name",pluginName)
                     .add("default_image_id", defaultImageId)
                     .add("anti_affinity",antiAffinity)
                     .add("neutron_management_network_id",managementNetworkId)
                     .add("node_groups",nodeGroups)
                     .add("cluster_configs",clusterConfigs)
                     .toString();
	}

	public static class ClusterTemplates extends ListResult<MapReduceClusterTemplate> {

		private static final long serialVersionUID = 1L;

                @JsonProperty("cluster_templates")
		private List<MapReduceClusterTemplate> nodeGroupTemplates;

		@Override
		protected List<MapReduceClusterTemplate> value() {
			return nodeGroupTemplates;
		}
	}

	public static class ConcreteClusterTemplateBuilder implements ClusterTemplateBuilder {

		private MapReduceClusterTemplate m;

		ConcreteClusterTemplateBuilder() {
			this(new MapReduceClusterTemplate());
		}

		ConcreteClusterTemplateBuilder(MapReduceClusterTemplate m) {
			this.m = m;
		}

		@Override
		public ClusterTemplate build() {
			return m;
		}

		@Override
		public ClusterTemplateBuilder from(ClusterTemplate in) {
			m = (MapReduceClusterTemplate) in;
			return this;
                }

		@Override
		public ClusterTemplateBuilder pluginName(String pluginName) {
			m.pluginName = pluginName;
			return this;
		}

		@Override
		public ClusterTemplateBuilder hadoopVersion(String hadoopVersion) {
			m.hadoopVersion = hadoopVersion;
			return this;
		}


		@Override
		public ClusterTemplateBuilder addNodeGroup(NodeGroup nodeGroup) {
                        if (m.nodeGroups == null)
                           m.nodeGroups = Lists.newArrayList();
                        m.nodeGroups.add((MapReduceNodeGroup) nodeGroup);
                        return this;
		}

		@Override
		public ClusterTemplateBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public ClusterTemplateBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public ClusterTemplateBuilder managementNetworkId(String networkId) {
			m.managementNetworkId = networkId;
			return this;
		}

		@Override
                public ClusterTemplateBuilder addServiceConfig(String name, ServiceConfig config) {
                        if (name != null && !name.isEmpty()) {
                           if (m.clusterConfigs == null)
                              m.clusterConfigs = new HashMap<String,MapReduceServiceConfig>();
                           m.clusterConfigs.put(name,(MapReduceServiceConfig) config);
                        }
                        return this;
                }

	}
}
