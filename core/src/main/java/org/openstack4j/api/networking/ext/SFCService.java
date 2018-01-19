package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;

/**
 * OpenStack Neutron SFC Operations API
 *
 * @author Dmitry Gerenrot
 *
 */
public interface SFCService extends RestService {
    /**
     * @return the Flow Classifier Service API
     */
     FlowClassifierService flowclassifiers();
}
