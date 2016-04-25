package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.ExternalPolicy;

/**
 * This interface defines all methods for the manipulation of external policies
 * 
 * @author vinod borole
 * 
 */
public interface ExternalPolicyService extends RestService{

    List<? extends ExternalPolicy> list();
    List<? extends ExternalPolicy> list(Map<String, String> filteringParams);
    ExternalPolicy get(String id);
    ActionResponse delete(String id);
    ExternalPolicy create(ExternalPolicy externalPolicy);
    ExternalPolicy update(String externalPolicyId,ExternalPolicy externalPolicy);
}
