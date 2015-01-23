package org.openstack4j.openstack.sahara.internal;

import java.util.List;

import org.openstack4j.api.sahara.ClusterService;
import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.model.sahara.ClusterCreate;
import org.openstack4j.openstack.sahara.domain.SaharaCluster;
import org.openstack4j.openstack.sahara.domain.SaharaCluster.Clusters;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sahara Data Processing Operations
 * 
 * @author Ekasit Kijsipongse
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
    public Cluster create(ClusterCreate cluster) {
        checkNotNull(cluster);
        return post(SaharaCluster.class, uri("/clusters"))
                     .entity(cluster)  // setup request
                     .execute();
       
    }

}
