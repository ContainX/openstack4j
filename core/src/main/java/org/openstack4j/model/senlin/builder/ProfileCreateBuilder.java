package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.ProfileCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link ProfileCreate} objects
 * 
 * @author lion
 */
public interface ProfileCreateBuilder extends Buildable.Builder<ProfileCreateBuilder, ProfileCreate> {

	ProfileCreateBuilder name(String name);

	ProfileCreateBuilder spec(Map<String, Object> spec);

	ProfileCreateBuilder metadata(Map<String, Map> metadata);


}
