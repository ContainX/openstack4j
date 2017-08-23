package com.huawei.openstack4j.openstack.dns.v2.internal;

import static com.google.common.base.Preconditions.*;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.dns.v2.PTRService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.dns.v2.PTR;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-16 10:40:24
 */
public class PTRServiceImpl extends BaseDNSServices implements PTRService {

	/*
	 * {@inheritDoc}
	 */
	@Override
	public DesignatePTR setup(DesignatePTR record) {
		checkNotNull(record, "The PTR record is Null.");
		checkNotNull(record.getPtrdname());
		checkArgument(record.getTtl() >= 300 && record.getTtl() <= 2147483647, "TTL value shold equal or bigger than 300, and equal or less than 2147483647");
		return patch(DesignatePTR.class, PATH_PTR, "/", record.getRegion(), ":", record.getFloatingIpId()).entity(record).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public DesignatePTR get(String region, String floatingIpId) {
		checkNotNull(region, "The region info is Null.");
		checkNotNull(floatingIpId, "The floating Ip ID is Null.");
		return get(DesignatePTR.class, PATH_PTR, "/", region, ":", floatingIpId).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PTR> list() {
		return get(DesignatePTR.PTRList.class, uri(PATH_PTR)).execute().getList();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PTR> list(Map<String, Object> filters) {
		Invocation<DesignatePTR.PTRList> invocation = get(DesignatePTR.PTRList.class, uri(PATH_PTR));
		invocation.params(filters);
		return invocation.execute().getList();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse restore(String region, String floatingIpId) {
		checkNotNull(region, "The region is Null.");
		checkNotNull(floatingIpId, "The floating Ip Id is Null.");
		DesignatePTR.DesignatePTRBuilder builder = DesignatePTR.builder().ptrdname(null);
		DesignatePTR ptrRecord = builder.build();
		return patchWithResponse(PATH_PTR, "/", region, ":", floatingIpId).entity(ptrRecord).execute();
	}

}

