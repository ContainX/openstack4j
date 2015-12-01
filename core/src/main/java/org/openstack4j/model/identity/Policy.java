package org.openstack4j.model.identity;

import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.identity.builder.PolicyBuilder;

/**
 * policy model class
 *
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#policies-v3">API reference</a>
 */
public interface Policy extends ModelEntity, Buildable<PolicyBuilder> {

    /**
     * the unique identifier
     * 
     * @return the id of the policy
     */
    String getId();

    /**
     * @return the type of the policy
     */
    String getType();

    /**
     * @return the BLOB of the policy
     */
    Map<String, String> getBlob();

    /**
     * @return the links of the policy
     */
    Map<String, String> getLinks();

}
