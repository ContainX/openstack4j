package org.openstack4j.test.core.transport;

import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.openstack4j.core.transport.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

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
