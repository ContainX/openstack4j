package org.openstack4j.openstack.telemetry.internal;

import com.google.common.collect.Lists;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.Collections;
import java.util.List;

/**
 * Created by eandgya on 3/4/17.
 */
public class BaseTelemetryGnocchiServices extends BaseOpenStackService {
    protected BaseTelemetryGnocchiServices() {
        super(ServiceType.TELEMETRY_GNOCCHI);
    }

    protected <T> List<T> wrapList(T[] type) {
        if (type != null)
            return Lists.newArrayList(type);
        return Collections.emptyList();

    }
}
