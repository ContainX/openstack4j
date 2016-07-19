package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.MemberV2;

import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron Lbaas) V2 Member Extention API
 * @author emjburns
 */
public interface MemberV2Service extends RestService {
    /**
     * List all members  that the current tenant has access to
     *
     * @return list of all Member
     */
    List<? extends MemberV2> list();

    /**
     * Returns list of member filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return
     */
    List<? extends MemberV2> list(Map<String, String> filteringParams);

    /**
     * Get the specified member by ID
     *
     * @param memberId the member identifier
     * @return the member or null if not found
     */
    MemberV2 get(String memberId);

    /**
     * Delete the specified member by ID
     * @param memberId the member identifier
     * @return the action response
     */
    ActionResponse delete(String memberId);
    /**
     * Create a member
     * @param member Member
     * @return Member
     */
    MemberV2 create(MemberV2 member);


    /**
     * Update a member
     * @param memberId the member identifier
     * @param member MemberUpdate
     * @return Member
     */
//    TODO: Impl of update
//    MemberV2 update(String memberId,MemberV2Update member);
}
