package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.PortUpdate;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.List;

/**
 * A Builder which creates a PortUpdate
 *
 * @author zhangliang
 */
public interface PortUpdateBuilder extends Buildable.Builder<PortUpdateBuilder, PortUpdate> {

    /**
     * set operation list
     * @param ops
     * @return
     */
    PortUpdateBuilder ops(List<PatchOperation> ops);

    /**
     * add a operation to list
     * @param op
     * @return
     */
    PortUpdateBuilder ops(PatchOperation op);

}
