package org.openstack4j.openstack.dns.v2.internal;

import static com.google.common.base.Preconditions.*;
import static org.openstack4j.core.transport.ClientConstants.PATH_PTR;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.dns.v2.PTRService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.PTR;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR;

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
		checkNotNull(record);
		checkArgument(record.getTtl() >= 300 && record.getTtl() <= 2147483647);
		return patch(DesignatePTR.class, PATH_PTR, "/", record.getRegion(), ":", record.getFloatingIpId()).entity(record).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public DesignatePTR get(String region, String floatingIpId) {
		checkNotNull(region);
		checkNotNull(floatingIpId);
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
	public ActionResponse restore(DesignatePTR record) {
		checkNotNull(record);
		return patchWithResponse(PATH_PTR, "/", record.getRegion(), ":", record.getFloatingIpId()).entity(record).execute();
	}

}
