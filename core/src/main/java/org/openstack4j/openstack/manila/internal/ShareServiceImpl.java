package org.openstack4j.openstack.manila.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.manila.SecurityServiceService;
import org.openstack4j.api.manila.ShareNetworkService;
import org.openstack4j.api.manila.ShareService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.Limits;
import org.openstack4j.openstack.common.ExtensionValue.ManilaExtensions;
import org.openstack4j.openstack.manila.domain.ManilaLimits;

import java.util.List;

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
}
