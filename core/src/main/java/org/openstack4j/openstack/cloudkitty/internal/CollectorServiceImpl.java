package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.cloudkitty.CollectorService;
import org.openstack4j.model.cloudkitty.CollectorInfos;
import org.openstack4j.model.cloudkitty.ServiceToCollectorMapping;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittyCollectorInfos;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittyServiceToCollectorMapping;

import java.util.List;

public class CollectorServiceImpl extends CloudKittyBaseService implements CollectorService {

    @Override
    public List<? extends ServiceToCollectorMapping> list() {
        return get(CloudkittyServiceToCollectorMapping.Mappings.class, "/collector/mappings").execute().getList();
    }

    @Override
    public ServiceToCollectorMapping get(String service) {
        return get(CloudkittyServiceToCollectorMapping.class, "/collector/mappings/", service).execute();
    }

    @Override
    public ServiceToCollectorMapping create(String collector, String service) {
        return post(CloudkittyServiceToCollectorMapping.class, "/collector/mappings")
                .param("collector", collector)
                .param("service", service)
                .execute();
    }

    @Override
    public ActionResponse delete(String service) {
        return deleteWithResponse("/collector/mappings")
                .param("service", service)
                .execute();
    }

    @Override
    public CollectorInfos setState(String collector, CollectorInfos infos) {
        return put(CloudkittyCollectorInfos.class, "/collector/states")
                .param("name", collector)
                .entity(infos)
                .execute();
    }
}
