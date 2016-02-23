package org.openstack4j.openstack.manila.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.manila.*;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.Limits;
import org.openstack4j.model.manila.Service;
import org.openstack4j.openstack.common.ExtensionValue.ManilaExtensions;
import org.openstack4j.openstack.manila.domain.ManilaLimits;
import org.openstack4j.openstack.manila.domain.ManilaService;
import org.openstack4j.openstack.manila.domain.actions.ServiceAction;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Shared File Systems API (Manila)
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ShareServiceImpl extends BaseShareServices implements ShareService {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Extension> listExtensions() {
        return get(ManilaExtensions.class, uri("/extensions")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Limits limits() {
        return get(ManilaLimits.class, uri("/limits")).execute();
    }

    /**
     * {@inheritDoc}
     */
    public SharesService shares() {
        return Apis.get(SharesService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityServiceService securityServices() {
        return Apis.get(SecurityServiceService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareNetworkService shareNetworks() {
        return Apis.get(ShareNetworkService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareServerService shareServers() {
        return Apis.get(ShareServerService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareInstanceService shareInstances() {
        return Apis.get(ShareInstanceService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerStatsService schedulerStats() {
        return Apis.get(SchedulerStatsService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Service> services() {
        return get(ManilaService.Services.class, uri("/os-services")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ManilaService.ServiceStatus enableService(String binary, String host) {
        checkNotNull(binary);
        checkNotNull(host);

        return put(ManilaService.ServiceStatus.class, uri("/os-services/enable"))
                .entity(ServiceAction.enable(binary, host))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ManilaService.ServiceStatus disableService(String binary, String host) {
        checkNotNull(binary);
        checkNotNull(host);

        return put(ManilaService.ServiceStatus.class, uri("/os-services/disable"))
                .entity(ServiceAction.disable(binary, host))
                .execute();
    }
}
