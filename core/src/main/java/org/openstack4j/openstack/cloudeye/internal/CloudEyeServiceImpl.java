package org.openstack4j.openstack.cloudeye.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.cloudeye.*;
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
		return Apis.get(MetricService.class);
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public AlarmService alarms() {
		return Apis.get(AlarmService.class);
	}

	@Override
	public MetricDataService metricsDatas() {
		return Apis.get(MetricDataService.class);
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
