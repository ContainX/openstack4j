package org.openstack4j.api.barbican;

import org.openstack4j.model.barbican.Secret;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by reneschollmeyer on 02.08.17.
 */
public interface SecretService {

    List<? extends Secret> list(final Map<String, String> filteringParams);

    List<? extends Secret> list(final String name);

    Secret get(final String secretId);

    ActionResponse delete(final String secretId);

    Secret create(final Secret secret);
}