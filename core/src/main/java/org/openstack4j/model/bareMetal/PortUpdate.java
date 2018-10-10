package org.openstack4j.model.bareMetal;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.bareMetal.builder.PortUpdateBuilder;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.List;

/**
 * the model for update bare metal
 *
 * @author zhangliang
 */
public interface PortUpdate extends ModelEntity, Buildable<PortUpdateBuilder> {

    /**
     * return the port update operation
     * @return
     */
    List<PatchOperation> getOps();

}
