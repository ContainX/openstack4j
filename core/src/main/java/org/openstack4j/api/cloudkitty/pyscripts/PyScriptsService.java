package org.openstack4j.api.cloudkitty.pyscripts;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.pyscripts.Script;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

public interface PyScriptsService extends RestService {

    List<? extends Script> list(boolean noData);

    Script get(String scriptId);

    Script create(Script script);

    Script update(String scriptId, Script script);

    ActionResponse delete(String scriptId);
}
