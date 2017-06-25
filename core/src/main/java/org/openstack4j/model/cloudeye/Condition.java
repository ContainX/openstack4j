package org.openstack4j.model.cloudeye;

import org.openstack4j.model.ModelEntity;

/**
 * Created by coa.ke on 6/24/17.
 */
public interface Condition extends ModelEntity {
    Integer getPeriod();
    String getFilter();
    String getComparisonOperator();
    Number getValue();
    String getUnit();
    Integer getCount();
}
