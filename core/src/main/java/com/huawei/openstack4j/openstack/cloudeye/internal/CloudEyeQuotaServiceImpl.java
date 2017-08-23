package com.huawei.openstack4j.openstack.cloudeye.internal;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import com.huawei.openstack4j.api.cloudeye.QuotaService;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeQuota;

public class CloudEyeQuotaServiceImpl extends BaseCloudEyeServices
		implements QuotaService {


	@Override
	public CloudEyeQuota get() {
		return get(CloudEyeQuota.class, PATH_QUOTA).execute();
	}
}
