package org.openstack4j.model.storage.block;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.storage.block.builder.QuotaSetBuilder;

/**
 * An OpenStack Quota-Set
 *
 * @author Jeremy Unruh
 */
public interface QuotaSet extends ModelEntity, Buildable<QuotaSetBuilder> {

    /**
     * @return the identifier
     */
    String getId();

    int getSnapshots();

    int getVolumes();

    int getGigabytes();

}
