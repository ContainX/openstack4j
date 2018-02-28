package org.openstack4j.openstack.cloudkitty.internal.hashmap;

import org.openstack4j.api.Apis;
import org.openstack4j.api.cloudkitty.hashmap.*;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.openstack.cloudkitty.internal.CloudKittyBaseService;

import java.util.List;

public class HashMapServiceImpl extends CloudKittyBaseService implements HashMapService {

    @Override
    public List<Mapping.Type> types() {
        return get(Mapping.Types.class, PATH_HASHMAP, "/types").execute().getList();
    }

    @Override
    public HashMapFieldService field() {
        return Apis.get(HashMapFieldService.class);
    }

    @Override
    public HashMapMappingService mapping() {
        return Apis.get(HashMapMappingService.class);
    }

    @Override
    public HashMapGroupService group() {
        return Apis.get(HashMapGroupService.class);
    }

    @Override
    public HashMapServiceService service() {
        return Apis.get(HashMapServiceService.class);
    }

}
