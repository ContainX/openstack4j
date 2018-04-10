package org.openstack4j.openstack.cloudkitty.internal.hashmap;

import org.openstack4j.api.cloudkitty.hashmap.HashMapFieldService;
import org.openstack4j.model.cloudkitty.hashmap.Field;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.domain.hashmap.HashMapField;
import org.openstack4j.openstack.cloudkitty.internal.CloudKittyBaseService;

import java.util.List;


public class HashMapFieldServiceImpl  extends CloudKittyBaseService implements HashMapFieldService {

    @Override
    public List<? extends Field> list() {
        return get(HashMapField.Fields.class, PATH_HASHMAP, "/fields").execute().getList();
    }

    @Override
    public Field get(String fieldId) {
        return get(HashMapField.class, PATH_HASHMAP, "/fields/", fieldId).execute();
    }

    @Override
    public Field create(Field field) {
        return post(HashMapField.class, PATH_HASHMAP, "/fields")
                .entity(field)
                .execute();
    }

    @Override
    public ActionResponse delete(String fieldId) {
        return delete(ActionResponse.class, PATH_HASHMAP, "/fields")
                .param("field_id", fieldId)
                .execute();
    }
}
