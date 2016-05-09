package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.ExternalSegment;
/**
 * A builder which produces a External Segments object
 * 
 * @author vinod borole
 */
public interface ExternalSegmentBuilder extends Builder<ExternalSegmentBuilder, ExternalSegment> {

    ExternalSegmentBuilder name(String name);
    ExternalSegmentBuilder description(String description);

}
