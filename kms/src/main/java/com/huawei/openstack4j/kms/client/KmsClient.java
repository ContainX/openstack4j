/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
package com.huawei.openstack4j.kms.client;

import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.kms.openstack.internal.CryptoService;
import com.huawei.openstack4j.kms.openstack.internal.KeyService;
import com.huawei.openstack4j.openstack.internal.OSClientSessionV3;

/**
 * A client which has been identified. Any calls spawned from this session will
 * automatically utilize the original authentication that was successfully
 * validated and authorized
 *
 * @author Super Stone
 */

public class KmsClient extends OSClientSessionV3 {  

	public KmsClient() {
		super();
	}
	
	public KmsClient withToken(String tokenId){
		super.withToken(tokenId);
		return this;
	}

	public KeyService keys() {
		KeyService impl = new KeyService();
		return impl;
	}
	
	public CryptoService crypto() {
		CryptoService impl = new CryptoService();
		return impl;
	}
	
	static KmsClient createSession() {
		return new KmsClient(null);
	}

	 static KmsClient createSession(Config config) {
		return new KmsClient(config);
	}

	private KmsClient( Config config) {
		this.config = config;
		sessions.set(this);
	}
}
