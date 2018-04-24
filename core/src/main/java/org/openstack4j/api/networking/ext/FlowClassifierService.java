package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FlowClassifier;

/**
 *
 * Flow Classifier Service
 *
 * @author Dmitry Gerenrot
 *
 */
public interface FlowClassifierService extends RestService {

    /**
     * Lists flow classifiers for port chains
     *
     * @return the list of flow classifiers
     */
    List<? extends FlowClassifier> list();

    /**
     * Get a flow classifier by id.
     *
     * @param flowClassifierId
     * @return FlowClassifier
     */
    FlowClassifier get(String flowClassifierId);

    /**
     * Update a flow classifier with the given id to match the given update object
     *
     * @param flowClassifierId
     * @param flowClassifier
     * @return flowClassifier : object updated
     */
    FlowClassifier update(String flowClassifierId, FlowClassifier flowClassifier);

    /**
     * Create a flow classifier
     *
     * @param flowClassifier
     * @return flowClassifier : object actually created
     */
    FlowClassifier create(FlowClassifier flowClassifier);

    /**
     * Delete a flow classifier
     *
     * @param flowClassifierId
     * @return the action response
     */
    ActionResponse delete(String flowClassifierId);
}
