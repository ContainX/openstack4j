package org.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.ext.FlowClassifierService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.openstack.networking.domain.ext.NeutronFlowClassifier.FlowClassifiers;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * {@inheritDoc}
 */
public class FlowClassifierServiceImpl extends BaseNetworkingServices implements FlowClassifierService {

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
    public FlowClassifier create(FlowClassifier flowClassifier) {
        checkNotNull(flowClassifier);
        return post(FlowClassifier.class, uri("/sfc/flow_classifiers")).entity(flowClassifier).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String flowClassifierId) {
        checkNotNull(flowClassifierId);
        return deleteWithResponse(uri("/sfc/flow_classifiers/%s", flowClassifierId 	)).execute();
    }
}
