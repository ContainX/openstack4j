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
package org.openstack4j.openstack.key.management.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.common.Quota;
import org.openstack4j.openstack.common.Quota.Quotas;
import org.openstack4j.openstack.key.management.domain.Key;
import org.openstack4j.openstack.key.management.domain.Key.Keys;
import org.openstack4j.openstack.key.management.domain.KeyCreate;
import org.openstack4j.openstack.key.management.domain.KeyWrap;
import org.openstack4j.openstack.key.management.options.KeyListOptions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 *
 * @author QianBiao.NG
 * @date   2017-07-13 09:31:18
 */
public class KeyService extends BaseKeyManagementServices implements RestService {

	/**
	 * List keys with filter options
	 * 
	 * @param options filter option
	 * @return
	 */
	public Keys list(KeyListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return post(Keys.class, "/kms/list-keys").entity(params).execute();
	}

	/**
	 * Get key details
	 * 
	 * @param keyId identifier of the key
	 * @return
	 */
	public Key get(String keyId, String sequence) {
		checkNotNull(keyId);
		KeyActionRequest request = new KeyActionRequest(keyId, sequence);
		KeyWrap execute = post(KeyWrap.class, "/kms/describe-key").entity(request).execute();
		return execute.getKey();
	}

	/**
	 * Create a new Key
	 * 
	 * @param keyCreate A modal identification the key to be created
	 * @return
	 */
	public Key create(KeyCreate keyCreate) {
		checkNotNull(keyCreate);
		KeyWrap execute = post(KeyWrap.class, uri("/kms/create-key")).entity(keyCreate).execute();
		return execute.getKey();
	}

	/**
	 * Enable a key
	 * 
	 * @param keyId identifier of the key
	 * @param sequence a 36 bit serial number sequence used to trace the request
	 * @return
	 */
	public Key enable(String keyId, String sequence) {
		checkNotNull(keyId);
		KeyActionRequest request = new KeyActionRequest(keyId, sequence);
		KeyWrap execute = post(KeyWrap.class, "/kms/enable-key").entity(request).execute();
		return execute.getKey();
	}

	/**
	 * Disable a key
	 * 
	 * @param keyId identifier of the key
	 * @param sequence a 36 bit serial number sequence used to trace the request
	 * @return
	 */
	public Key disable(String keyId, String sequence) {
		checkNotNull(keyId);
		KeyActionRequest request = new KeyActionRequest(keyId, sequence);
		KeyWrap execute = post(KeyWrap.class, "/kms/disable-key").entity(request).execute();
		return execute.getKey();
	}

	/** 
	 * delete a key after pending days by scheduled task
	 * 
	 * @param keyId identifier of the key
	 * @param pendingDays Number of days a key is scheduled to be deleted (The value ranges from 7 to1096.)
	 * @param sequence a 36 bit serial number sequence used to trace the request
	 * @return
	 */
	public Key scheduleDeletion(String keyId, Integer pendingDays, String sequence) {
		checkNotNull(keyId);
		checkNotNull(pendingDays);
		KeyActionRequest request = new KeyActionRequest(keyId, pendingDays, sequence);
		return post(Key.class, "/kms/schedule-key-deletion").entity(request).execute();
	}

	/**
	 * cancel the scheduled deletion of a key
	 * 
	 * @param keyId
	 * @param sequence
	 * @return
	 */
	public Key cancelDeletion(String keyId, String sequence) {
		checkNotNull(keyId);
		KeyActionRequest request = new KeyActionRequest(keyId, sequence);
		return post(Key.class, "/kms/cancel-key-deletion").entity(request).execute();
	}
	
	
	/**
	 * get the number of Master-Key created by user. (the default master key is not include)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getKeyCreatedAmount() {
		HashMap<String, Integer> execute = get(HashMap.class, "/kms/user-instances").execute();
		return execute.get("instance_num");
	}
	
	/**
	 * Query for the quota of CMK
	 * @return
	 */
	public List<Quota> quotas() {
		Quotas quotas = get(Quotas.class, "/kms/user-quotas").execute();
		return quotas.getList();
	}


	static class KeyActionRequest {

		@JsonProperty("key_id")
		String id;

		@JsonProperty("sequence")
		String sequence;

		@JsonProperty("pending_days")
		Integer pendingDays;

		public KeyActionRequest(String id, Integer pendingDays, String sequence) {
			super();
			this.id = id;
			this.pendingDays = pendingDays;
			this.sequence = sequence;
		}

		public KeyActionRequest(String id, String sequence) {
			super();
			this.id = id;
			this.sequence = sequence;
		}

		public String getId() {
			return id;
		}

		public String getSequence() {
			return sequence;
		}

	}

}
