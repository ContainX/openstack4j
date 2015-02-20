package org.openstack4j.openstack.sahara.internal;

import java.util.List;

import org.openstack4j.api.sahara.ClusterService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.model.sahara.NodeGroup;
import org.openstack4j.openstack.sahara.domain.actions.SaharaActions.ResizeNodeGroupAction;
import org.openstack4j.openstack.sahara.domain.actions.SaharaActions.AddNodeGroupAction;
import org.openstack4j.openstack.sahara.domain.SaharaCluster;
import org.openstack4j.openstack.sahara.domain.SaharaClusterUnwrapped;
import org.openstack4j.openstack.sahara.domain.SaharaCluster.Clusters;

import java.io.File;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.payloads.FilePayload;

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
        SaharaClusterUnwrapped unwrapped = new SaharaClusterUnwrapped(cluster);
        return post(SaharaCluster.class, uri("/clusters"))
                     .entity(unwrapped)  // setup request
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse resizeNodeGroup(String clusterId, String groupName, int count) {
        checkNotNull(clusterId);
        checkNotNull(groupName);
        return put(ActionResponse.class, uri("/clusters/%s", clusterId)).entity(new ResizeNodeGroupAction(groupName,count)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addNodeGroup(String clusterId, NodeGroup nodeGroup) {
        checkNotNull(clusterId);
        checkNotNull(nodeGroup);
        return put(ActionResponse.class, uri("/clusters/%s", clusterId)).entity(new AddNodeGroupAction(nodeGroup)).execute();
    }

}
