package org.openstack4j.api.manila;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.Limits;

import java.util.List;

/**
 * Shared File Systems API (Manila)
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareService extends RestService {
    /**
     * @return a list of available Shared File Systems API extensions
     */
    List<? extends Extension> listExtensions();

    /**
     * @return the resource limitations allowed for the tenant
     */
    Limits limits();

    /**
     * @return service which provides methods to manage security services
     */
    SecurityServiceService securityServices();
}
