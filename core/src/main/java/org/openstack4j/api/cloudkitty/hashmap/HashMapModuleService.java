package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;

import java.util.List;

public interface HashMapModuleService extends RestService {

    List<Mapping.Type> types();
}
