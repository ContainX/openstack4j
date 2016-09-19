package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.compute.HostService;
import org.openstack4j.model.compute.HostResource;
import org.openstack4j.openstack.compute.domain.NovaHost;

/**
 * OS Host Service
 * 
 * @author Qin An
 *
 */
public class HostServiceImpl extends BaseComputeServices implements HostService {

    @Override
	public List<? extends HostResource> hostDescribe(String hostName) {
		checkNotNull(hostName);
        return get(NovaHost.class, uri("/os-hosts/%s", hostName)).execute().getList();
    }

}
