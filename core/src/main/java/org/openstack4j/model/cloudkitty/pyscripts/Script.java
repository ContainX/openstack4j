package org.openstack4j.model.cloudkitty.pyscripts;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.builder.pyscripts.ScriptBuilder;

public interface Script extends ModelEntity, Buildable<ScriptBuilder> {

    String getId();

    String getChecksum();

    String getData();

    String getName();
}
