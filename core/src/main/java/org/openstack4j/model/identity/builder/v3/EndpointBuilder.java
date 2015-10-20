package org.openstack4j.model.identity.builder.v3;

import java.net.URL;
import java.util.Map;

import org.openstack4j.api.types.Facing;
import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.builder.v3.EndpointBuilder;

/**
 * A Builder which creates an identity v3 endpoint.
 * 
 * 
 */
public interface EndpointBuilder extends Builder<EndpointBuilder, Endpoint> {

    /**
     * @see EndpointV3#getId()
     */
    EndpointBuilder id(String id);

    /**
     * @see EndpointV3#getDescription()
     */
    EndpointBuilder description(String description);

    /**
     * @see EndpointV3#getIface()
     */
    EndpointBuilder iface(Facing iface);

    /**
     * @see EndpointV3#getServiceId()
     */
    EndpointBuilder serviceId(String serviceId);

    /**
     * @see EndpointV3#getName()
     */
    EndpointBuilder name(String name);

    /**
     * @see EndpointV3#getRegion()
     */
    EndpointBuilder region(String region);

    /**
     * @see EndpointV3#getUrl()
     */
    EndpointBuilder url(URL url);

    /**
     * @see EndpointV3#getLinks()
     */
    EndpointBuilder links(Map<String, String> links);

}
