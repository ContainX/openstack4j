package org.openstack4j.openstack.internal;

import static org.openstack4j.core.transport.HttpExceptionHandler.mapException;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.HttpEntityHandler;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.openstack.identity.domain.Auth;
import org.openstack4j.openstack.identity.domain.Auth.Type;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.RaxApiKeyCredentials;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.identity.domain.TokenAuth;
import org.openstack4j.openstack.identity.domain.v3.AccessWrapper;
import org.openstack4j.openstack.identity.domain.v3.KeystoneAuth;
import org.openstack4j.openstack.identity.domain.v3.KeystoneTokenV3;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

/**
 * Responsible for authenticating and re-authenticating sessions for V2 and V3 of the Identity API
 * 
 * @author Jeremy Unruh
 */
public class OSAuthenticator {

    private static final String TOKEN_INDICATOR = "Tokens";
    private static final Logger LOG = LoggerFactory.getLogger(OSAuthenticator.class);
    /**
     * Invokes authentication to obtain a valid Token for either V2 and V3
     * 
     * @param auth the authentication credentials
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    public static OSClient invoke(AuthStore auth, String endpoint, Facing perspective, Config config) {
        if (auth.getVersion() == AuthVersion.V2)
            return authenticateV2((Auth) auth.unwrap(), endpoint, perspective, false, config);

        return authenticateV3((KeystoneAuth) auth.unwrap(), endpoint, perspective, config);
    }

    /**
     * Invokes V2 authentication via an existing token
     * @param auth the token authentication
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    public static OSClient invoke(TokenAuth auth, String endpoint, Facing perspective, Config config) {
        return authenticateV2(auth, endpoint, perspective, false, config);
    }
    
    /**
     * Re-authenticates/renews the token for the current Session
     */
    public static void reAuthenticate() {
        
        LOG.debug("Re-Authenticating session due to expired Token or invalid response");
        
        OSClientSession session = OSClientSession.getCurrent();
        switch (session.getAccess().getVersion()) {
            case V3:
                KeystoneTokenV3 token = session.getAccess().unwrap();
                authenticateV3(token.getCredentials(), token.getEndpoint(), session.getPerspective(), session.getConfig());
                break;
            case V2:
            default:
                KeystoneAccess access = session.getAccess().unwrap();
                Auth auth = (Auth) ((access.isCredentialType()) ? access.getCredentials().unwrap() : access.getTokenAuth());
                authenticateV2(auth, access.getEndpoint(), session.getPerspective(), true, session.getConfig());
                break;
        }
    }
    
    private static OSClient authenticateV2(Auth auth, String endpoint, Facing perspective, boolean reLinkToExistingSession, Config config) {
        HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR)
                .endpoint(endpoint)
                .method(HttpMethod.POST)
                .path("/tokens")
                .config(config)
                .entity(auth)
                .build();
        
        HttpResponse response = HttpExecutor.create().execute(request);
        if (response.getStatus() >= 400) {
            try {
                throw mapException(response.getStatusMessage(), response.getStatus());
            }
            finally {
                HttpEntityHandler.closeQuietly(response);
            }
        }
        
        KeystoneAccess access = response.getEntity(KeystoneAccess.class);
        
        if (auth.getType() == Type.CREDENTIALS) {
            access = access.applyContext(endpoint, (Credentials) auth);
        }
        else if (auth.getType() == Type.RAX_APIKEY) {
            access = access.applyContext(endpoint, (RaxApiKeyCredentials) auth);
        }
        else {
            access = access.applyContext(endpoint, (TokenAuth) auth);
        }
        
        if (!reLinkToExistingSession)
            return OSClientSession.createSession(access, perspective, config);
        
        OSClientSession current = OSClientSession.getCurrent();
        current.access = access;
        return current;
    }

    private static OSClient authenticateV3(KeystoneAuth credentials, String endpoint, Facing perspective, Config config) {
        HttpRequest<KeystoneTokenV3> request = HttpRequest.builder(KeystoneTokenV3.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR)
                .endpoint(endpoint).method(HttpMethod.POST)
                .path("/auth/tokens")
                .config(config)
                .entity(credentials)
                .build();
        
        HttpResponse response = HttpExecutor.create().execute(request);
        
        if (response.getStatus() >= 400) {
            try
            {
                throw mapException(response.getStatusMessage(), response.getStatus());
            }
            finally {
                HttpEntityHandler.closeQuietly(response);
            }
        }
        
        KeystoneTokenV3 access = response.getEntity(KeystoneTokenV3.class);
        access.id = response.header(ClientConstants.HEADER_X_SUBJECT_TOKEN);
        return OSClientSession.createSession(AccessWrapper.wrap(access.applyContext(endpoint, credentials)), perspective, config);
    }
}
