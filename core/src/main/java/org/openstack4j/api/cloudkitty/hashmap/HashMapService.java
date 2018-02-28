package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.cloudkitty.hashmap.Service;
import org.openstack4j.model.cloudkitty.hashmap.Threshold;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * The HashMap service
 * @author mariusleu
 */
public interface HashMapService extends RestService {

    /**
     * @return the available mapping types
     */
    List<Mapping.Type> types();

    /**
     * @return the hashmap field service
     */
    HashMapFieldService field();

    /**
     * @return the hashmap mapping service
     */
    HashMapMappingService mapping();

    /**
     * @return the hashmap group service
     */
    HashMapGroupService group();

    /**
     * @return the service for the hashmap service entity
     */
    HashMapServiceService service();
}
