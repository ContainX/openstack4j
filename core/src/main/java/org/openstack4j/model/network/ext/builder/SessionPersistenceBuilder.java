package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.SessionPersistence;
import org.openstack4j.model.network.ext.SessionPersistenceType;

/**
 * A builder to create and update a SessionPersistence
 *
 * @author liujunpeng
 */
public interface SessionPersistenceBuilder extends Builder<SessionPersistenceBuilder, SessionPersistence> {

    /**
     * required
     *
     * @param type APP_COOKIE,HTTP_COOKIE,SOURCE_IP
     * @return SessionPersistenceBuilder
     */
    SessionPersistenceBuilder type(SessionPersistenceType type);

    /**
     * optional
     *
     * @param cookieName
     * @return SessionPersistenceBuilder
     */
    SessionPersistenceBuilder cookieName(String cookieName);
}
