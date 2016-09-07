package org.openstack4j.model.image.v2.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.image.v2.ImageUpdate;
import org.openstack4j.model.image.v2.PatchOperation;

import java.util.List;

public interface ImageUpdateBuilder extends Buildable.Builder<ImageUpdateBuilder, ImageUpdate> {
    /**
     * @see ImageUpdate#getOps()
     */
    ImageUpdateBuilder ops(List<PatchOperation> ops);
}
