package com.huawei.openstack4j.api.cloudeye;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeQuota;

public interface QuotaService extends RestService {
	CloudEyeQuota get();
}
