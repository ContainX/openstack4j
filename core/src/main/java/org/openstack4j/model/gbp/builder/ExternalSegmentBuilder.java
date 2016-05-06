package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.ExternalSegment;

public interface ExternalSegmentBuilder extends Builder<ExternalSegmentBuilder, ExternalSegment> {

    ExternalSegmentBuilder name(String name);
    ExternalSegmentBuilder description(String description);

}
