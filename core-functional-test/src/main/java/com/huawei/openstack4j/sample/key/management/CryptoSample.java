/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.sample.key.management;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.key.management.domain.DEK;
import com.huawei.openstack4j.openstack.key.management.domain.DecryptDEK;
import com.huawei.openstack4j.openstack.key.management.domain.DecryptedDEK;
import com.huawei.openstack4j.openstack.key.management.domain.EncryptDEK;
import com.huawei.openstack4j.openstack.key.management.domain.EncryptedDEK;
import com.huawei.openstack4j.openstack.key.management.domain.KeyCreate;
import com.huawei.openstack4j.sample.AbstractSample;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 14:43:01
 */
public class CryptoSample extends AbstractSample {

	String name = randomName();
	String keyId = null;
	boolean deleteKey = false;

	DEK dek;
	EncryptedDEK encryptedDEK;

	@BeforeClass
	public void prepare() {
		// KeyCreate create = KeyCreate.builder().alias(name).description("desc").realm("eu-de").build();
		// key = osclient.keyManagement().keys().create(create);
		List<String> keys = osclient.keyManagement().keys().list(null).get();
		if (keys != null && keys.size() > 0) {
			keyId = keys.get(0);
		} else {
			KeyCreate create = KeyCreate.builder().alias(name).description("desc").realm("eu-de").build();
			keyId = osclient.keyManagement().keys().create(create).getId();
			deleteKey = true;
		}
	}

	@AfterClass
	public void cleanup() {
		if (deleteKey) {
			osclient.keyManagement().keys().scheduleDeletion(keyId, 7, null);
		}
	}

	@Test
	public void testGenerateRandom() {
		String randomString = osclient.keyManagement().crypto().generateRandomString(null);
		Assert.assertEquals(randomString.length(), 128);
	}

	@Test
	public void testCreateDEKWithPlaintext() {
		HashMap<String, Object> encryptionContext = Maps.newHashMap();
		encryptionContext.put("Key1", "value1");
		encryptionContext.put("Key2", "value2");
		dek = osclient.keyManagement().crypto().createDEK(keyId, encryptionContext, null);
		Assert.assertEquals(dek.getKeyId(), keyId);
		Assert.assertEquals(dek.getPlainText().length(), 128);
		Assert.assertEquals(dek.getCipherText().length(), 376);
	}

	@Test
	public void testCreateDEKWithoutPlaintext() {
		HashMap<String, Object> encryptionContext = Maps.newHashMap();
		encryptionContext.put("Key1", "value1");
		encryptionContext.put("Key2", "value2");
		DEK dek = osclient.keyManagement().crypto().createDEKWithoutPlaintext(keyId, encryptionContext, null);
		Assert.assertEquals(dek.getKeyId(), keyId);
		Assert.assertEquals(dek.getPlainText(), null);
		Assert.assertEquals(dek.getCipherText().length(), 376);
	}

	@Test(dependsOnMethods = { "testCreateDEKWithPlaintext" })
	public void testEncryptDEK() {
		HashMap<String, Object> context = Maps.newHashMap();
		context.put("Key1", "value1");
		context.put("Key2", "value2");

		EncryptDEK encrypt = EncryptDEK.builder().keyId(keyId).plainText(dek.getPlainText()).encryptionContext(context)
				.build();
		encryptedDEK = osclient.keyManagement().crypto().encryptDEK(encrypt);
		Assert.assertEquals(encryptedDEK.getKeyId(), keyId);
	}

	@Test(dependsOnMethods = { "testEncryptDEK" })
	public void testDecryptDEK() {
		HashMap<String, Object> context = Maps.newHashMap();
		context.put("Key1", "value1");
		context.put("Key2", "value2");
		DecryptDEK decrypt = DecryptDEK.builder().keyId(keyId).cipherText(encryptedDEK.getCipherText())
				.encryptionContext(context).build();
		
		DecryptedDEK decryptDEK = osclient.keyManagement().crypto().decryptDEK(decrypt);
		Assert.assertEquals(decryptDEK.getDataKey(), dek.getPlainText());
		Assert.assertEquals(decryptDEK.getDatakeyLength().intValue(), 64);
	}

}
