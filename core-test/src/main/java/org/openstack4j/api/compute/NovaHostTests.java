package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.HostResource;
import org.testng.annotations.Test;

/**
 * Test case for Nova Host Describe
 * 
 * @author Qin An
 */
@Test(suiteName = "NovaHost")
public class NovaHostTests extends AbstractTest {

	private static final String OS_HOST_DESCRIBE = "/compute/host_describe.json";
	private static final String HOST_NAME = "testHost";

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

	public void hostDescribe() throws Exception {
        respondWith(OS_HOST_DESCRIBE);
		List<? extends HostResource> resources = osv3().compute().host().hostDescribe(HOST_NAME);
		assertEquals(resources.size(), 3, "The size of resources is not correct");
		HostResource resource1 = resources.get(0);
		assertEquals(resource1.getDiskInGb(), 1028);
		assertEquals(resource1.getCpu(), 1);
		
		HostResource resource2 = resources.get(1);
		assertEquals(resource2.getHost(), "c1a7de0ac9d94e4baceae031d05caae3");
		assertEquals(resource2.getMemoryInMb(), 512);
        
		HostResource resource3 = resources.get(2);
		assertEquals(resource3.getProject(), "(used_max)");
    }

}
