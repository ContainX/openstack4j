package org.openstack4j.openstack.sahara.internal;

import java.util.List;

import org.openstack4j.api.sahara.ClusterService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.openstack.sahara.domain.SaharaCluster;
import org.openstack4j.openstack.sahara.domain.SaharaCluster.Clusters;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sahara Data Processing Operations
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public class ClusterServiceImpl extends BaseSaharaServices implements ClusterService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Cluster> list() {
        return get(Clusters.class, uri("/clusters")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cluster get(String clusterId) {
        checkNotNull(clusterId);
        return get(SaharaCluster.class, uri("/clusters/%s", clusterId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cluster create(Cluster cluster) {
        checkNotNull(cluster);
        return post(SaharaCluster.class, uri("/clusters"))
                     .entity(cluster)  // setup request
                     .execute();
       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String clusterId) {
        checkNotNull(clusterId);
        return deleteWithResponse(uri("/clusters/%s", clusterId)).execute();
    }

}
