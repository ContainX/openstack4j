package org.openstack4j.model.cloudkitty;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.ServiceInfoBuilder;

import java.util.List;

/**
 * A cloudkitty service info object
 * @author mariusleu
 */
public interface ServiceInfo extends ModelEntity, Buildable<ServiceInfoBuilder> {

    /**
     * @return list of metadata fields
     */
    List<String> getMetadata();

    /**
     * @return the service id
     */
    String getServiceId();

    /**
     * @return the unit
     */
    String unit();
}
