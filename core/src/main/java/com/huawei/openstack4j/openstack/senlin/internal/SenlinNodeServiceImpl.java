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
package com.huawei.openstack4j.openstack.senlin.internal;

import java.util.List;

import com.huawei.openstack4j.api.senlin.SenlinNodeService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.ActionID;
import com.huawei.openstack4j.model.senlin.Node;
import com.huawei.openstack4j.model.senlin.NodeActionCreate;
import com.huawei.openstack4j.model.senlin.NodeCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinActionID;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinNode;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available node services
 * 
 * @author lion
 */
public class SenlinNodeServiceImpl extends BaseSenlinServices implements SenlinNodeService {

	@Override
	public List<? extends Node> list() {
		return get(SenlinNode.Node.class, uri("/nodes")).execute().getList();
	}

	@Override
	public Node create(NodeCreate newNode) {
		checkNotNull(newNode);
		return post(SenlinNode.class, uri("/nodes")).entity(newNode).execute();
	}

	@Override
	public Node get(String nodeID) {
		return get(nodeID, false);	//false for backward compatibility
	}

	@Override
	public Node get(final String nodeID, final boolean showDetails) {
		checkNotNull(nodeID);
		//see at https://developer.openstack.org/api-ref/clustering/?expanded=show-node-details-detail
		return get(SenlinNode.class, uri("/nodes/%s", nodeID)).param("show_details", showDetails).execute();
	}

	@Override
	public ActionResponse delete(String nodeID) {
		checkNotNull(nodeID);
		return deleteWithResponse(uri("/nodes/%s", nodeID)).execute();
	}

	@Override
	public Node update(String nodeID, NodeCreate newNode) {
		checkNotNull(nodeID);
		checkNotNull(newNode);
		return patch(SenlinNode.class, uri("/nodes/%s", nodeID)).entity(newNode).execute();
	}

	@Override
	public ActionID action(String nodeID, NodeActionCreate newNodeAction) {
		checkNotNull(nodeID);
		checkNotNull(newNodeAction);
		return post(SenlinActionID.class, uri("/nodes/%s/actions", nodeID)).entity(newNodeAction).execute();
	}

}
