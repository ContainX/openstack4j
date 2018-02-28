package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.cloudkitty.hashmap.Threshold;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * A hashmap group service
 * @author mariusleu
 */
public interface HashMapGroupService extends RestService {

    /**
     * @return the list of groups
     */
    List<? extends Group> list();

    /**
     * Get a group by id
     *
     * @param groupId group id
     * @return group
     */
    Group get(String groupId);

    /**
     * Create a group
     *
     * @param group group object
     * @return newly created group
     */
    Group create(Group group);

    /**
     * Delete a group
     *
     * @param groupId group id
     * @param recursive delete mappings recursively
     * @return action response
     */
    ActionResponse delete(String groupId, boolean recursive);

    /**
     * Get the mappings attached to the group
     *
     * @param groupId group id
     * @return list of mapping objects
     */
    List<? extends Mapping> mappings(String groupId);

    /**
     * Get the thresholds attached to the group
     *
     * @param groupId group id
     * @return the list of threshold objects
     */
    List<? extends Threshold> thresholds(String groupId);
}
