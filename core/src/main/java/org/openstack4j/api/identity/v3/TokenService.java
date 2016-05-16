package org.openstack4j.api.identity.v3;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Token;

/**
 * Identity V3 Token operations
 *
 */
public interface TokenService extends RestService {
    
    /***
     * Validates and shows information for a token.
     * 
     * @param tokenId the identifier of the token that is subject to be checked
     * @return the token if valid
     */
    Token get(String tokenId);
    
    /**
     * Validates a token.
     * 
     * @param tokenId the identifier of the token that is subject to be checked
     * @return the ActionResponse
     */
    ActionResponse check(String tokenId);
    
    /**
     * Revokes a token.
     * 
     * @param tokenId the identifier of the token that is going to be deleted
     * @return the ActionResponse
     */
    ActionResponse delete(String tokenId);    
}
