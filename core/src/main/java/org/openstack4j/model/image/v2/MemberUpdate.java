package org.openstack4j.model.image.v2;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.image.v2.builder.MemberUpdateBuilder;

public interface MemberUpdate extends ModelEntity, Buildable<MemberUpdateBuilder> {
    /**
     * The status of the member, indicating whether they
     * accept or reject membership
     * @return status of member
     */
    Member.MemberStatus getStatus();
}
