package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.Member;
import org.openstack4j.model.network.ext.SessionPersistence;

/**
 * A builder to create and update a SessionPersistence
 * @author liujunpeng
 *
 */
public interface SessionPersistenceBuilder extends Builder<SessionPersistenceBuilder, SessionPersistence>{

	/**
	 * required
	 * 
	 * @param type
	 *            APP_COOKIE,HTTP_COOKIE,SOURCE_IP
	 * @return SessionPersistenceBuilder
	 */
	public SessionPersistenceBuilder type(String type);

	/**
	 * optional
	 * 
	 * @param cookieName
	 * @return SessionPersistenceBuilder
	 */
	public SessionPersistenceBuilder cookieName(String cookieName);
}
