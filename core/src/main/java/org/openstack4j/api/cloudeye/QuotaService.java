package org.openstack4j.api.cloudeye;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeQuota;

public interface QuotaService extends RestService {
	CloudEyeQuota get();
}
