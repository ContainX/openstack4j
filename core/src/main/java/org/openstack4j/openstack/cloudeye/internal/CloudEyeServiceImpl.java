package org.openstack4j.openstack.cloudeye.internal;

import org.openstack4j.api.cloudeye.CloudEyeService;
import org.openstack4j.api.cloudeye.MetricDataService;
import org.openstack4j.api.cloudeye.MetricService;
import org.openstack4j.api.cloudeye.QuotaService;
import org.openstack4j.api.scaling.AutoScalingGroupInstanceService;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-14 09:57:45
 */
public class CloudEyeServiceImpl extends BaseCloudEyeServices implements CloudEyeService {

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public MetricService metrics() {
		return null;
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public MetricDataService alarms() {
		return null;
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public AutoScalingGroupInstanceService datas() {
		return null;
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public QuotaService quotas() {
		return null;
	}

	
}
