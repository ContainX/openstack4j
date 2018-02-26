package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.Summary;

import java.util.Date;

public interface SummaryBuilder extends Buildable.Builder<SummaryBuilder, Summary> {

    SummaryBuilder begin(Date begin);

    SummaryBuilder end(Date end);

    SummaryBuilder tenantId(String tenantId);

    SummaryBuilder resourceType(String resourceType);

    SummaryBuilder rate(float rate);
}
