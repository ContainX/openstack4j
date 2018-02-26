package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.Resource;

import java.util.Map;

public interface ResourceBuilder extends Buildable.Builder<ResourceBuilder, Resource> {

    ResourceBuilder description(Map<String, String> description);

    ResourceBuilder service(String service);

    ResourceBuilder volume(double volume);
}
