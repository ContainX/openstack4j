package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.cloudkitty.StorageService;
import org.openstack4j.model.cloudkitty.DataFrame;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittyDataFrame;

import java.util.Date;
import java.util.List;

public class StorageServiceImpl extends CloudKittyBaseService implements StorageService {

    @Override
    public List<? extends DataFrame> dataFrames(Date begin, Date end, String tenantId, String resourceType) {
        return get(CloudkittyDataFrame.DataFrames.class, "/storage/dataframes")
                .param("begin", begin)
                .param("end", end)
                .param("tenant_id", tenantId)
                .param("resource_type", resourceType)
                .execute()
                .getList();
    }
}
