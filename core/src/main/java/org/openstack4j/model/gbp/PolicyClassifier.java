package org.openstack4j.model.gbp;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyClassifierBuilder;

public interface PolicyClassifier extends Buildable<PolicyClassifierBuilder>, Resource {

    boolean isShared();

    String getProtocol();

    String getDirection();

    String getPortRange();

    String getDescription();

}
