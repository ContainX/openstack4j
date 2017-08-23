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
package com.huawei.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.ActionID;
import com.huawei.openstack4j.model.senlin.Node;
import com.huawei.openstack4j.model.senlin.NodeActionCreate;
import com.huawei.openstack4j.model.senlin.NodeCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinNodeActionCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinNodeCreate;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for node on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/node")
public class NodeServiceTest extends AbstractTest {

    private static final String NODES="/senlin/v1/nodes.json";
    private static final String NODE="/senlin/v1/node.json";
    private static final String RASPACTION="/senlin/v1/resp_action.json";
    private static final String ID="d5779bb0-f0a0-49c9-88cc-6f078adb5a0b";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListNode() throws Exception{
        respondWith(NODES);
        List<? extends Node> nodeList = osv3().senlin().node().list();
        assertEquals(3, nodeList.size());
        Preconditions.checkNotNull(nodeList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Node from List : "+nodeList.get(0));
        assertEquals(nodeList.get(0).getId(), "d3451489-708b-4bd0-a147-a0c02dd9cb00");
    }
    @Test
    public void testGetNode() throws Exception{
        respondWith(NODE);
        Node node = osv3().senlin().node().get(ID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Node by ID : " + node);
        assertNotNull(node);
        assertEquals(ID, node.getId());
    }
    @Test
    public void testCreateNode() throws Exception{
        respondWith(NODE);
        String nodeName = "node1";
        NodeCreate newNode = new SenlinNodeCreate();
        newNode.toBuilder()
                .name(nodeName);
        Node node = osv3().senlin().node().create(newNode);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Node : " + node);
        assertEquals(nodeName, node.getName());
    }
    @Test
    public void testUpdateNode() throws Exception{
        respondWith(NODE);
        String nodeName = "node1";
        NodeCreate newNode = new SenlinNodeCreate();
        newNode.toBuilder()
                .name(nodeName);
        Node node = osv3().senlin().node().update(ID, newNode);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Node : " + node);
        assertEquals(nodeName, node.getName());
    }
    @Test
    public void testDeleteNode() {
        respondWith(200);
        ActionResponse result = osv3().senlin().node().delete(ID);
        assertTrue(result.isSuccess());
    }
    @Test
    public void testNodeAction() throws Exception{
        respondWith(RASPACTION);
        NodeActionCreate newNodeAction = new SenlinNodeActionCreate();
        newNodeAction.toBuilder().check(new HashMap<String, String>());
        ActionID respAction = osv3().senlin().node().action("573aa1ba-bf45-49fd-907d-6b5d6e6adfd3", newNodeAction);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Trigger node action : " + respAction);
        assertEquals("40a436b1-28d1-4de6-b2c3-0a34f478e2c9", respAction.getActionID());
    }

}
