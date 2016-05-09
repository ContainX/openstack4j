package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.L2Policy;
/**
 * A builder which produces a L2Policies object
 * 
 * @author vinod borole
 */
public interface L2PolicyBuilder extends Builder<L2PolicyBuilder, L2Policy> {

    L2PolicyBuilder name(String name);
    L2PolicyBuilder description(String description);
    L2PolicyBuilder isShared(boolean shared);
}
