package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Field;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

public interface HashMapFieldService extends RestService {

    List<? extends Field> list();

    Field get(String fieldId);

    Field create(Field field);

    ActionResponse delete(String fieldId);
}
