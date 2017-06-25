package org.openstack4j.openstack.cloudeye.internal;

import org.openstack4j.api.cloudeye.QuotaService;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeQuota;

import static org.openstack4j.core.transport.ClientConstants.PATH_QUOTA;

public class CloudEyeQuotaServiceImpl extends BaseCloudEyeServices
		implements QuotaService {


	@Override
	public CloudEyeQuota get() {
		return get(CloudEyeQuota.class, PATH_QUOTA).execute();
	}
}
