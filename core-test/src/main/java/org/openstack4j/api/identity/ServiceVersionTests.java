package org.openstack4j.api.identity;

import static org.testng.Assert.assertTrue;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.resolvers.LatestServiceVersionResolver;
import org.openstack4j.openstack.internal.OSClientSession;
import org.testng.annotations.Test;

@Test(suiteName="Identity/ServiceVersions")
public class ServiceVersionTests extends AbstractTest {

    @Test
    public void thatComputeV2IsReturnedByDefault() {
        assertTrue(session().useConfig(Config.DEFAULT).getEndpoint(ServiceType.COMPUTE).contains("/v2/"), "Endpoint was not version 2");
    }
    
    @Test
    public void thatComputeV21IsReturned() {
        Config config = Config.newConfig().withResolver(LatestServiceVersionResolver.INSTANCE);
        assertTrue(session().useConfig(config).getEndpoint(ServiceType.COMPUTE).contains("/v2.1/"), "Endpoint was not version 2.1");
    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
    
    private OSClientSession session() {
        return (OSClientSession)os();
    }
}
