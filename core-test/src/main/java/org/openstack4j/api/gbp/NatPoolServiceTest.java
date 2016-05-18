package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.NatPool;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
/**
 * Test cases for nat pool on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/nat_pools")
public class NatPoolServiceTest extends AbstractTest {

    private static final String NAT_POOLS="/network/gbp/nat_pools.json";
    private static final String NAT_POOL="/network/gbp/nat_pool.json";
    private static final String NAT_POOL_UPDATE="/network/gbp/nat_pool_update.json";
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListNatPool() throws Exception{
        respondWith(NAT_POOLS);
        List<? extends NatPool> natpoolList = osv2().gbp().natPool().list();
        assertEquals(2, natpoolList.size()); 
        Preconditions.checkNotNull(natpoolList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Nat Pool from List : "+natpoolList.get(0));
        assertEquals(natpoolList.get(0).getId(), "f2e4fce7-4c55-497b-ac4c-290dd202c71a");
    }
    @Test
    public void testGetNatPool() throws Exception{
        respondWith(NAT_POOL);
        String id = "e2d4fce7-4c55-497b-ac4c-290dd202c71a";
        NatPool natPool = osv2().gbp().natPool().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Nat Pool by ID : "+natPool);
        assertNotNull(natPool);
        assertEquals(id, natPool.getId());
    }
    @Test
    public void testCreateNatPool() throws Exception{
        respondWith(NAT_POOL);
        NatPool natPool= Builders.natPool().name("ptg_nat_pool").build();
        NatPool npool = osv2().gbp().natPool().create(natPool);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Nat Pool : "+npool);
        assertEquals("ptg_nat_pool", npool.getName());
    }
    @Test
    public void testUpdateNatPool() throws Exception{
        respondWith(NAT_POOL_UPDATE);
        String id = "e2d4fce7-4c55-497b-ac4c-290dd202c71a";
        NatPool natPoolUpdate= Builders.natPool().name("ptg_nat_pool-update").build();
        NatPool natPool =osv2().gbp().natPool().update(id, natPoolUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Nat Pool : "+natPool);
        assertEquals("ptg_nat_pool-update", natPool.getName());

    }
    @Test
    public void testDeleteNatPool() {
        respondWith(200);
        String id = "e2d4fce7-4c55-497b-ac4c-290dd202c71a";
        ActionResponse result = osv2().gbp().natPool().delete(id);
        assertTrue(result.isSuccess());
    }


}
