package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.NatPool;

/**
 * This interface defines all methods for the manipulation of nat-pool
 * 
 * @author vinod borole
 * 
 */
public interface NatPoolService {
    /**
     * List all nat pool
     * 
     * @return List of nat pool
     */
    List<? extends NatPool> list();
    List<? extends NatPool> list(Map<String, String> filteringParams);
    NatPool get(String id);
    ActionResponse delete(String id);
    NatPool create(NatPool natpool);
    NatPool update(NatPool natpool);
}
