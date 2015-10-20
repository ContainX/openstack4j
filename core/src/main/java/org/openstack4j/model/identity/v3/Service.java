package org.openstack4j.model.identity.v3;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.identity.builder.v3.ServiceBuilder;

/**
 * service v3 role model class
 * 
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#service-catalog-v3">API reference</a>
 */
public interface Service extends ModelEntity, Buildable<ServiceBuilder> {

    /**
     * @return the id of the service
     */
    String getId();

    /**
     * @return the description of the service
     */
    String getDescription();

    /**
     * @return the name of the service
     */
    String getName();

    /**
     * @return the type of the service
     */
    String getType();

    /**
     * @return the list of endpoints
     */
    List<? extends Endpoint> getEndpoints();

    /**
     * @return the links of the service
     */
    Map<String, String> getLinks();

}