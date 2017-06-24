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
package org.openstack4j.openstack.loadbalance.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openstack4j.api.loadbalance.ELBServerService;
import org.openstack4j.model.loadbalance.Server;
import org.openstack4j.model.loadbalance.ServerCreate;
import org.openstack4j.model.loadbalance.ServerDelete;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.loadbalance.domain.ELBJob;
import org.openstack4j.openstack.loadbalance.domain.ELBServer;
import org.openstack4j.openstack.loadbalance.options.ELBServerListOptions;

import com.google.common.base.Strings;

public class ELBServerServiceImpl extends BaseELBServices implements ELBServerService {

	private static final String API_PATH = "/elbaas/listeners";

	@Override
	public ELBJob create(String listenerId, List<ServerCreate> servers) {
		checkArgument(!Strings.isNullOrEmpty(listenerId), "listenerId is required");
		checkArgument(servers != null && !servers.isEmpty(), "servers is required");
		for (ServerCreate server : servers) {
			checkArgument(server != null, "server can not be null");
			checkArgument(!Strings.isNullOrEmpty(server.getServerId()), "serverId is required");
			checkArgument(!Strings.isNullOrEmpty(server.getAddress()), "server address is required");
		}

		return post(ELBJob.class, uri("%s/%s/members", API_PATH, listenerId)).entity(servers).execute();
	}

	@Override
	public ELBJob delete(String listenerId, ServerDelete serverDelete) {
		checkArgument(!Strings.isNullOrEmpty(listenerId), "listenerId is required");
		checkArgument(serverDelete != null, "serverDelete is required");

		List<IdResourceEntity> removeMember = serverDelete.getRemoveMember();
		checkArgument(removeMember != null && !removeMember.isEmpty(), "removeMember is required");
		for (IdResourceEntity member : removeMember) {
			checkArgument(member != null, "member can not be null");
			checkArgument(!Strings.isNullOrEmpty(member.getId()), "server id is required");
		}

		return post(ELBJob.class, uri("%s/%s/members/action", API_PATH, listenerId)).entity(serverDelete).execute();
	}

	@Override
	public Server[] list(String listenerId) {
		checkArgument(!Strings.isNullOrEmpty(listenerId), "listenerId is required");
		return get(ELBServer[].class, uri("%s/%s/members", API_PATH, listenerId)).execute();
	}

	@Override
	public Server[] list(String listenerId, ELBServerListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(listenerId), "listenerId is required");
		checkArgument(options != null, "options is required");

		return get(ELBServer[].class, uri("%s/%s/members", API_PATH, listenerId)).params(options.getOptions()).execute();
	}

}
