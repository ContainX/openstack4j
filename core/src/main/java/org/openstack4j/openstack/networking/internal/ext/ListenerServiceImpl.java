package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.ListenerService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Listener;

import java.util.List;
import java.util.Map;

/**
 * Lbaas listener imlementation
 */
public class ListenerServiceImpl implements ListenerService{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Listener> list(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Listener> list(Map<String, String> filteringParams){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener get(String listenerId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String listenerId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener create(Listener listener){
        return null;
    }
}
