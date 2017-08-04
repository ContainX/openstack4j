/*******************************************************************************
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
package org.openstack4j.openstack.trove.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.trove.domain.DatabaseInstance;
import org.openstack4j.openstack.trove.domain.DatabaseInstance.DatabaseInstances;
import org.openstack4j.openstack.trove.domain.DatabaseInstanceCreate;


/**
 * The implementation of manipulation of {@link DatabaseInstance}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:13:41
 */
public class TroveDatabaseInstanceService extends BaseTroveServices {

	public List<DatabaseInstance> list() {
		return get(DatabaseInstances.class, uri("/instances")).execute().getList();

	}

	public DatabaseInstance get(String instanceId) {
		checkNotNull(instanceId);
		DatabaseInstance instance = get(DatabaseInstance.class, uri("/instances/%s", instanceId)).execute();
		return instance;
	}

	public DatabaseInstance create(DatabaseInstanceCreate instanceCreate) {
		return post(DatabaseInstance.class, uri("/instances")).entity(instanceCreate).execute();
	}

	public ActionResponse delete(String id) {
		checkNotNull(id);
		return deleteWithResponse(uri("/instances/%s", id)).execute();
	}
}
