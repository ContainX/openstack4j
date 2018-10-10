package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.NodeUpdate;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.List;

/**
 * A Builder which creates a NodeUpdate
 *
 * @author zhangliang
 */
public interface NodeUpdateBuilder extends Buildable.Builder<NodeUpdateBuilder, NodeUpdate> {

    /**
     * set operation list
     * @param ops
     * @return
     */
    NodeUpdateBuilder ops(List<PatchOperation> ops);

    /**
     * add a operation to list
     * @param op
     * @return
     */
    NodeUpdateBuilder ops(PatchOperation op);

}
