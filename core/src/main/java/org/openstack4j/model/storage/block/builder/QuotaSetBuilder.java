package org.openstack4j.model.storage.block.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.storage.block.QuotaSet;

/**
 * Builder for a QuotaSet model class.
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSetBuilder extends Builder<QuotaSetBuilder, QuotaSet> {

    QuotaSetBuilder volumes(int volumes);

    QuotaSetBuilder snapshots(int snapshots);

    QuotaSetBuilder gigabytes(int gigabytes);

}
