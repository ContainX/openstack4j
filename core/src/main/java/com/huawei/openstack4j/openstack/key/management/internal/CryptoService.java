/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package com.huawei.openstack4j.openstack.key.management.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.openstack.key.management.domain.DEK;
import com.huawei.openstack4j.openstack.key.management.domain.DecryptDEK;
import com.huawei.openstack4j.openstack.key.management.domain.DecryptedDEK;
import com.huawei.openstack4j.openstack.key.management.domain.EncryptDEK;
import com.huawei.openstack4j.openstack.key.management.domain.EncryptedDEK;

/**
 * Crypto Service
 *
 * @author QianBiao.NG
 * @date   2017-07-13 09:31:10
 */
public class CryptoService extends BaseKeyManagementServices implements RestService {

	/**
	 * Create a 512 bit random data
	 * 
	 * @param sequence a 36-byte serial number used to trace the request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String generateRandomString(String sequence) {
		Map<String, String> entity = Maps.newHashMap();
		entity.put("random_data_length", "512");
		entity.put("sequence", sequence);
		HashMap<String, String> execute = post(HashMap.class, "/kms/gen-random").entity(entity).execute();
		return execute.get("random_data");
	}

	/**
	 * 
	 * Create a data encrypt key
	 * 
	 * @param keyId					the key used to create the DEK
	 * @param encryptionContext		the encryption context (key-value pairs)
	 * @param sequence				a 36-byte serial number used to trace the request
	 * @return	a DEK with plain and cipher text
	 */
	public DEK createDEK(String keyId, HashMap<String, Object> encryptionContext, String sequence) {
		return createDEK(keyId, encryptionContext, true, sequence);
	}

	/**
	 * 
	 * Create a data encrypt key but with no plain text
	 * 
	 * @param keyId					the key used to create the DEK
	 * @param encryptionContext		the encryption context (key-value pairs)
	 * @param sequence				a 36-byte serial number used to trace the request
	 * @return	a DEK with cipher text
	 */
	public DEK createDEKWithoutPlaintext(String keyId, HashMap<String, Object> encryptionContext, String sequence) {
		return createDEK(keyId, encryptionContext, false, sequence);
	}

	/**
	 * 
	 * Create a data encrypt key
	 * 
	 * @param keyId					the key used to create the DEK
	 * @param encryptionContext		the encryption context (key-value pairs)
	 * @param withPlainText			whether to return plain text in result
	 * @param sequence				a 36-byte serial number used to trace the request
	 * @return	a DEK with plain and cipher text
	 */
	private DEK createDEK(String keyId, HashMap<String, Object> encryptionContext, boolean withPlainText,
			String sequence) {

		Map<String, Object> entity = Maps.newHashMap();
		entity.put("key_id", keyId);
		entity.put("datakey_length", "512");
		if (encryptionContext != null && !encryptionContext.isEmpty()) {
			entity.put("encryption_context", encryptionContext);
		}

		if (!Strings.isNullOrEmpty(sequence)) {
			entity.put("sequence", sequence);
		}

		String url = withPlainText ? "/kms/create-datakey" : "/kms/create-datakey-without-plaintext";
		return post(DEK.class, url).entity(entity).execute();
	}

	/**
	 * encrypt a DEK using a specified CMK
	 * 
	 * @param encrypt
	 * @return
	 */
	public EncryptedDEK encryptDEK(EncryptDEK encrypt) {
		checkNotNull(encrypt, "parameter `encrypt` should not be null");
		checkNotNull(encrypt.getKeyId(), "parameter `encrypt.keyId` should not be null");
		checkNotNull(encrypt.getPlainText(), "parameter `encrypt.plainText` should not be null");
		if (encrypt.getPlainTextLength() == null) {
			encrypt = encrypt.toBuilder().plainTextLength(64).build();
		}
		
		// generate digest text
		byte[] decode = BaseEncoding.base16().lowerCase().decode(encrypt.getPlainText());
		byte[] digest = Hashing.sha256().hashBytes(decode).asBytes();
		String digestText = BaseEncoding.base16().lowerCase().encode(digest);
		encrypt = encrypt.toBuilder().plainText(encrypt.getPlainText() + digestText).build();
		
		return post(EncryptedDEK.class, "/kms/encrypt-datakey").entity(encrypt).execute();
	}
	
	/**
	 * decrypt a DEK using a specified CMK
	 * 
	 * @param decrypt
	 * @return
	 */
	public DecryptedDEK decryptDEK(DecryptDEK decrypt) {
		checkNotNull(decrypt, "parameter `decrypt` should not be null");
		checkNotNull(decrypt.getKeyId(), "parameter `decrypt.keyId` should not be null");
		checkNotNull(decrypt.getCipherText(), "parameter `decrypt.cipherText` should not be null");
		if (decrypt.getCipherTextLength() == null) {
			decrypt = decrypt.toBuilder().cipherTextLength(64).build();
		}
		return post(DecryptedDEK.class, "/kms/decrypt-datakey").entity(decrypt).execute();
	}
	
}
