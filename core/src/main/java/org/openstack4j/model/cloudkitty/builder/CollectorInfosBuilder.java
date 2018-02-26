package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.CollectorInfos;

/**
 * A collector infos buiolder
 * @author mariusleu
 */
public interface CollectorInfosBuilder extends Buildable.Builder<CollectorInfosBuilder, CollectorInfos> {

    CollectorInfosBuilder enabled(boolean enabled);

    CollectorInfosBuilder name(String name);
}
