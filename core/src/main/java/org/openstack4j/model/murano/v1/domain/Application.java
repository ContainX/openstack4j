package org.openstack4j.model.murano.v1.domain;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.murano.v1.builder.ApplicationBuilder;

import java.util.Map;

/**
 * @author Nikolay Mahotkin.
 */
public interface Application extends ModelEntity/*, Buildable<ApplicationBuilder>*/ {
    /**
     *
     * @return data in raw format (json-string).
     */
    Object getData();

    /**
     *
     * @return data in map model.
     */
    //Map<String, Object> getDataMap();
}
