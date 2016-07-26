package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.ListenerService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.ListenerUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Lbaas listener imlementation
 * @author emjburns
 */
public class ListenerServiceImpl extends BaseNetworkingServices implements ListenerService{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Listener> list(){
        return get(NeutronListenerV2.Listeners.class, uri("/lbaas/listeners")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Listener> list(Map<String, String> filteringParams){
        Invocation<NeutronListenerV2.Listeners> req = get(NeutronListenerV2.Listeners.class, uri("/lb/listeners"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener get(String listenerId){
        checkNotNull(listenerId);
        return get(NeutronListenerV2.class, uri("/lbaas/listeners/%s",listenerId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String listenerId){
        checkNotNull(listenerId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/lbaas/listeners/%s",listenerId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener create(Listener listener){
        checkNotNull(listener);
        return post(NeutronListenerV2.class, uri("lbaas/listeners")).entity(listener).execute();

    }

    @Override
    public Listener update(String listenerId, ListenerUpdate listener){
        checkNotNull(listenerId);
        checkNotNull(listener);
        return put(NeutronListenerV2.class, uri("lbaas/listeners/%s",listenerId)).entity(listener).execute();
    }
}
