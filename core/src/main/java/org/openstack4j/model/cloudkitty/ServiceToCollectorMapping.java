package org.openstack4j.model.cloudkitty;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.ServiceToCollectorMappingBuilder;

/**
 * A service to collector mapping
 * @author mariusleu
 */
public interface ServiceToCollectorMapping extends ModelEntity, Buildable<ServiceToCollectorMappingBuilder> {

    /**
     * @return collector name
     */
    String getCollector();

    /**
     * @return service name
     */
    String getService();
}
