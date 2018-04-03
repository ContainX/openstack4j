package org.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.*;

import org.openstack4j.api.identity.v3.ApplicationCredentialService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.ApplicationCredential;
import org.openstack4j.openstack.identity.v3.domain.KeystoneApplicationCredential;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.List;

public class ApplicationCredentialServiceImpl extends BaseOpenStackService implements ApplicationCredentialService {

    @Override
    public ApplicationCredential create(String userId, ApplicationCredential applicationCredential) {
        checkNotNull(applicationCredential);
        return post(KeystoneApplicationCredential.class, PATH_USERS, "/", userId, PATH_APPLICATION_CREDENTIALS)
                .entity(applicationCredential).execute();
    }

    @Override
    public ApplicationCredential get(String userId, String applicationCredentialId) {
        checkNotNull(userId);
        checkNotNull(applicationCredentialId);
        return get(KeystoneApplicationCredential.class, PATH_USERS, "/", userId, PATH_APPLICATION_CREDENTIALS, "/", applicationCredentialId)
                .execute();
    }

    @Override
    public List<? extends ApplicationCredential> list(String userId) {
        checkNotNull(userId);
        return get(KeystoneApplicationCredential.ApplicationCredentials.class, PATH_USERS, PATH_APPLICATION_CREDENTIALS)
                .execute().getList();
    }

    @Override
    public ActionResponse delete(String userId, String applicationCredentialId) {
        checkNotNull(userId);
        checkNotNull(applicationCredentialId);
        return deleteWithResponse(PATH_USERS, "/", userId, PATH_APPLICATION_CREDENTIALS, "/", applicationCredentialId)
                .execute();
    }
}
