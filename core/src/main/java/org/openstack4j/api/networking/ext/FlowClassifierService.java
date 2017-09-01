package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.FlowClassifierUpdate;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairUpdate;

import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron) Flow Classifier Extension API
 * @author Massimiliano Romano
 *
 */
public interface FlowClassifierService extends RestService {
    /**
     * List all flow classifiers  that the current tenant has access to
     *
     * @return list of all flow classifiers
     */
    List<? extends FlowClassifier> list();

    /**
     * Returns list of flow classifiers filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of flow classifiers fitered by filteringParams
     */
    List<? extends FlowClassifier> list(Map<String, String> filteringParams);


    /**
     * Get the specified flow classifiers by ID
     *
     * @param flowClassifierId the flow classifiers identifier
     * @return the flow classifiers or null if not found
     */
    FlowClassifier get(String flowClassifierId);
    
    /**
     * Delete the specified flow classifiers by ID
     * @param flowClassifierId the flow classifiers identifier
     * @return the action response
     */
    ActionResponse delete(String flowClassifierId);
    /**
     * Create a flow classifiers
     * @param flowClassifier flow classifiers
     * @return flow classifiers
     */
    FlowClassifier create(FlowClassifier flowClassifier);
    /**
     * Update a flow classifiers
     * @param flowClassifierId the flow classifiers identifier
     * @param flowClassifierUpdate FlowClassifierUpdate
     * @return FlowClassifier
     */
    FlowClassifier update(String flowClassifierId, FlowClassifierUpdate flowClassifierUpdate);
}
