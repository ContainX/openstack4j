package org.openstack4j.api.cloudkitty.pyscripts;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.pyscripts.Script;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * A pyscripts service
 * @author mariusleu
 */
public interface PyScriptsService extends RestService {

    /**
     * Get the script list
     *
     * @param noData Set to True to remove script data from output
     * @return list of every scripts
     */
    List<? extends Script> list(boolean noData);

    /**
     * Get a script by id
     *
     * @param scriptId script id
     * @return script
     */
    Script get(String scriptId);

    /**
     * Create a script
     *
     * @param script script object
     * @return newly created script
     */
    Script create(Script script);

    /**
     * Update a script
     *
     * @param scriptId script id to be updated
     * @param script script data to update
     * @return script
     */
    Script update(String scriptId, Script script);

    /**
     * Delete a script
     *
     * @param scriptId script id to be deleted
     * @return action response
     */
    ActionResponse delete(String scriptId);
}
