package org.openstack4j.api.cloudkitty;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.Summary;

import java.util.Date;
import java.util.List;

/**
 * Report service
 */
public interface ReportService extends RestService {

    List<? extends Summary> summary(Date begin, Date end, String tenantId, String service, String groupBy, boolean allTenants);

    List<String> tenants(Date begin, Date end);

    float total(Date begin, Date end, String tenantId, String service, boolean allTenants);
}
