package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.LbPoolV2Service;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * OpenStack (Neutron) lbaas v2 lb pool operations
 * @author emjburns
 */
public class LbPoolV2ServiceImpl extends BaseNetworkingServices implements LbPoolV2Service{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LbPoolV2> list(){
        return get(NeutronLbPoolV2.LbPoolsV2.class, uri("/lbaas/pools")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LbPoolV2> list(Map<String, String> filteringParams){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2 get(String lbPoolId){
        checkNotNull(lbPoolId);
        return get(NeutronLbPoolV2.class, uri("/lbaas/pools/%s",lbPoolId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String lbPoolId){
        checkNotNull(lbPoolId);
        return ToActionResponseFunction.INSTANCE.apply(delete(void.class, uri("/lbaas/pools/%s",lbPoolId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2 create(LbPoolV2 lbPool){
        checkNotNull(lbPool);
        return post(NeutronLbPoolV2.class, uri("/lbaas/pools")).entity(lbPool).execute();
    }
}
