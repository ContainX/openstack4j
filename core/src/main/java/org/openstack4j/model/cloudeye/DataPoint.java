package org.openstack4j.model.cloudeye;

import org.openstack4j.model.ModelEntity;

import java.util.Date;

public interface DataPoint extends ModelEntity{
    Number getAverage();
    Number getVariance();
    Number getMin();
    Number getMax();
    Date getTimestamp();
    String getUnit();
}
