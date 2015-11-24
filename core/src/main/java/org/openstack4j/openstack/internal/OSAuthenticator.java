package org.openstack4j.openstack.internal;

import static org.openstack4j.core.transport.HttpExceptionHandler.mapException;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.client.CloudProvider;
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
import org.openstack4j.model.identity.Token;
import org.openstack4j.openstack.common.Auth.Type;
import org.openstack4j.openstack.identity.domain.KeystoneAuth;
import org.openstack4j.openstack.identity.domain.KeystoneToken;
import org.openstack4j.openstack.identity.domain.TokenAuth;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

/**
 * Responsible for authenticating and re-authenticating sessions Identity API V3
 */
public class OSAuthenticator {

    private static final String TOKEN_INDICATOR = "Tokens";
    private static final Logger LOG = LoggerFactory.getLogger(OSAuthenticator.class);
    /**
     * Invokes authentication to obtain a valid V3 Token, throws an UnsupportedOperationException for an V2 attempt.
     *
     * @param auth the authentication credentials
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    public static OSClient invoke(AuthStore auth, String endpoint, Facing perspective, Config config, CloudProvider provider) {
    	SessionInfo info = new SessionInfo(endpoint, perspective, false, provider);
        if (auth.getVersion() == AuthVersion.V2){
            throw new UnsupportedOperationException("V2 authentication is no longer supported in this version of OpenStack4j.");
        }
        return authenticate((KeystoneAuth) auth.unwrap(), info, config);
    }

    /**
     * Invokes V3 authentication via an existing token
     * @param auth the token authentication
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    public static OSClient invoke(KeystoneAuth auth, String endpoint, Facing perspective, Config config, CloudProvider provider) {
    	SessionInfo info = new SessionInfo(endpoint, perspective, false, provider);
        return authenticate(auth, info, config);
    }


    /**
     * Re-authenticates/renews the token for the current Session
     */
    public static void reAuthenticate() {

        LOG.debug("Re-Authenticating session due to expired Token or invalid response");

        OSClientSession session = OSClientSession.getCurrent();

        Token token = session.getToken();
        SessionInfo info = new SessionInfo(token.getEndpoint(), session.getPerspective(), true, session.getProvider());
        authenticate( (KeystoneAuth) token.getCredentials(), info, session.getConfig());

    }

    private static OSClient authenticate(KeystoneAuth auth, SessionInfo info, Config config) {
        HttpRequest<KeystoneToken> request = HttpRequest.builder(KeystoneToken.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR)
                .endpoint(info.endpoint)
                .method(HttpMethod.POST)
                .path("/auth/tokens")
                .config(config)
                .entity(auth)
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
        KeystoneToken token = response.getEntity(KeystoneToken.class);
        token.setId(response.header(ClientConstants.HEADER_X_SUBJECT_TOKEN));

        if (auth.getType() == Type.CREDENTIALS) {
            token = token.applyContext(info.endpoint, new org.openstack4j.openstack.identity.domain.Credentials(auth.getUsername(), auth.getPassword()));
        }
        else {
        	token = token.applyContext(	info.endpoint, new TokenAuth(token.getId(), auth.getScope().getProject().getName(), auth.getScope().getProject().getId()));

        }

        if (!info.reLinkToExistingSession)
            return OSClientSession.createSession(token, info.perspective, info.provider, config);

        OSClientSession current = OSClientSession.getCurrent();
        current.token = token;
        return current;

    }

    private static class SessionInfo {
        String endpoint;
        Facing perspective;
        boolean reLinkToExistingSession;
        CloudProvider provider;

        SessionInfo(String endpoint, Facing perspective, boolean reLinkToExistingSession, CloudProvider provider) {
            this.endpoint = endpoint;
            this.perspective = perspective;
            this.reLinkToExistingSession = reLinkToExistingSession;
            this.provider = provider;
        }

    }

}