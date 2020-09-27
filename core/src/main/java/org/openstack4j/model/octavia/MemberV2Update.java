package org.openstack4j.model.octavia;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.octavia.builder.MemberV2UpdateBuilder;

/**
 * An entity used to update a member of an lbaas v2 pool
 *
 * @author wei
 */
public interface MemberV2Update extends ModelEntity, Buildable<MemberV2UpdateBuilder> {
    /**
     * @see MemberV2#isAdminStateUp()
     */
    public boolean isAdminStateUp();

    /**
     * @see MemberV2#getWeight()
     */
    public Integer getWeight();
}
