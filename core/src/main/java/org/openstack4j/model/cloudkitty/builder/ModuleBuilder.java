package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.Module;

public interface ModuleBuilder extends Buildable.Builder<ModuleBuilder, Module> {

    ModuleBuilder description(String description);

    ModuleBuilder enabled(boolean enabled);

    ModuleBuilder supportsHotConfig(boolean hotConfig);

    ModuleBuilder moduleId(String moduleId);

    ModuleBuilder priority(int priority);
}
