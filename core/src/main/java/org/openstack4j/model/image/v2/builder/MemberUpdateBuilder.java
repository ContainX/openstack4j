package org.openstack4j.model.image.v2.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.MemberUpdate;

/**
 * Builder which creates an object to update a Glance V2 image member
 * @author emjburns
 */
public interface MemberUpdateBuilder extends Buildable.Builder<MemberUpdateBuilder, MemberUpdate> {
    /**
     * @see MemberUpdate#getStatus()
     */
    MemberUpdateBuilder status(Member.MemberStatus status);
}
