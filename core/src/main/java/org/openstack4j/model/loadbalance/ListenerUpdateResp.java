/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package org.openstack4j.model.loadbalance;

import java.util.Date;

import org.openstack4j.model.ModelEntity;

public interface ListenerUpdateResp extends ModelEntity {
	
	/**
	 * @return update time
	 */	
	Date getUpdateTime();
	
	/**
	 * @return backend port
	 */
	Integer getBackendPort();
	
	/**
	 * @return listener id
	 */
	String getId();
	
	/**
	 * @return backend protocol
	 */
	String getBackendProtocol();
	
	/**
	 * @return sticky session type
	 */
	String getStickySessionType();
	
	/**
	 * @return description
	 */
	String getDescription();
	
	/**
	 * @return load balancer id
	 */
	String getLoadBalancerId();
	
	/**
	 * @return create time
	 */
	Date getCreateTime();
	
	/**
	 * @return listener status
	 */
	String getStatus();
	
	/**
	 * @return listener protocol
	 */
	String getProtocol();
	
	/**
	 * @return listener port
	 */
	Integer getPort();
	
	/**
	 * @return cookie timeout
	 */
	Integer getCookieTimeout();
	
	/**
	 * @return whether administration state up
	 */
	Boolean getAdminStateUp();
	
	/**
	 * @return health check id
	 */
	String getHealthCheckId();
	
	/**
	 * @return whether session sticky
	 */
	Boolean getSessionSticky();
	
	/**
	 * @return load balancer algorithm
	 */
	String getLbAlgorithm();
	
	/**
	 * @return listener name
	 */
	String getName();
	
	/**
	 * @return whether tcp draining
	 */
	Boolean getTcpDraining();
	
	/**
	 * @return tcp draining timeout
	 */
	Integer getTcpDrainingTimeout();
	
	/**
	 * @return certificate id
	 */
	String getCertificateId();
}
