package org.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_TOKENS;

import org.openstack4j.api.identity.v3.TokenService;

import static org.openstack4j.core.transport.ClientConstants.HEADER_X_SUBJECT_TOKEN;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.openstack.identity.v3.domain.KeystoneToken;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class TokenServiceImpl extends BaseOpenStackService implements TokenService {

    @Override
    public Token get(String tokenId) {
        checkNotNull(tokenId);
        return get(KeystoneToken.class, PATH_TOKENS).header(HEADER_X_SUBJECT_TOKEN, tokenId).execute();
    }

    @Override
    public ActionResponse check(String tokenId) {
        checkNotNull(tokenId);
        return head(ActionResponse.class, PATH_TOKENS).header(HEADER_X_SUBJECT_TOKEN, tokenId).execute();
    }

    @Override
    public ActionResponse delete(String tokenId) {
        checkNotNull(tokenId);
        return deleteWithResponse(PATH_TOKENS).header(HEADER_X_SUBJECT_TOKEN, tokenId).execute();
    }

}
