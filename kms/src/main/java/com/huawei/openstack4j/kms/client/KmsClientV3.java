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

import com.huawei.openstack4j.api.types.ServiceType;
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

public class KmsClientV3 extends OSClientSessionV3 {  

	public KmsClientV3() {
		super();
	}
	
	public KmsClientV3 withToken(String tokenId){
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
	
	static KmsClientV3 createSession() {
		return new KmsClientV3(null);
	}

	 static KmsClientV3 createSession(Config config) {
		return new KmsClientV3(config);
	}

	private KmsClientV3( Config config) {
		initConfig();
		this.config = config;
		sessions.set(this);
	}

	private void initConfig(){
		super.setServiceType(ServiceType.KEY_MANAGEMENT); 
		super.setVersion("v1.0");
	}
}
