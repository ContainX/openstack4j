package org.openstack4j.model.image.v2.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.MemberCreate;

public interface MemberCreateBuilder extends Buildable.Builder<MemberCreateBuilder, MemberCreate> {
    /**
     * @see MemberCreate#getMember()
     */
    MemberCreateBuilder member(String member);
}
