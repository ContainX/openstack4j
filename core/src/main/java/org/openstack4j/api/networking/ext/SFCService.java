package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.network.ext.FlowClassifier;

/**
 * 
 * Service Function Chain service
 * 
 * @author Dmitry Gerenrot
 *
 */
public interface SFCService extends RestService {
	
	/**
	 * Lists flow classifiers for port chains
	 * 
	 * @return the list of flow classifiers
	 */
	List<? extends FlowClassifier> listFlowClassifiers();
}
