package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.cloudkitty.hashmap.HashMapService;
import org.openstack4j.api.cloudkitty.pyscripts.PyScriptsService;
import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.Resource;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * Rating service
 * @author mariusleu
 */
public interface RatingService extends RestService {

    /**
     * @return the info service
     */
    InfoService info();

    /**
     * @return the collector service
     */
    CollectorService collector();

    /**
     * @return the reporting service
     */
    ReportService reporting();

    /**
     * @return the module service
     */
    ModuleService module();

    /**
     * @return the storage service
     */
    StorageService storage();

    /**
     * @return the hashmap module services
     */
    HashMapService hashmap();

    /**
     * @return the pyscripts module service
     */
    PyScriptsService pyscripts();

    /**
     * Get an instant quote based on multiple resource description
     *
     * @param resData List of resource descriptions
     * @return float quote
     */
    float quote(List<? extends Resource> resData);

    /**
     * Trigger a module list reload
     * @return action response
     */
    ActionResponse reload();
}
