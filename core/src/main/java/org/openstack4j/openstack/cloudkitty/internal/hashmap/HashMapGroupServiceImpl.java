package org.openstack4j.openstack.cloudkitty.internal.hashmap;

import org.openstack4j.api.cloudkitty.hashmap.HashMapGroupService;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.cloudkitty.hashmap.Threshold;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapGroup;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapMapping;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapThreshold;
import org.openstack4j.openstack.cloudkitty.internal.CloudKittyBaseService;

import java.util.List;

public class HashMapGroupServiceImpl extends CloudKittyBaseService implements HashMapGroupService {

    @Override
    public List<? extends Group> list() {
        return get(HashMapGroup.Groups.class, PATH_HASHMAP, "/groups").execute().getList();
    }

    @Override
    public Group get(String groupId) {
        return get(HashMapGroup.class, PATH_HASHMAP, "/groups/", groupId).execute();
    }

    @Override
    public Group create(Group group) {
        return post(HashMapGroup.class, PATH_HASHMAP, "/groups")
                .entity(group)
                .execute();
    }

    @Override
    public ActionResponse delete(String groupId, boolean recursive) {
        return deleteWithResponse(PATH_HASHMAP, "/groups")
                .param("group_id", groupId)
                .param("recursive", recursive)
                .execute();
    }

    @Override
    public List<? extends Mapping> mappings(String groupId) {
        return get(HashMapMapping.Mappings.class, PATH_HASHMAP, "/groups/mappings")
                .param("group_id", groupId)
                .execute()
                .getList();

    }

    @Override
    public List<? extends Threshold> thresholds(String groupId) {
        return get(HashMapThreshold.Thresholds.class, PATH_HASHMAP, "/groups/thresholds")
                .param("group_id", groupId)
                .execute()
                .getList();
    }
}
