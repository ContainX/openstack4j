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
package org.openstack4j.openstack.sahara.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.sahara.constants.BillingType;
import org.openstack4j.openstack.sahara.constants.ClusterState;
import org.openstack4j.openstack.sahara.constants.VolumeType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("cluster")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaharaCluster2 implements ModelEntity {

	private static final long serialVersionUID = 1L;


	@JsonProperty("clusterId")
	String id;

	@JsonProperty("clusterName")
	String name;

	@JsonProperty("clusterVersion")
	String version;

	Integer masterNodeNum;
	Integer coreNodeNum;
	String masterNodeSize;
	String coreNodeSize;

	@JsonProperty("clusterState")
	ClusterState state;

	@JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
	Date createAt;
	@JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
	Date updateAt;

	BillingType billingType;
	String dataCenter;

	@JsonProperty("vpc")
	String vpcName;

	String subnetName;
	String securityGroupsId;
	String slaveSecurityGroupsId;

	String duration;
	String fee;

	Integer volumeSize;
	VolumeType volumeType;

	String azId;
	String azName;

	@JsonProperty("componentList")
	List<SaharaComponent> components;
	String externalIp;
	String externalAlternateIp;
	String internalIp;
	String deploymentId;
	String remark;
	String orderId;

	String masterNodeProductId;
	String masterNodeSpecId;
	String coreNodeProductId;
	String coreNodeSpecId;

	String instanceId;
	String vnc;
	String tenantId;

	String hadoopVersion;
	Integer safeMode;
	@JsonProperty("nodePublicCertName")
	String keypair;
	String masterNodeIp;
	String privateIpFirst;
	String errorInfo;
	String chargingStartTime;
	
	static class SaharaComponent {
		
		@JsonProperty("componentId")
		String id;
		
		@JsonProperty("componentName")
		String name;
		
		@JsonProperty("componentVersion")
		String version;
		
		@JsonProperty("componentDesc")
		String desc;
	}

}
