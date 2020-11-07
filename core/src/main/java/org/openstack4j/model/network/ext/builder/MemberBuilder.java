package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.Member;

/**
 * A Builder to create a member
 *
 * @author liujunpeng
 */
public interface MemberBuilder extends Builder<MemberBuilder, Member> {

    /**
     * @param tenantId Owner of the member. Only an administrative user can specify a
     *                 tenant ID other than its own.
     * @return MemberBuilder
     */
    MemberBuilder tenantId(String tenantId);

    /**
     * @param adminStateUp The administrative state of the member, which is up (true) or
     *                     down (false).
     * @return MemberBuilder
     */
    MemberBuilder adminStateUp(boolean adminStateUp);

    /**
     * @param address The IP address of the member.
     * @return MemberBuilder
     */
    MemberBuilder address(String address);

    /**
     * @param protocolPort . The port on which the application is hosted..A valid value
     *                     is from 1 to 65535
     * @return MemberBuilder
     */
    MemberBuilder protocolPort(Integer protocolPort);


    /**
     * @param weight Weight of member.from 1 to 256
     * @return MemberBuilder
     */
    MemberBuilder weight(Integer weight);

    /**
     * @param lbPoolId the lb pool identifier
     * @return MemberBuilder
     */
    MemberBuilder poolId(String lbPoolId);
}
