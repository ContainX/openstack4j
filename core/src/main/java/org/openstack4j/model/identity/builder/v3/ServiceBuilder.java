package org.openstack4j.model.identity.builder.v3;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.v3.Service;

/**
 * A Builder which creates a identity v3 service
 * 
 * 
 */
public interface ServiceBuilder extends Builder<ServiceBuilder, Service> {

    /**
     * @see Service#getId()
     */
    ServiceBuilder id(String id);

    /**
     * @see Service#getDescription()
     */
    ServiceBuilder description(String description);

    /**
     * @see Service#getType()
     */
    ServiceBuilder type(String type);

    /**
     * @see Service#getName()
     */
    ServiceBuilder name(String name);

    /**
     * @see Service#getEndpoints()
     */
    ServiceBuilder endpoints(List<? extends Endpoint> endpoints);

    /**
     * @see Service#getLinks()
     */
    ServiceBuilder links(Map<String, String> links);

}
