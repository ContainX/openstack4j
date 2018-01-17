package org.openstack4j.openstack.networking.internal.ext;

import java.util.List;

import org.openstack4j.api.networking.ext.SFCService;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.openstack.networking.domain.ext.NeutronFlowClassifier.FlowClassifiers;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Tests Compute -> Service Function Chain API against the mock webserver and spec based
 * json responses 
 * 
 * @author Dmitry Gerenrot
 *
 */
public class SFCServiceImpl extends BaseNetworkingServices implements SFCService {
	@Override
	public List<? extends FlowClassifier> listFlowClassifiers() {
		return get(FlowClassifiers.class, uri("/sfc/flow_classifiers")).execute().getList();
	}
}
