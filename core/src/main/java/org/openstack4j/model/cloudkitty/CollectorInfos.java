package org.openstack4j.model.cloudkitty;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.CollectorInfosBuilder;

/**
 * A collector info object
 * @author mariusleu
 */
public interface CollectorInfos extends ModelEntity, Buildable<CollectorInfosBuilder> {

    /**
     * @return enabled
     */
    boolean isEnabled();

    /**
     * @return collector name
     */
    String getName();
}
