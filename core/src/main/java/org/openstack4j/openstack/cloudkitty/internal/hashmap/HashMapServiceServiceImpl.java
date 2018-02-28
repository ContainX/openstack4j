package org.openstack4j.openstack.cloudkitty.internal.hashmap;

import org.openstack4j.api.cloudkitty.hashmap.HashMapServiceService;
import org.openstack4j.model.cloudkitty.hashmap.Service;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.internal.CloudKittyBaseService;

import java.util.List;

public class HashMapServiceServiceImpl extends CloudKittyBaseService implements HashMapServiceService {

    @Override
    public List<? extends Service> list() {
        return get(org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapService.Services.class,
                PATH_HASHMAP, "/services"
        ).execute().getList();
    }

    @Override
    public Service get(String serviceId) {
        return get(org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapService.class,
                PATH_HASHMAP, "/services/", serviceId
        ).execute();
    }

    @Override
    public Service create(Service service) {
        return post(org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapService.class,
                PATH_HASHMAP, "/services"
        ).entity(service).execute();
    }

    @Override
    public ActionResponse delete(String serviceId) {
        return deleteWithResponse(PATH_HASHMAP, "/services").param("service_id", serviceId).execute();
    }
}
