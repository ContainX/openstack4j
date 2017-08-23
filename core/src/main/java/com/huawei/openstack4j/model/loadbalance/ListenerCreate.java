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
package com.huawei.openstack4j.model.loadbalance;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLCiphers;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLProtocols;
import com.huawei.openstack4j.model.loadbalance.Listener.StickySessionType;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Status;

public interface ListenerCreate extends ModelEntity {
	
	/**
	 * @return listener name
	 */
	String getName();
	
	/**
	 * @return listener description
	 */
	String getDescription();
	
	/**
	 * @return load balancer id
	 */
	String getLoadBalancerId();
	
	/**
	 * @return listener protocol
	 */
	Protocol getProtocol();
	
	/**
	 * @return listen port
	 */
	Integer getPort();
	
	/**
	 * @return backend protocol of listener
	 */
	BackendProtocol getBackendProtocol();
	
	/**
	 * @return backend listen port
	 */
	Integer getBackendPort();
	
	/**
	 * @return algorithm of load balancer
	 */
	LbAlgorithm getLbAlgorithm();

	/**
	 * @return whether session sticky
	 */
	Boolean getSessionSticky();
	
	/**
	 * @return session sticky type
	 */
	StickySessionType getStickySessionType();
	
	/**
	 * @return cookie timeout
	 */
	Integer getCookieTimeout();

	/**
	 * @return tcp connection timeout
	 */
	Integer getTcpTimeout();
	
	/**
	 * @return whether tcp connection draining
	 */
	Boolean getTcpDraining();
	
	/**
	 * @return tcp connection draining timeout
	 */
	Integer getTcpDrainingTimeout();
	
	/**
	 * @return certificate id
	 */
	String getCertificateId();
	
	/**
	 * @return udp connection timeout
	 */
	Integer getUdpTimeout();
	
	/**
	 * @return ssl protocols of listener
	 */
	SSLProtocols getSslProtocols();
	
	/**
	 * @return ssl ciphers
	 */
	SSLCiphers getSslCiphers();
	
	/**
	 * @return listener id
	 */
	String getId();
	
	/**
	 * @return listener status
	 */
	Status getStatus();
	
	/**
	 * @return whether administration state up
	 */
	Boolean getAdminStateUp();
	
	/**
	 * @return create time
	 */
	Date getCreateTime();
	
	/**
	 * @return update time
	 */
	Date getUpdateTime();
}
