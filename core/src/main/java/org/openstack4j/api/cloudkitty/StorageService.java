package org.openstack4j.api.cloudkitty;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.DataFrame;

import java.util.Date;
import java.util.List;

public interface StorageService extends RestService {

    /**
     * Get a list of rated resources for a time period and a tenant
     *
     * @param begin Start of the period
     * @param end End of the period
     * @param tenantId UUID of the tenant to filter on
     * @param resourceType Type of the resource to filter on
     * @return the list of data frames
     */
    List<? extends DataFrame> dataFrames(Date begin, Date end, String tenantId, String resourceType);
}
