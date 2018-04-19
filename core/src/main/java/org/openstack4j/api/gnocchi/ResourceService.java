package org.openstack4j.api.gnocchi;

import org.openstack4j.model.gnocchi.Resource;

import java.util.Date;
import java.util.List;

/**
 * resource service
 *
 * @author zhangliang
 */
public interface ResourceService {

    /**
     * return resource metric
     * @param resourceType
     * @param resourceId
     * @param metricType
     * @param start
     * @return
     */
    Object[][] listMetric(String resourceType, String resourceId, String metricType, Date start);

    /**
     * return resource list
     * @param resourceType
     * @param json
     * @return
     */
    List<? extends Resource> listResource(String resourceType, String json);

}
