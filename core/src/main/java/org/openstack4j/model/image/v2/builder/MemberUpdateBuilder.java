package org.openstack4j.model.image.v2.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.MemberUpdate;

public interface MemberUpdateBuilder extends Buildable.Builder<MemberUpdateBuilder, MemberUpdate> {
    /**
     * @see MemberUpdate#getStatus()
     */
    MemberUpdateBuilder status(Member.MemberStatus status);
}
