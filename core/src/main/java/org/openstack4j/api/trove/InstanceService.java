package org.openstack4j.api.trove;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.trove.Instance;
import org.openstack4j.model.trove.InstanceCreate;

import java.util.List;

/**
 *
 * @author Craig Vyvial
 */
public interface InstanceService {
    List<? extends Instance> list();
    Instance get(String instanceId);
    Instance create(InstanceCreate instance);
    ActionResponse delete(String instanceId);

}
