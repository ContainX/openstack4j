package org.openstack4j.openstack.octavia.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.octavia.L7PolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.L7Policy;
import org.openstack4j.model.octavia.L7PolicyUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.octavia.domain.OctaviaL7Policy;

public class L7PolicyServiceImpl extends BaseOctaviaServices implements L7PolicyService {
    @Override
	public List<? extends L7Policy> list()
    {
    	 return get(OctaviaL7Policy.L7Policies.class, uri("/lbaas/l7policies")).execute().getList();
    }

    @Override
    public L7Policy get(String l7PolicyId)
    {
		 checkNotNull(l7PolicyId);
	     return get(OctaviaL7Policy.class, uri("/lbaas/l7policies/%s",l7PolicyId)).execute();
    }
    
    @Override
    public ActionResponse delete(String l7PolicyId)
    {
    	 checkNotNull(l7PolicyId);
         return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/lbaas/l7policies/%s",l7PolicyId)).executeWithResponse());
    }
    
    @Override
    public L7Policy create(L7Policy l7Policy)
    {
    	 checkNotNull(l7Policy);
         return post(OctaviaL7Policy.class,uri("/lbaas/l7policies")).entity(l7Policy).execute();
    }
    
    @Override
    public L7Policy update(String l7PolicyId, L7PolicyUpdate l7PolicyUpdate)
    {
    	 checkNotNull(l7PolicyId);
         checkNotNull(l7PolicyUpdate);
         return put(OctaviaL7Policy.class, uri("/lbaas/l7policies/%s",l7PolicyId)).entity(l7PolicyUpdate).execute();
    }
}
