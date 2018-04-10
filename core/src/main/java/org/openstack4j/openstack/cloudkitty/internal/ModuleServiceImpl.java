package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.cloudkitty.ModuleService;
import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.Module;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittyModule;

import java.util.List;

public class ModuleServiceImpl extends CloudKittyBaseService implements ModuleService {

    @Override
    public List<? extends Module> list() {
        return get(CloudkittyModule.Modules.class, "/rating/modules").execute().getList();
    }

    @Override
    public Module get(String moduleId) {
        return get(CloudkittyModule.class, "/rating/modules/", moduleId).execute();
    }

    @Override
    public Module update(String moduleId, Module module) {
        return put(CloudkittyModule.class, "/rating/modules")
                .param("module_id", moduleId)
                .entity(module)
                .execute();
    }
}
