package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.ChassisUpdate;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.List;

/**
 * A Builder which creates a ChassisUpdate
 *
 * @author zhangliang
 */
public interface ChassisUpdateBuilder extends Buildable.Builder<ChassisUpdateBuilder, ChassisUpdate> {

    /**
     * set operation list
     * @param ops
     * @return
     */
    ChassisUpdateBuilder ops(List<PatchOperation> ops);

    /**
     * add a operation to list
     * @param op
     * @return
     */
    ChassisUpdateBuilder ops(PatchOperation op);

}
