package org.openstack4j.openstack.compute.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.MicroVersion;
import org.openstack4j.openstack.internal.MicroVersionedOpenStackService;

class BaseMicroVersionedComputeServices extends MicroVersionedOpenStackService {

    static MicroVersion MICRO_VERSION_2_15 = new MicroVersion(2, 15);

    BaseMicroVersionedComputeServices(MicroVersion microVersion) {
        super(ServiceType.COMPUTE, new ComputeMicroVersion(microVersion));
    }

    @Override
    protected String getApiVersionHeader() {
        return "OpenStack-API-Version";
    }

    private static class ComputeMicroVersion extends MicroVersion {

        ComputeMicroVersion(MicroVersion microVersion) {
            super(microVersion.toString());
        }

        @Override
        public String toString() {
            return "compute " + super.toString();
        }
    }
}
