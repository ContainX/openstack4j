package org.openstack4j.model.image.v2.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.PatchOperation;
import org.openstack4j.openstack.image.v2.GlancePatchOperation;


public interface PatchOperationBuilder extends Buildable.Builder<PatchOperationBuilder, PatchOperation> {
    /**
     * @see PatchOperation#getOp()
     */
    PatchOperationBuilder operationType(GlancePatchOperation.OperationType operationType);

    /**
     * @see PatchOperation#getPath()
     */
    PatchOperationBuilder path(String path);

    /**
     * @see PatchOperation#getValue()
     */
    PatchOperationBuilder value(Object value);

}
