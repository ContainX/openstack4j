package com.huawei.openstack4j.openstack.cloudeye.internal;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.cloudeye.QuotaService;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.Quotas;

public class CloudEyeQuotaServiceImpl extends BaseCloudEyeServices implements QuotaService {

	@Override
	public List<Quota> get() {
		return get(Quotas.class, PATH_QUOTA).execute().getList();
	}
}
