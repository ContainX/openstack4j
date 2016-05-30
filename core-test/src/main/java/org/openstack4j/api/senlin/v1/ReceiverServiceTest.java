package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.senlin.Receiver;
import org.openstack4j.model.senlin.ReceiverCreate;
import org.openstack4j.openstack.senlin.domain.SenlinReceiverCreate;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for receiver on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/receiver")
public class ReceiverServiceTest extends AbstractTest {

    private static final String RECEIVERS="/senlin/v1/receivers.json";
    private static final String RECEIVER="/senlin/v1/receiver.json";
    private static final String ID="573aa1ba-bf45-49fd-907d-6b5d6e6adfd3";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListReceiver() throws Exception{
        respondWith(RECEIVERS);
        List<? extends Receiver> receiverList = osv3().senlin().receiver().list();
        assertEquals(5, receiverList.size());
        Preconditions.checkNotNull(receiverList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Receiver from List : " + receiverList.get(0));
        assertEquals(receiverList.get(0).getId(), "05f72ca7-d0cd-4e9c-9f84-caec408e7580");
    }
    @Test
    public void testGetReceiver() throws Exception{
        respondWith(RECEIVER);
        Receiver receiver = osv3().senlin().receiver().get(ID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Receiver by ID : "+ receiver);
        assertNotNull(receiver);
        assertEquals(ID, receiver.getId());
    }
    @Test
    public void testCreateReceiver() throws Exception{
        respondWith(RECEIVER);
        String receiverName = "cluster_inflate";
        ReceiverCreate newReceiver = new SenlinReceiverCreate();
        newReceiver.toBuilder()
                .name(receiverName);
        Receiver receiver = osv3().senlin().receiver().create(newReceiver);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Receiver : " + receiver);
        assertEquals(receiverName, receiver.getName());
    }
    @Test
    public void testDeleteReceiver() {
        respondWith(200);
        ActionResponse result = osv3().senlin().receiver().delete(ID);
        assertTrue(result.isSuccess());
    }

}
