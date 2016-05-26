package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.ext.MeteringLabelService;
import org.openstack4j.api.networking.ext.MeteringRuleService;
import org.openstack4j.api.networking.ext.MeteringService;

/**
 * Networking (Neutron) Metering Service
 * 
 * @author Caio Bergamasco
 */
public class MeteringServiceImpl implements MeteringService {

    @Override
    public MeteringLabelService meteringLabel() {
        return Apis.get(MeteringLabelService.class);
    }

    @Override
    public MeteringRuleService meteringRule() {
        return Apis.get(MeteringRuleService.class);
    }


}
