package org.openstack4j.model.bareMetal;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.bareMetal.builder.ChassisUpdateBuilder;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.List;

/**
 * The model to update a chassis
 * @author zhangliang
 */
public interface ChassisUpdate extends ModelEntity, Buildable<ChassisUpdateBuilder> {

    /**
     * return the chassis update operations
     * @return
     */
    List<PatchOperation> getOps();

}
