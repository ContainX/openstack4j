package org.openstack4j.model.murano.v1.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.murano.v1.domain.Application;

/**
 * @author Nikolay Mahotkin.
 */
public interface ApplicationBuilder /*extends Builder<ApplicationBuilder, Application>*/ {

    ApplicationBuilder dataRaw(String jsonString);
}
