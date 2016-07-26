package org.openstack4j.openstack.networking.domain.ext;

import org.openstack4j.model.network.ext.MemberV2Update;
import org.openstack4j.model.network.ext.builder.MemberV2UpdateBuilder;

/**
 * Entity for updating lbaas v2 members
 * @author emjburns
 */
public class NeutronMemberV2Update implements MemberV2Update{

    //TODO: implementation

    @Override
    public boolean isAdminStateUp(){
        return false;
    }

    @Override
    public Integer getWeight(){
        return null;
    }

    @Override
    public MemberV2UpdateBuilder toBuilder(){
        return null;
    }

    public static MemberV2UpdateBuilder builder(){
        return null;
    }
}
