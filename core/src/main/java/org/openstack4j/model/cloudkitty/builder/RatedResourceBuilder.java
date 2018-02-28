package org.openstack4j.model.cloudkitty.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.cloudkitty.RatedResource;

import java.util.Map;

public interface RatedResourceBuilder extends Buildable.Builder<RatedResourceBuilder, RatedResource> {

    RatedResourceBuilder description(Map<String, String> description);

    RatedResourceBuilder service(String service);

    RatedResourceBuilder volume(float volume);

    RatedResourceBuilder rating(float rating);
}
