package org.openstack4j.model.cloudeye;

import org.openstack4j.model.ModelEntity;

/**
 * Created by coa.ke on 6/21/17.
 */

public interface MetricDimensions extends ModelEntity {
    String getName();
    String getValue();
}
