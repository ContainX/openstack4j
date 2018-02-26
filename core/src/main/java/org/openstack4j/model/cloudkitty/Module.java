package org.openstack4j.model.cloudkitty;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.ModuleBuilder;

/**
 * A module object
 * @author mariusleu
 */
public interface Module extends ModelEntity, Buildable<ModuleBuilder> {

    /**
     * @return Short description of the module.
     */
    String getDescription();

    /**
     * @return enabled status
     */
    boolean isEnabled();

    /**
     * @return On-the-fly configuration support
     */
    boolean isHotConfig();

    /**
     * @return priority of the module
     */
    int getPriority();

    /**
     * @return name of the module
     */
    String getModuleId();
}
