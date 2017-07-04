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

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import org.openstack4j.model.loadbalance.Listener.SSLCiphers;
import org.openstack4j.model.loadbalance.Listener.SSLProtocols;

public interface ListenerUpdate extends ModelEntity {
	
	/**
	 * @return listener name
	 */
	String getName();
	
	/**
	 * @return listener description
	 */
	String getDescription();
	
	/**
	 * @return listen port
	 */
	Integer getPort();
	
	/**
	 * @return backend listen port
	 */
	Integer getBackendPort();
	
	/**
	 * @return algorithm of load balancer
	 */
	LbAlgorithm getLbAlgorithm();

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
}
