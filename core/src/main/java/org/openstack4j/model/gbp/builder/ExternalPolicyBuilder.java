package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.ExternalPolicy;

public interface ExternalPolicyBuilder extends Builder<ExternalPolicyBuilder, ExternalPolicy> {

    ExternalPolicyBuilder name(String string);
    ExternalPolicyBuilder description(String description);
}
