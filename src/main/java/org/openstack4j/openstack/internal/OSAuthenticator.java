package org.openstack4j.openstack.internal;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.identity.domain.v3.AccessWrapper;
import org.openstack4j.openstack.identity.domain.v3.KeystoneAuth;
import org.openstack4j.openstack.identity.domain.v3.KeystoneTokenV3;

/**
 * Responsible for authenticating and re-authenticating sessions for V2 and V3 of the Identity API
 * 
 * @author Jeremy Unruh
 *
 */
public class OSAuthenticator {

    private static final String TOKEN_INDICATOR = "Tokens";
    /**
     * Invokes authentication to obtain a valid Token for either V2 and V3
     * 
     * @param auth the authentication credentials
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param useNonStrictSSL true to bypass non-authorative signed certificate checks
     * @return the OSClient
     */
    public static OSClient invoke(AuthStore auth, String endpoint, Facing perspective, boolean useNonStrictSSL) {
        if (auth.getVersion() == AuthVersion.V2)
            return authenticateV2((Credentials) auth.unwrap(), endpoint, perspective, useNonStrictSSL);

        return authenticateV3((KeystoneAuth) auth.unwrap(), endpoint, perspective, useNonStrictSSL);
    }

    /**
     * Re-authenticates/renews the token for the current Session
     */
    public static void reAuthenticate() {
        OSClientSession session = OSClientSession.getCurrent();
        switch (session.getAccess().getVersion()) {
            case V3:
                KeystoneTokenV3 token = session.getAccess().unwrap();
                authenticateV3(token.getCredentials(), token.getEndpoint(), session.getPerspective(), session.useNonStrictSSLClient());
                break;
            case V2:
            default:
                KeystoneAccess access = session.getAccess().unwrap();
                authenticateV2((Credentials) access.getCredentials().unwrap(), access.getEndpoint(), session.getPerspective(), session.useNonStrictSSLClient());
                break;
        }
    }
    
    private static OSClient authenticateV2(Credentials credentials, String endpoint, Facing perspective, boolean useNonStrictSSL) {
        HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR)
                .endpoint(endpoint)
                .method(HttpMethod.POST)
                .path("/tokens")
                .entity(credentials)
                .useNonStrictSSL(useNonStrictSSL)
                .build();
        
        KeystoneAccess access = HttpExecutor.create().execute(request).getEntity(KeystoneAccess.class);
        return OSClientSession.createSession(access.applyContext(endpoint, credentials), perspective, useNonStrictSSL);
    }

    private static OSClient authenticateV3(KeystoneAuth credentials, String endpoint, Facing perspective, boolean useNonStrictSSL) {
        HttpRequest<KeystoneTokenV3> request = HttpRequest.builder(KeystoneTokenV3.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR)
                .endpoint(endpoint).method(HttpMethod.POST)
                .path("/auth/tokens")
                .entity(credentials)
                .useNonStrictSSL(useNonStrictSSL)
                .build();
        
        HttpResponse response = HttpExecutor.create().execute(request);
        KeystoneTokenV3 access = response.getEntity(KeystoneTokenV3.class);
        access.id = response.header(ClientConstants.HEADER_X_SUBJECT_TOKEN);
        return OSClientSession.createSession(AccessWrapper.wrap(access.applyContext(endpoint, credentials)), perspective, useNonStrictSSL);
    }
}
