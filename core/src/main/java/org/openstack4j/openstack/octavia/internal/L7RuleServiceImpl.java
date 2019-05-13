package org.openstack4j.openstack.octavia.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.octavia.L7RuleService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.L7Rule;
import org.openstack4j.model.octavia.L7RuleUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.octavia.domain.OctaviaL7Rule;

public class L7RuleServiceImpl extends BaseOctaviaServices implements L7RuleService {
	
	@Override
	public List<? extends L7Rule> list(String l7PolicyId)
	{
		 checkNotNull(l7PolicyId);
		 return get(OctaviaL7Rule.L7Rules.class,uri("/lbaas/l7policies/%s/rules",l7PolicyId)).execute().getList();
	}
	
    @Override
    public L7Rule get(String l7PolicyId,String l7RuleId)
    {
		 checkNotNull(l7PolicyId);
		 checkNotNull(l7RuleId);
	     return get(OctaviaL7Rule.class, uri("/lbaas/l7policies/%s/rules/%s",l7PolicyId,l7RuleId)).execute();
    }
    
    @Override
    public ActionResponse delete(String l7PolicyId,String l7RuleId)
    {
    	 checkNotNull(l7PolicyId);
		 checkNotNull(l7RuleId);
         return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/lbaas/l7policies/%s/rules/%s",l7PolicyId,l7RuleId)).executeWithResponse());
    }
    
    @Override
    public L7Rule create(String l7PolicyId,L7Rule l7Rule)
    {
    	 checkNotNull(l7Rule);
    	 checkNotNull(l7PolicyId);
         return post(OctaviaL7Rule.class,uri("/lbaas/l7policies/%s/rules",l7PolicyId)).entity(l7Rule).execute();
    }
    
    @Override
    public L7Rule update(String l7PolicyId,String l7RuleId,L7RuleUpdate l7RuleUpdate)
    {
    	 checkNotNull(l7PolicyId);
    	 checkNotNull(l7RuleId);
         checkNotNull(l7RuleUpdate);
         return put(OctaviaL7Rule.class, uri("/lbaas/l7policies/%s/rules/%s",l7PolicyId,l7RuleId)).entity(l7RuleUpdate).execute();
    }
    
	
}
