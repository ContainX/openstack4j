package org.openstack4j.api.scaling;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.scaling.ScalingQuota;

public interface AutoScalingQuotaService extends RestService {
	public List<? extends ScalingQuota> list();
	
	public List<? extends ScalingQuota> list(String groupId);
}
