package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Field;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * A hashmap field service
 * @author mariusleu
 */
public interface HashMapFieldService extends RestService {

    /**
     * @return the list of fields
     */
    List<? extends Field> list();

    /**
     * Get a field by id
     *
     * @param fieldId field id
     * @return field
     */
    Field get(String fieldId);

    /**
     * Create a field
     *
     * @param field field object
     * @return newly created field
     */
    Field create(Field field);

    /**
     * Delete a field
     *
     * @param fieldId field id
     * @return action resopnse
     */
    ActionResponse delete(String fieldId);
}
