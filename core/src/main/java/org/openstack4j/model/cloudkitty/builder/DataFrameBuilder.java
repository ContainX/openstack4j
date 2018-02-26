package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.DataFrame;
import org.openstack4j.model.cloudkitty.RatedResource;

import java.util.Date;
import java.util.List;

public interface DataFrameBuilder extends Buildable.Builder<DataFrameBuilder, DataFrame> {

    DataFrameBuilder begin(Date begin);

    DataFrameBuilder end(Date end);

    DataFrameBuilder resources(List<? extends RatedResource> resources);

    DataFrameBuilder tenantId(String tenantId);
}
