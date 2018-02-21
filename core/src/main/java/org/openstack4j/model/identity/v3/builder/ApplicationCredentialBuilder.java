package org.openstack4j.model.identity.v3.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.identity.v3.ApplicationCredential;
import org.openstack4j.model.identity.v3.Credential;
import org.openstack4j.model.identity.v3.Role;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ApplicationCredentialBuilder extends Buildable.Builder<ApplicationCredentialBuilder, ApplicationCredential> {

    ApplicationCredentialBuilder id(String id);

    ApplicationCredentialBuilder secret(String secret);

    ApplicationCredentialBuilder name(String name);

    ApplicationCredentialBuilder description(String description);

    ApplicationCredentialBuilder projectId(String projectId);

    ApplicationCredentialBuilder roles(List<? extends Role> roles);

    ApplicationCredentialBuilder unrestricted(Boolean unrestricted);

    ApplicationCredentialBuilder expiresAt(Date expiresAt);

    ApplicationCredentialBuilder links(Map<String, String> links);
}
