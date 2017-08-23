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
package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.QuotaSet;

import com.google.common.base.MoreObjects;

/**
 * An OpenStack Quota-Set
 *
 * @author Jeremy Unruh
 */
@JsonRootName("quota_set")
public class NovaQuotaSet implements QuotaSet {

	private static final long serialVersionUID = 1L;

	private String id;

	@JsonProperty("metadata_items")
	private int metadataItems;

	@JsonProperty("injected_file_content_bytes")
	private int injectedFileContentBytes;

	@JsonProperty("injected_files")
	private int injectedFiles;

	private int gigabytes;
	private int ram;

	@JsonProperty("floating_ips")
	private int floatingIps;

	private int instances;

	private int volumes;

	private int cores;

	@JsonProperty("security_groups")
	private int securityGroups;

	@JsonProperty("security_group_rules")
	private int securityGroupRules;

	@JsonProperty("injected_file_path_bytes")
	private int injectedFilePathBytes;

	@JsonProperty("key_pairs")
	private int keyPairs;

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
	public int getMetadataItems() {
		return metadataItems;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInjectedFileContentBytes() {
		return injectedFileContentBytes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInjectedFiles() {
		return injectedFiles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getGigabytes() {
		return gigabytes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRam() {
		return ram;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getFloatingIps() {
		return floatingIps;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInstances() {
		return instances;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getVolumes() {
		return volumes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCores() {
		return cores;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSecurityGroups() {
		return securityGroups;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSecurityGroupRules() {
		return securityGroupRules;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInjectedFilePathBytes() {
		return injectedFilePathBytes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getKeyPairs() {
		return keyPairs;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("metadataItems", metadataItems).add("injectedFileContentBytes", injectedFileContentBytes)
				     .add("injectedFileContentBytes", injectedFileContentBytes).add("injectedFiles", injectedFiles).add("gigabytes", gigabytes)
				     .add("ram", "ram").add("securityGroups", securityGroups).add("securityGroupRules", securityGroupRules)
				     .add("cores", cores).add("floatingIps", floatingIps).add("instances", instances).add("volumes", volumes)
				     .add("injectedFilePathBytes", injectedFilePathBytes).add("keyPairs", keyPairs)
				     .toString();
	}

	@JsonRootName("quota_class_set")
	public static class NovaQuotaSetClass extends NovaQuotaSet {
        private static final long serialVersionUID = 1L;
	}
}
