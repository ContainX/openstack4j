package org.openstack4j.model.cloudkitty.builder.pyscripts;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.pyscripts.Script;

public interface ScriptBuilder extends Buildable.Builder<ScriptBuilder, Script> {

    ScriptBuilder id(String id);

    ScriptBuilder checksum(String checksum);

    ScriptBuilder name(String name);

    ScriptBuilder data(String data);
}
