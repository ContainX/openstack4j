package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Service;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

public interface HashMapServiceService extends RestService {

    List<? extends Service> list();

    Service get(String serviceId);

    Service create(Service service);

    ActionResponse delete(String serviceId);
}
