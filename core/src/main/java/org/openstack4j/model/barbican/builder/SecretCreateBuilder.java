package org.openstack4j.model.barbican.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.barbican.Secret;

/**
 * Created by reneschollmeyer on 02.08.17.
 */
public interface SecretCreateBuilder extends Buildable.Builder<SecretCreateBuilder, Secret> {

    SecretCreateBuilder name(String name);
}
