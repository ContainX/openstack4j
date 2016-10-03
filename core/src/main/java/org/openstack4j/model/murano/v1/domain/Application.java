package org.openstack4j.model.murano.v1.domain;

import org.openstack4j.model.ModelEntity;

/**
 * @author Nikolay Mahotkin.
 */
public interface Application extends ModelEntity {
    /**
     *
     * @return data in raw format (json-string).
     */
    Object getData();
}
