package org.openstack4j.model.identity.v3;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.identity.v3.builder.ApplicationCredentialBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ApplicationCredential model.
 *
 * @see <a href="https://developer.openstack.org/api-ref/identity/v3/index.html#application-credentials">API reference</a>
 */
public interface ApplicationCredential extends ModelEntity, Buildable<ApplicationCredentialBuilder> {

    String getId();

    String getSecret();

    String getProjectId();

    String getName();

    String getDescription();

    List<? extends Role> getRoles();

    Map<String, String> getLinks();

    Date getExpiresAt();

    Boolean getUnrestricted();
}
