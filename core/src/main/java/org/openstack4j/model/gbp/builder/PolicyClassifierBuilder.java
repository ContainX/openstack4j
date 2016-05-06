package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyClassifier;

public interface PolicyClassifierBuilder extends Builder<PolicyClassifierBuilder, PolicyClassifier> {
    PolicyClassifierBuilder name(String name);
    PolicyClassifierBuilder description(String description);
    PolicyClassifierBuilder portRangeMin(int min);
    PolicyClassifierBuilder portRangeMax(int max);
    PolicyClassifierBuilder direction(PolicyClassifier.Direction direction);
    PolicyClassifierBuilder protocol(PolicyClassifier.Protocol protocol);
    PolicyClassifierBuilder shared(boolean shared);
}
