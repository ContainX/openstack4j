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
package com.huawei.openstack4j.test.core.transport;

import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.core.transport.Config;

public class ConfigTest {

	@Test
	public void testUnequalConfigSsl() {
		Config firstConfig = Config.newConfig();
		try {
			SSLContext firstSslContext =  SSLContext.getInstance("SSL");
			
			firstSslContext.init(null, new TrustManager[] { null }, new SecureRandom());
			firstConfig.withSSLContext(firstSslContext);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Config secondConfig = Config.newConfig();
		try {
			SSLContext secondSslContext =  SSLContext.getInstance("SSL");
			
			secondSslContext.init(null, new TrustManager[] { null }, new SecureRandom());
			secondConfig.withSSLContext(secondSslContext);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertNotEquals(firstConfig, secondConfig);
	}
	
	@Test
	public void testUnequalConfigHostnameVerifier() {
		Config firstConfig = Config.newConfig();
		firstConfig.withHostnameVerifier(new HostnameVerifier() {
			
			@Override
			public boolean verify(String hostname, SSLSession session) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		Config secondConfig = Config.newConfig();
		
		Assert.assertNotEquals(firstConfig, secondConfig);
	}
}
