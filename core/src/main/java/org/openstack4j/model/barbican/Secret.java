package org.openstack4j.model.barbican;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.barbican.builder.SecretCreateBuilder;
import org.openstack4j.model.common.ActionResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by reneschollmeyer on 02.08.17.
 */
public interface Secret extends ModelEntity, Buildable<SecretCreateBuilder> {

    String getAlgorithm();

    int getBitLength();

    List<String> getContentTypes();

    Date getCreateTime();

    Date getUpdateTime();

    String getCreatorId();

    String getExpiration();

    String getMode();

    String getName();

    String getSecretReference();

    String getSecretType();

    String getStatus();
}
