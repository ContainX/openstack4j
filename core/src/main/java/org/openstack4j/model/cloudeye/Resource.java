package org.openstack4j.model.cloudeye;

import org.openstack4j.model.ModelEntity;

public interface Resource extends ModelEntity{
    QuotaType getType();
    Integer getUsed();
    String getUnit();
    Integer getQuota();
}
