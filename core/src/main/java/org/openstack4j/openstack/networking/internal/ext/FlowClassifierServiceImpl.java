package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.FlowClassifierService;
import org.openstack4j.api.networking.ext.PortPairService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.FlowClassifierUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronFlowClassifier;
import org.openstack4j.openstack.networking.domain.ext.NeutronFlowClassifier.FlowClassifiers;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *  OpenStack (Neutron) Flow Classifier based Operations
 * @author Massimiliano Romano
 *
 */
public class FlowClassifierServiceImpl extends BaseNetworkingServices implements
		FlowClassifierService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FlowClassifier> list() {
		return get(FlowClassifiers.class, uri("/sfc/flow_classifiers")).execute().getList();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FlowClassifier> list(Map<String, String> filteringParams) {
		Invocation<FlowClassifiers> req = get(FlowClassifiers.class, uri("/sfc/flow_classifiers"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FlowClassifier get(String flowClassifierId) {
		checkNotNull(flowClassifierId);
		return get(NeutronFlowClassifier.class,uri("/sfc/flow_classifiers/%s",flowClassifierId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String flowClassifierId) {
		checkNotNull(flowClassifierId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,uri("/sfc/flow_classifiers/%s",flowClassifierId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FlowClassifier create(FlowClassifier flowClassifier) {
		checkNotNull(flowClassifier);
		return post(NeutronFlowClassifier.class,uri("/sfc/flow_classifiers")).entity(flowClassifier).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FlowClassifier update(String flowClassifierId, FlowClassifierUpdate flowClassifierUpdate) {
		checkNotNull(flowClassifierId);
		checkNotNull(flowClassifierUpdate);
		return put(NeutronFlowClassifier.class,uri("/sfc/flow_classifiers/%s",flowClassifierId)).entity(flowClassifierUpdate).execute();
	}

}
