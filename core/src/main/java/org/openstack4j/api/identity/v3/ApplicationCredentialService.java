package org.openstack4j.api.identity.v3;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.ApplicationCredential;

import java.util.List;

public interface ApplicationCredentialService extends RestService {

    ApplicationCredential create(String userId, ApplicationCredential applicationCredential);

    ApplicationCredential get(String userId, String applicationCredentialId);

    List<? extends ApplicationCredential> list(String userId);

    ActionResponse delete(String userId, String applicationCredentialId);
}
