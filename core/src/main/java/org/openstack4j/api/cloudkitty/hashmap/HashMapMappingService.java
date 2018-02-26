package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

public interface HashMapMappingService extends RestService {

    List<? extends Mapping> list();

    Mapping get(String mappingId);

    Mapping create(Mapping mapping);

    Mapping update(String mappingId, Mapping mapping);

    ActionResponse delete(String mappingId);

    List<? extends Group> group(String mappingId);
}
