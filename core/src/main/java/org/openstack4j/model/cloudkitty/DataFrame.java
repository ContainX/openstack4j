package org.openstack4j.model.cloudkitty;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.DataFrameBuilder;

import java.util.Date;
import java.util.List;

public interface DataFrame extends ModelEntity, Buildable<DataFrameBuilder> {

    Date getBegin();

    Date getEnd();

    List<?extends RatedResource> getResources();

    String getTenantId();
}
