package org.openstack4j.openstack.bareMetal.internal;

import org.openstack4j.api.bareMetal.PortService;
import org.openstack4j.model.bareMetal.Port;
import org.openstack4j.model.bareMetal.PortUpdate;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.bareMetal.domain.BareMetalPort;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Port (Bare Metal Port) Operation API implementation
 *
 * @author zhangliang
 */
public class PortServiceImpl extends BaseBareMetalServices implements PortService {

    @Override
    public List<? extends Port> list() {
        return get(BareMetalPort.Ports.class, uri("/ports")).execute().getList();
    }

    @Override
    public Port create(Port port) {
        checkNotNull(port);
        return post(BareMetalPort.class, uri("/ports")).entity(port).execute();
    }

    @Override
    public ActionResponse delete(String portId) {
        checkNotNull(portId);
        return deleteWithResponse(uri("/ports/%s", portId)).execute();
    }

    @Override
    public Port update(String portId, PortUpdate portUpdate) {
        checkNotNull(portId);
        checkNotNull(portUpdate);
        return patch(BareMetalPort.class, uri("/ports/%s", portId)).entity(portUpdate).execute();
    }

}
