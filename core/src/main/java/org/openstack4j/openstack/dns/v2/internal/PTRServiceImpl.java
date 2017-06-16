package org.openstack4j.openstack.dns.v2.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

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
		return null;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public DesignatePTR get(String region, String floatingIpId) {
		checkNotNull(region);
		checkNotNull(floatingIpId);
		return get(DesignatePTR.class, "/reverse/floatingips/", region, ":", floatingIpId).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PTR> list() {
		return get(DesignatePTR.PTRList.class, uri("/reverse/floatingips")).execute().getList();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse restore(DesignatePTR record) {
		// TODO Auto-generated method stub
		return null;
	}

}
