package org.openstack4j.model.storage.block.builder;

import org.openstack4j.model.storage.block.BlockQuotaSet;

/**
 * The Storage builders
 */
public interface StorageBuilders {

    /**
     * The builder which creates a BlockQuotaSet
     *
     * @return the block quota-set builder
     */
    public BlockQuotaSetBuilder blockQuotaSet();

    /**
     * The builder to create a Block Volume
     *
     * @return the volume builder
     */
    public VolumeBuilder volume();

    /**
     * The builder to create a Block Volume Snapshot
     *
     * @return the snapshot builder
     */
    public VolumeSnapshotBuilder volumeSnapshot();

}
