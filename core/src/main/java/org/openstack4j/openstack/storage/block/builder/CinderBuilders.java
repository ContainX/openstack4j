package org.openstack4j.openstack.storage.block.builder;


import org.openstack4j.model.storage.block.builder.*;
import org.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import org.openstack4j.openstack.storage.block.domain.CinderVolume;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackupCreate;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeSnapshot;

public class CinderBuilders implements StorageBuilders {

    private CinderBuilders StorageBuilders() {
        return this;
    }

    @Override
    public BlockQuotaSetBuilder blockQuotaSet() {
        return CinderBlockQuotaSet.builder();
    }

    @Override
    public VolumeBuilder volume() {
        return CinderVolume.builder();
    }

    @Override
    public VolumeSnapshotBuilder volumeSnapshot() {
        return CinderVolumeSnapshot.builder();
    }

    @Override
    public VolumeBackupCreateBuilder volumeBackupCreate() {
        return CinderVolumeBackupCreate.builder();
    }
}
