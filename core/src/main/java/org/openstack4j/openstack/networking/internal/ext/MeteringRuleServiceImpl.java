package org.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.ext.MeteringRuleService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.MeteringRule;
import org.openstack4j.openstack.networking.domain.NeutronMeteringRule;
import org.openstack4j.openstack.networking.domain.NeutronMeteringRule.MeteringRules;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) Metering Rule Extension API
 * 
 * @author Caio Bergamasco
 */
public class MeteringRuleServiceImpl extends BaseNetworkingServices implements MeteringRuleService {

    @Override
    public List<? extends MeteringRule> get() {
        return get(MeteringRules.class, uri("/metering/metering-label-rules")).execute().getList();
    }

    @Override
    public MeteringRule get(String meteringId) {
        checkNotNull(meteringId);
        return get(NeutronMeteringRule.class, uri("/metering/metering-label-rules/%s", meteringId)).execute();
    }

    @Override
    public MeteringRule create(MeteringRule metering) {
        checkNotNull(metering);
        return post(NeutronMeteringRule.class, uri("/metering/metering-label-rules")).entity(metering).execute();
    }

    @Override
    public ActionResponse delete(String meteringId) {
        checkNotNull(meteringId);
        return deleteWithResponse(uri("/metering/metering-label-rules/%s", meteringId)).execute();
    }

}
