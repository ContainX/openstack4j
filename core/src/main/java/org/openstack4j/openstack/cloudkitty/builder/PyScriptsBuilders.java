package org.openstack4j.openstack.cloudkitty.builder;

import org.openstack4j.model.cloudkitty.builder.pyscripts.ScriptBuilder;
import org.openstack4j.openstack.cloudkitty.domain.pyscripts.PyScriptsScript;

public class PyScriptsBuilders {

    public ScriptBuilder script() {
        return PyScriptsScript.builder();
    }
}
