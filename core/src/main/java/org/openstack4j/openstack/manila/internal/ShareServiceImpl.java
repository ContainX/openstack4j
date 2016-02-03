package org.openstack4j.openstack.manila.internal;

import org.openstack4j.api.manila.ShareService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.openstack.common.ExtensionValue.ManilaExtensions;

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
}
