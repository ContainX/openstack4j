package org.openstack4j.openstack.cloudkitty.internal.hashmap;

import org.openstack4j.api.cloudkitty.hashmap.HashMapMappingService;
import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapGroup;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapMapping;
import org.openstack4j.openstack.cloudkitty.internal.CloudKittyBaseService;

import java.util.List;

public class HashMapMappingServiceImpl extends CloudKittyBaseService implements HashMapMappingService {

    @Override
    public List<? extends Mapping> list() {
        return get(HashMapMapping.Mappings.class, PATH_HASHMAP, "/mappings").execute().getList();
    }

    @Override
    public Mapping get(String mappingId) {
        return get(HashMapMapping.class, PATH_HASHMAP, "/mappings/", mappingId).execute();
    }

    @Override
    public Mapping create(Mapping mapping) {
        return post(HashMapMapping.class, PATH_HASHMAP, "/mappings")
                .entity(mapping)
                .execute();
    }

    @Override
    public ActionResponse update(String mappingId, Mapping mapping) {
        return put(ActionResponse.class, PATH_HASHMAP, "/mappings")
                .param("mapping_id", mappingId)
                .entity(mapping)
                .execute();
    }

    @Override
    public ActionResponse delete(String mappingId) {
        return deleteWithResponse(PATH_HASHMAP, "/mappings")
                .param("mapping_id", mappingId)
                .execute();
    }

    @Override
    public Group group(String mappingId) {
        return get(HashMapGroup.class, PATH_HASHMAP, "/mappings/group")
                .param("mapping_id", mappingId)
                .execute();
    }
}
