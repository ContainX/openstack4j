package org.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.ext.MeteringLabelService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.MeteringLabel;
import org.openstack4j.openstack.networking.domain.NeutronMeteringLabel;
import org.openstack4j.openstack.networking.domain.NeutronMeteringLabel.MeteringLabels;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) Metering Label Extension API
 * 
 * @author Caio Bergamasco
 */
public class MeteringLabelServiceImpl extends BaseNetworkingServices implements MeteringLabelService {

    @Override
    public List<? extends MeteringLabel> get() {
        return get(MeteringLabels.class, uri("/metering/metering-labels")).execute().getList();
    }

    @Override
    public MeteringLabel get(String meteringId) {
        checkNotNull(meteringId);
        return get(NeutronMeteringLabel.class, uri("/metering/metering-labels/%s", meteringId)).execute();
    }

    @Override
    public MeteringLabel create(MeteringLabel metering) {
        checkNotNull(metering);
        return post(NeutronMeteringLabel.class, uri("/metering/metering-labels")).entity(metering).execute();
    }

    @Override
    public ActionResponse delete(String meteringId) {
        checkNotNull(meteringId);
        return deleteWithResponse(uri("/metering/metering-labels/%s", meteringId)).execute();
    }

}
