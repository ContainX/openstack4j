package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * A HashMap Mapping service
 * @author mariusleu
 */
public interface HashMapMappingService extends RestService {

    /**
     * @return all the mappings list
     */
    List<? extends Mapping> list();

    /**
     * Get a mapping
     *
     * @param mappingId mapping id
     * @return mapping
     */
    Mapping get(String mappingId);

    /**
     * Create a mapping
     *
     * @param mapping mapping object
     * @return newly created mapping
     */
    Mapping create(Mapping mapping);

    /**
     * Update a mapping
     *
     * @param mappingId mapping id to be updated
     * @param mapping mapping object to insert
     * @return action response
     */
    ActionResponse update(String mappingId, Mapping mapping);

    /**
     * Delete a mapping
     *
     * @param mappingId id of the mapping to be deleted
     * @return action response
     */
    ActionResponse delete(String mappingId);

    /**
     * Get the group attached to the mapping
     *
     * @param mappingId mapping id
     * @return the group
     */
    Group group(String mappingId);
}
