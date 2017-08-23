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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

import com.google.common.base.Strings;

public interface Listener extends ModelEntity {

	public enum Status {
		ACTIVE, PENDING_CREATE, ERROR;

		@JsonCreator
		public Status forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (Status status : Status.values()) {
					if (status.name().equalsIgnoreCase(value)) {
						return status;
					}
				}
			}
			return null;
		}
	}

	public enum Protocol {
		HTTP, TCP, HTTPS, UDP;

		@JsonCreator
		public Protocol forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (Protocol p : Protocol.values()) {
					if (p.name().equalsIgnoreCase(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	public enum BackendProtocol {
		HTTP, TCP, UDP;

		@JsonCreator
		public BackendProtocol forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (BackendProtocol p : BackendProtocol.values()) {
					if (p.name().equalsIgnoreCase(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	public enum LbAlgorithm {
		ROUND_ROBIN("roundrobin"), LEAST_CONN("leastconn"), SOURCE("source");

		private String val;

		private LbAlgorithm(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public LbAlgorithm forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (LbAlgorithm la : LbAlgorithm.values()) {
					if (la.getVal().equalsIgnoreCase(value)) {
						return la;
					}
				}
			}
			return null;
		}
	}

	public enum SSLProtocols {
		TLS_1_2("TLSv1.2"), TLS_1_1("TLSv1.1"), TLS_1("TLSv1");

		private String val;

		private SSLProtocols(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public SSLProtocols forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (SSLProtocols p : SSLProtocols.values()) {
					if (p.getVal().equalsIgnoreCase(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	public enum SSLCiphers {
		DEFAULT("Default"), EXTENDED("Extended"), STRICT("Strict");

		private String val;

		private SSLCiphers(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public SSLCiphers forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (SSLCiphers cipher : SSLCiphers.values()) {
					if (cipher.getVal().equalsIgnoreCase(value)) {
						return cipher;
					}
				}
			}
			return null;
		}
	}
	
	public enum StickySessionType {
		INSERT("insert");
		
		private String  val;
		
		private StickySessionType(String val) {
			this.val = val;
		}
		
		@JsonValue
		public String getVal() {
			return this.val;
		}
		
		@JsonCreator
		public StickySessionType forValue(String value) {
			if(!Strings.isNullOrEmpty(value)) {
				for (StickySessionType type : StickySessionType.values()) {
					if(type.getVal().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}

	/**
	 * @return listener id
	 */
	String getId();

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
	 * @return certificate id
	 */
	String getCertificateId();

	/**
	 * @return status of listener
	 */
	Status getStatus();

	/**
	 * @return whether administration state up
	 */
	Boolean getAdminStateUp();

	/**
	 * @return health check id
	 */
	String getHealthCheckId();

	/**
	 * @return whether tcp draining
	 */
	Boolean getTcpDraining();

	/**
	 * @return tcp draining timeout
	 */
	Integer getTcpDrainingTimeout();

	/**
	 * @return create time
	 */
	Date getCreateTime();

	/**
	 * @return update time
	 */
	Date getUpdateTime();

}
