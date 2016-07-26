package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.MemberV2Service;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.MemberV2;
import org.openstack4j.model.network.ext.MemberV2Update;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

/**
 * Openstack (Neutron) lbaas V2 member operations
 * @author emjburns
 */
public class MemberV2ServiceImpl extends BaseNetworkingServices implements MemberV2Service{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MemberV2> list(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MemberV2> list(Map<String, String> filteringParams){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberV2 get(String memberId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String memberId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberV2 create(MemberV2 member){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberV2 update(String memberId, MemberV2Update member){
        return null;
    }
}
