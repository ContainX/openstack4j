package org.openstack4j.openstack.cloudkitty.internal.pyscripts;

import org.openstack4j.api.cloudkitty.pyscripts.PyScriptsService;
import org.openstack4j.model.cloudkitty.pyscripts.Script;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.domain.pyscripts.PyScriptsScript;
import org.openstack4j.openstack.cloudkitty.internal.CloudKittyBaseService;

import java.util.List;

public class PyScriptsServiceImpl extends CloudKittyBaseService implements PyScriptsService {


    @Override
    public List<? extends Script> list(boolean noData) {
        return get(PyScriptsScript.Scripts.class, PATH_PYSCRIPTS, "/scripts")
                .param("no_data", noData)
                .execute()
                .getList();
    }

    @Override
    public Script get(String scriptId) {
        return get(PyScriptsScript.class, PATH_PYSCRIPTS, "/scripts/", scriptId).execute();
    }

    @Override
    public Script create(Script script) {
        return post(PyScriptsScript.class, PATH_PYSCRIPTS, "/scripts")
                .entity(script)
                .execute();
    }

    @Override
    public Script update(String scriptId, Script script) {
        return put(PyScriptsScript.class, PATH_PYSCRIPTS, "/scripts")
                .param("script_id", scriptId)
                .entity(script)
                .execute();
    }

    @Override
    public ActionResponse delete(String scriptId) {
        return deleteWithResponse(PATH_PYSCRIPTS, "/scripts")
                .param("script_id", scriptId)
                .execute();
    }
}
