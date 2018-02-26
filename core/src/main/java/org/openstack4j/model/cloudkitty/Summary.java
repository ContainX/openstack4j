package org.openstack4j.model.cloudkitty;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.SummaryBuilder;

import java.util.Date;

public interface Summary extends ModelEntity, Buildable<SummaryBuilder> {

    Date getBegin();

    Date getEnd();

    String getTenantId();

    String getResourceType();

    float getRate();
}
