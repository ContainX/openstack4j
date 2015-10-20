package org.openstack4j.model.identity.builder.v3;

import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.v3.Policy;

/**
 * 
 * the policy builder
 *
 */
public interface PolicyBuilder extends Builder<PolicyBuilder, Policy> {

    /**
     * @see Policy#getId()
     */
    PolicyBuilder id(String id);

    /**
     * @see Policy#getType()
     */
    PolicyBuilder type(String type);

    /**
     * @see Policy#getBlob()
     */
    PolicyBuilder blob(Map<String, String> blob);

    /**
     * @see Policy#getLinks()
     */
    PolicyBuilder links(Map<String, String> links);

}
