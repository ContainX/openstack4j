package org.openstack4j.model.image.v2;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.image.v2.builder.MemberCreateBuilder;

/**
 * An object for creating a Glance V2 Member
 * @author emjburns
 */
public interface MemberCreate extends ModelEntity, Buildable<MemberCreateBuilder> {
    /**
     * An identifier for the image member (tenantId or projectId)
     * @return memberId
     */
    String getMember();
}
