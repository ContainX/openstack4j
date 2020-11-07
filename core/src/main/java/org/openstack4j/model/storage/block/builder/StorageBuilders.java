package org.openstack4j.model.storage.block.builder;


/**
 * The Storage builders
 */
public interface StorageBuilders {

    /**
     * The builder which creates a BlockQuotaSet
     *
     * @return the block quota-set builder
     */
    BlockQuotaSetBuilder blockQuotaSet();

    /**
     * The builder to create a Block Volume
     *
     * @return the volume builder
     */
    VolumeBuilder volume();

    /**
     * The builder to create a Block Volume Snapshot
     *
     * @return the snapshot builder
     */
    VolumeSnapshotBuilder volumeSnapshot();

    /**
     * The builder to create a volume backup
     *
     * @return the backup creation builder
     */
    VolumeBackupCreateBuilder volumeBackupCreate();

}
