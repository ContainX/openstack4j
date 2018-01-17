package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.testng.annotations.Test;

@Test(suiteName = "ServiceFunctionChain")
public class SFCTests extends AbstractTest {
	private static final String JSON_FLOW_CLASSIFIERS = "/sfc/flow_classifiers.json";

	@Test
	public void flowClassifiersList() throws Exception {
		respondWith(JSON_FLOW_CLASSIFIERS);
		List<? extends FlowClassifier> flowClassifiers = osv3().networking().sfc().listFlowClassifiers();
		server.takeRequest();
		assertNotNull(flowClassifiers);
		assertEquals(2, flowClassifiers.size());
		assertEquals(flowClassifiers.get(0).getName(), "FC1");
		assertEquals(flowClassifiers.get(1).getName(), "FC2");
	}
	
	@Override
	protected Service service() {
		return Service.NETWORK;
	}
}
