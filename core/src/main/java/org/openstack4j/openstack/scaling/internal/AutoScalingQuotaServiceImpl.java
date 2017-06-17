package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingQuotaService;
import org.openstack4j.model.scaling.ScalingQuota;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingQuota.ASAutoScalingQuotas;
import org.testng.util.Strings;

public class AutoScalingQuotaServiceImpl extends BaseAutoScalingServices implements AutoScalingQuotaService {

	@Override
	public List<? extends ScalingQuota> list() {
		return get(ASAutoScalingQuotas.class, uri("/quotas")).execute().getList();
	}

	@Override
	public List<? extends ScalingQuota> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		return get(ASAutoScalingQuotas.class, uri("/quotas/%s", groupId)).execute().getList();
	}
}
