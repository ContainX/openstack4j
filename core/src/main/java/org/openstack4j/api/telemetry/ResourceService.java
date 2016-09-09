package org.openstack4j.api.telemetry;

import java.util.List;

import org.openstack4j.model.telemetry.Resource;

public interface ResourceService {

    List<? extends Resource> list();

    Resource get(String resourceId);

}
