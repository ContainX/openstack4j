package org.openstack4j.api.cloudkitty;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.Module;
import org.openstack4j.model.cloudkitty.Resource;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * Cloudkitty V1 Rating service
 *
 * @author mariusleu
 */
public interface RatingService extends RestService {
    /**
     * @return the list of loaded modules
     */
    List<? extends Module> getModules();

    /**
     * @param moduleId module name
     * @return a module
     */
    Module getModule(String moduleId);

    /**
     * Change the state and priority of a module
     *
     * @param moduleId name of the module to modify
     * @param module   module object describing the new desired state
     * @return module
     */
    Module updateModule(String moduleId, Module module);

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
    ActionResponse reloadModules();
}
