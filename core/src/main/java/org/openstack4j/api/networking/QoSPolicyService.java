package org.openstack4j.api.networking;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.QoSPolicy;
import org.openstack4j.model.network.options.QoSPolicyListOptions;

import java.util.List;

public interface QoSPolicyService extends RestService {

    /**
     * Lists all QoS policies authorized by the current Tenant
     *
     * @return the list of QoS policies
     */
    List<? extends QoSPolicy> list();

    /**
     * Lists all QoS policies authorized by the current Tenant
     *
     * @param options filtering options
     * @return the list of QoS policies
     */
    List<? extends QoSPolicy> list(QoSPolicyListOptions options);

    /**
     * Gets the QoS policy by ID
     *
     * @param policyId the QoS QoSPolicy identifier
     * @return the QoSPolicy or null if not found
     */
    QoSPolicy get(String policyId);

    /**
     * Delete a QoSPolicy by ID
     *
     * @param policyId the policy identifier to delete
     * @return the action response
     */
    ActionResponse delete(String policyId);

    /**
     * Creates a new QoSPolicy
     * @param policy the QoSPolicy to create
     * @return the newly create QoSPolicy
     */
    QoSPolicy create(QoSPolicy policy);

    /**
     * Updates an existing QoSPolicy.  The QoSPolicy identifier must be set on the policy object to be successful
     * @param policy the QoSPolicy to update
     * @return the updated QoSPolicy
     */
    QoSPolicy update(QoSPolicy policy);
}
