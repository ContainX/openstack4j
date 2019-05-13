package org.openstack4j.api.octavia;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.L7Policy;
import org.openstack4j.model.octavia.L7PolicyUpdate;

public interface L7PolicyService extends RestService {
    List<? extends L7Policy> list();

    L7Policy get(String l7PolicyId);

    ActionResponse delete(String l7PolicyId);
    
    L7Policy create(L7Policy l7Policy);
    
    L7Policy update(String l7PolicyId, L7PolicyUpdate l7PolicyUpdate);

}
