package org.openstack4j.api.octavia;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.L7Rule;
import org.openstack4j.model.octavia.L7RuleUpdate;

public interface L7RuleService extends RestService {
    List<? extends L7Rule> list(String l7PolicyId);

    L7Rule get(String l7PolicyId,String l7RuleId);

    ActionResponse delete(String l7PolicyId,String l7RuleId);
    
    L7Rule create(String l7PolicyId,L7Rule l7Rule);
    
    L7Rule update(String l7PolicyId,String l7RuleId,L7RuleUpdate l7RuleUpdate);

}
