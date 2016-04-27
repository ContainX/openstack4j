package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.ExternalSegment;

/**
 * This interface defines all methods for the manipulation of external segments
 * 
 * @author vinod borole
 * 
 */
public interface ExternalSegmentService {
    /**
     * List all external segment
     * 
     * @return List of external segment
     */
    List<? extends ExternalSegment> list();
    List<? extends ExternalSegment> list(Map<String, String> filteringParams);
    ExternalSegment get(String id);
    ActionResponse delete(String id);
    ExternalSegment create(ExternalSegment externalSegment);
    ExternalSegment update(ExternalSegment externalSegment);
}
