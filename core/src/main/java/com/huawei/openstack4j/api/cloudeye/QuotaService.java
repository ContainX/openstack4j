package com.huawei.openstack4j.api.cloudeye;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.openstack.common.Quota;

public interface QuotaService extends RestService {
	
	/**
	 * get Quota of CloudEye Service
	 * 
	 * @return list of {@link Quota} instance
	 */
	List<Quota> get();
	
}
