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
package org.openstack4j.api.key.management;

import java.io.IOException;
import java.util.HashMap;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.key.management.domain.DEK;
import org.openstack4j.openstack.key.management.domain.DecryptDEK;
import org.openstack4j.openstack.key.management.domain.DecryptedDEK;
import org.openstack4j.openstack.key.management.domain.EncryptDEK;
import org.openstack4j.openstack.key.management.domain.EncryptedDEK;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "KMS/Crypto", enabled = true)
public class CryptoTest extends AbstractTest {

	String sequence = "919c82d4-8046-4722-9094-35c3c6524cff";

	@Test
	public void testCreateDEK() throws IOException, InterruptedException {
		respondWith("/kms/create_dek_response.json");

		HashMap<String, Object> encryptionContext = Maps.newHashMap();
		encryptionContext.put("Key1", "value1");
		encryptionContext.put("Key2", "value2");
		DEK dek = osv3().keyManagement().crypto().createDEK("key-id", encryptionContext, sequence);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/create-datakey");
		Assert.assertEquals(request.getMethod(), "POST");

		String actualBody = request.getBody().readUtf8();
		String expectBody = getResource("/kms/create_dek_request.json");
		Assert.assertEquals(actualBody, expectBody);

		Assert.assertEquals(dek.getKeyId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(dek.getPlainText(),
				"8151014275E426C72EE7D44267EF11590DCE0089E19863BA8CC832187B156A72A5A17F17B5EF0D525872C59ECEB72948AF85E18427F8BE0D46545C979306C08D");
		Assert.assertEquals(dek.getCipherText(),
				"020098009EEAFCE122CAA5927D2E020086F9548BA1675FDB022E4ECC01B96F2189CF4B85E78357E73E1CEB518DAF7A4960E7C7DE8885ED3FB2F1471ABF400119CC1B20BD3C4A9B80AF590EFD0AEDABFDBB0E2B689DA7B6C9E7D3C5645FCD9274802586BE63779471F9156F2CDF07CD8412FFBE9230643034363662302D653732372D346439632D623335642D6638346262343734613337660000000045B05321483BD9F9561865EE7DFE9BE267A42EB104E98C16589CE46940B18E52");
	}

	@Test
	public void testCreateDEKWithoutPlaintext() throws IOException, InterruptedException {
		respondWith("/kms/create_dek_without_plaintext_response.json");

		HashMap<String, Object> encryptionContext = Maps.newHashMap();
		encryptionContext.put("Key1", "value1");
		encryptionContext.put("Key2", "value2");
		DEK dek = osv3().keyManagement().crypto().createDEKWithoutPlaintext("key-id", encryptionContext, sequence);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/create-datakey-without-plaintext");
		Assert.assertEquals(request.getMethod(), "POST");

		String actualBody = request.getBody().readUtf8();
		String expectBody = getResource("/kms/create_dek_request.json");
		Assert.assertEquals(actualBody, expectBody);

		Assert.assertEquals(dek.getKeyId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(dek.getPlainText(), null);
		Assert.assertEquals(dek.getCipherText(),
				"020098009EEAFCE122CAA5927D2E020086F9548BA1675FDB022E4ECC01B96F2189CF4B85E78357E73E1CEB518DAF7A4960E7C7DE8885ED3FB2F1471ABF400119CC1B20BD3C4A9B80AF590EFD0AEDABFDBB0E2B689DA7B6C9E7D3C5645FCD9274802586BE63779471F9156F2CDF07CD8412FFBE9230643034363662302D653732372D346439632D623335642D6638346262343734613337660000000045B05321483BD9F9561865EE7DFE9BE267A42EB104E98C16589CE46940B18E52");
	}

	@Test
	public void testEncryptDEK() throws IOException, InterruptedException {
		respondWith("/kms/encrypt_dek_response.json");

		HashMap<String, Object> context = Maps.newHashMap();
		context.put("Key1", "value1");
		context.put("Key2", "value2");
		EncryptDEK encrypt = EncryptDEK.builder().keyId("key-id")
				.plainText(
						"00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
				.encryptionContext(context).sequence(sequence).build();
		EncryptedDEK encrypted = osv3().keyManagement().crypto().encryptDEK(encrypt);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/encrypt-datakey");
		Assert.assertEquals(request.getMethod(), "POST");

		String actualBody = request.getBody().readUtf8();
		String expectBody = getResource("/kms/encrypt_dek_request.json");
		Assert.assertEquals(actualBody, expectBody);

		Assert.assertEquals(encrypted.getKeyId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(encrypted.getDataKeyLength().intValue(), 64);
		Assert.assertEquals(encrypted.getCipherText(),
				"020098005273E14E6E8E95F5463BECDC27E80AF820B9FC086CB47861899149F67CF07DAFF2810B7D27BDF19AB7632488E0926A48DB2FC85BEA905119411B46244C5E6B8036C60A0B0B4842FFE6994518E89C19B1C1D688D9043BCD6053EA7BA0652642CE59F2543C80669139F4F71ABB9BD9A24330643034363662302D653732372D346439632D623335642D66383462623437346133376600000000D34457984F9730D57F228C210FD22CA6017913964B21D4ECE45D81092BB9112E");
	}

	@Test
	public void testDecryptDEK() throws IOException, InterruptedException {
		respondWith("/kms/decrypt_dek_response.json");

		HashMap<String, Object> context = Maps.newHashMap();
		context.put("Key1", "value1");
		context.put("Key2", "value2");
		DecryptDEK decrypt = DecryptDEK.builder().keyId("key-id").cipherText("cipher-text").encryptionContext(context)
				.sequence(sequence).build();
		DecryptedDEK decrypted = osv3().keyManagement().crypto().decryptDEK(decrypt);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/decrypt-datakey");
		Assert.assertEquals(request.getMethod(), "POST");

		String actualBody = request.getBody().readUtf8();
		String expectBody = getResource("/kms/decrypt_dek_request.json");
		Assert.assertEquals(actualBody, expectBody);

		Assert.assertEquals(decrypted.getDataKey(),
				"00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		Assert.assertEquals(decrypted.getDatakeyLength().intValue(), 64);
		Assert.assertEquals(decrypted.getDatakeyDigest(),
				"F5A5FD42D16A20302798EF6ED309979B43003D2320D9F0E8EA9831A92759FB4B");
	}

	@Override
	protected Service service() {
		return Service.KEY_MANAGEMENT;
	}

}
