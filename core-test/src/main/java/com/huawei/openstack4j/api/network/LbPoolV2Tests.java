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
package com.huawei.openstack4j.api.network;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.LbMethod;
import com.huawei.openstack4j.model.network.ext.LbPoolV2;
import com.huawei.openstack4j.model.network.ext.LbPoolV2Update;
import com.huawei.openstack4j.model.network.ext.Protocol;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author ashleykasim
 *
 */
@Test(suiteName="Network/lbpoolv2", enabled=true)
public class LbPoolV2Tests extends AbstractTest {
    private static final String LBPOOLSV2_JSON = "/network/lbpoolsv2.json";
    private static final String LBPOOLV2_JSON = "/network/lbpoolv2.json";
    private static final String LBPOOLV2_UPDATE_JSON = "/network/lbpoolv2_update.json";

    public void testListPoolV2() throws IOException {
        respondWith(LBPOOLSV2_JSON);
        List<? extends LbPoolV2> list = osv3().networking().lbaasV2().lbPool().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "b7f6a49f-ebd8-43c5-b792-5748366eff21");
    }

    public void testListPoolV2Filter() throws IOException {
        respondWith(LBPOOLSV2_JSON);
        Map<String, String> map = new HashMap<String, String>();
        map.put("protocol", "HTTP");
        List<? extends LbPoolV2> list = osv3().networking().lbaasV2().lbPool().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetPoolV2() throws IOException {
        respondWith(LBPOOLV2_JSON);
        String id = "b7f6a49f-ebd8-43c5-b792-5748366eff21";
        LbPoolV2 pool = osv3().networking().lbaasV2().lbPool().get(id);
        assertNotNull(pool);
        assertEquals(pool.getId(), id);
    }

    public void testCreatePoolV2() throws IOException {
        respondWith(LBPOOLV2_JSON);
        String name = "testlbpool";
        Protocol protocol = Protocol.HTTP;
        LbPoolV2 create = Builders.lbpoolV2()
                .adminStateUp(true)
                .description("im a swimming pool")
                .lbMethod(LbMethod.LEAST_CONNECTIONS)
                .name(name)
                .tenantId("6f759d84e3ca496ab77f8c0ffaa0311e")
                .protocol(protocol)
                .build();
        LbPoolV2 result = osv3().networking().lbaasV2().lbPool().create(create);
        assertEquals(result.getName(), name);
        assertEquals(result.getLbMethod(), LbMethod.LEAST_CONNECTIONS);
        assertEquals(result.getProtocol(), protocol);
    }

    public void testUpdatePoolV2() throws IOException {
        respondWith(LBPOOLV2_UPDATE_JSON);
        String poolId = "b7f6a49f-ebd8-43c5-b792-5748366eff21";
        String name = "v2update";
        LbPoolV2Update update = Builders.lbPoolV2Update()
                .adminStateUp(false)
                .description("im a carpool")
                .lbMethod(LbMethod.ROUND_ROBIN)
                .name(name)
                .build();
        LbPoolV2 result = osv3().networking().lbaasV2().lbPool().update(poolId, update);
        assertEquals(result.getName(), name);
        assertEquals(result.getLbMethod(), LbMethod.ROUND_ROBIN);
        assertFalse(result.isAdminStateUp());
    }

    public void testDeletePoolV2() {
        respondWith(204);
        ActionResponse result = osv3().networking().lbaasV2().lbPool().delete("b7f6a49f-ebd8-43c5-b792-5748366eff21");
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
