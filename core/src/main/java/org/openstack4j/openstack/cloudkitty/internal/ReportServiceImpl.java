package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.cloudkitty.ReportService;
import org.openstack4j.model.cloudkitty.Summary;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittySummary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportServiceImpl extends CloudKittyBaseService implements ReportService {


    @Override
    public List<? extends Summary> summary(Date begin, Date end, String tenantId, String service, String groupBy, boolean allTenants) {
        return get(CloudkittySummary.Summaries.class, "/report/summary")
                .param("begin", begin)
                .param("end", end)
                .param("tenant_id", tenantId)
                .param("service", service)
                .param("groupby", groupBy)
                .param("all_tenants", allTenants)
                .execute()
                .getList();
    }

    @Override
    public List<String> tenants(Date begin, Date end) {
        return get(ArrayList.class, "/report/tenants").execute();
    }

    @Override
    public float total(Date begin, Date end, String tenantId, String service, boolean allTenants) {
        return get(Float.class, "/report/total")
                .param("begin", begin)
                .param("end", end)
                .param("tenant_id", tenantId)
                .param("service", service)
                .param("all_tenants", allTenants)
                .execute();
    }
}
