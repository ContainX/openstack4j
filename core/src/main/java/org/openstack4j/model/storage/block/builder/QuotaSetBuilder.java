package org.openstack4j.model.storage.block.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.storage.block.QuotaSet;

/**
 * Builder for a QuotaSet model class.
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSetBuilder extends Builder<QuotaSetBuilder, QuotaSet> {

    /**
     * @param volumes
     * @return volumes consumed in the Block Storage.
     */
    QuotaSetBuilder volumes(int volumes);

    /**
     *
     * @param snapshots
     * @return snapshots present in the Block Storage.
     */
    QuotaSetBuilder snapshots(int snapshots);

    /**
     *
     * @param gigabytes
     * @return space consumed in the Block Storage.
     */
    QuotaSetBuilder gigabytes(int gigabytes);
}
