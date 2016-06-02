package org.openstack4j.model.senlin;

import org.openstack4j.model.ResourceEntity;

import java.util.Date;
import java.util.Map;

/**
 * This interface describes the getter-methods (and thus components) of a Profile.
 * All getters map to the possible return values of
 * <code> GET /v1/profiles/​{profile_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Profile extends ResourceEntity {

    /**
     * Returns the created at time of the profile
     *
     * @return the created at time of the profile
     */
    Date getCreatedAt();

    /**
     * Returns the domain of the profile
     *
     * @return the domain of the profile
     */
    String getDomain();

    /**
     * Returns the project of the profile
     *
     * @return the project of the profile
     */
    String getProject();

    /**
     * Returns the metadata of the profile
     *
     * @return the metadata of the profile
     */
    Map<String, Map> getMetadata();

    /**
     * Returns the spec of the profile
     *
     * @return the spec of the profile
     */
    Map<String, Object> getSpec();
}
