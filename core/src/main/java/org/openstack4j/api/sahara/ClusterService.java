package org.openstack4j.api.sahara;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.model.sahara.ClusterCreate;

/**
 * Sahara Data Processing Operations
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterService extends RestService {

    /**
     * Lists all clusters
     * 
     * @return list of images or empty
     */
    List<? extends Cluster> list();

    /**
     * Creates a new cluster
     * 
     * @param cluster the cluster to create
     * @return the created cluster
     */
    Cluster create(ClusterCreate cluster);

}
