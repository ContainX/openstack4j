package org.openstack4j.api.sahara;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.sahara.Cluster;

/**
 * Sahara Data Processing Operations
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterService extends RestService {

    /**
     * List all clusters
     * 
     * @return list of images or empty
     */
     List<? extends Cluster> list();

    /**
     * Get a cluster by ID
     * @param clusterId the cluster identifier
     * @return the cluster or null if not found
     */
     Cluster get(String clusterId);


    /**
     * Create a new cluster
     * 
     * @param cluster the cluster to create
     * @return the created cluster
     */
     Cluster create(Cluster cluster);

    /**
     * Delete the specified cluster 
     * 
     * @param clusterId the cluster identifier
     * @return the action response
     */
     ActionResponse delete(String clusterId);

}
