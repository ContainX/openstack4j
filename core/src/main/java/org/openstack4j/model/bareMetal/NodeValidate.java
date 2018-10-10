package org.openstack4j.model.bareMetal;

public interface NodeValidate {

    Validate getBoot();

    Validate getConsole();

    Validate getDeploy();

    Validate getInspect();

    Validate getManagement();

    Validate getNetwork();

    Validate getPower();

    Validate getRaid();

    Validate getRescue();

    Validate getStorage();

}
