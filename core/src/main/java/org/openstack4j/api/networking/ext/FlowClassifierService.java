package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FlowClassifier;

/**
*
* Service Flow Classifier Service
*
* @author Dmitry Gerenrot
*
*/
public interface FlowClassifierService {

	/**
	 * Lists flow classifiers for port chains
	 *
	 * @return the list of flow classifiers
	 */
	List<? extends FlowClassifier> list();

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
	 * @param flowClassifier
	 * @return the action response
	 */
	ActionResponse delete(String flowClassifierId);

}
