package com.huawei.openstack4j.functional.loadbalancer;

import static org.testng.Assert.*;

import java.util.Map;

import com.google.common.base.Strings;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Type;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.AsyncJob.Status;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBJob;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate;

/**
 *
 * @author QianBiao.NG
 * @date   2017-08-25 15:50:55
 */
public class BaseELBTest extends AbstractTest {

	public LoadBalancer createLoadBalancer(String name, String vpcId, Integer bandwidth) {
		ELBLoadBalancerCreate loadBalancer = ELBLoadBalancerCreate.builder().name(name).vpcId(vpcId)
				.bandwidth(bandwidth).type(Type.EXTERNAL).adminStateUp(1).build();
		ELBJob job = osclient.loadBalancer().loadBalancers().create(loadBalancer);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));

		// waiting for load balancer asynchronous creating
		AsyncJob asyncJob = (AsyncJob) this.retry(new Retry() {
			@Override
			public Integer maxRetryTimes() {
				return 40;
			}

			@Override
			public Integer delay() {
				return 10;
			}

			@Override
			public Object run() {
				AsyncJob asyncJob = osclient.loadBalancer().jobs().get(job.getJobId());
				if (asyncJob.getStatus().equals(Status.SUCCESS)) {
					return asyncJob;
				}
				if (asyncJob.getStatus().equals(Status.FAIL)) {
					throw new RuntimeException("failed to create load balancer");
				}
				return null;
			}
		});

		@SuppressWarnings("unchecked")
		Map<String, Object> elb = (Map<String, Object>) asyncJob.getEntities().get("elb");
		return osclient.loadBalancer().loadBalancers().get(elb.get("id").toString());
	}
	
	public void deleteLoadBalancer(String lbId) {
		ELBJob job = osclient.loadBalancer().loadBalancers().delete(lbId);
		assertTrue(!Strings.isNullOrEmpty(job.getJobId()));
		// waiting for load balancer asynchronous creating
		this.retry(new Retry() {
			@Override
			public Integer maxRetryTimes() {
				return 40;
			}

			@Override
			public Integer delay() {
				return 10;
			}

			@Override
			public Object run() {
				AsyncJob asyncJob = osclient.loadBalancer().jobs().get(job.getJobId());
				if (asyncJob.getStatus().equals(Status.SUCCESS)) {
					return asyncJob;
				}
				if (asyncJob.getStatus().equals(Status.FAIL)) {
					throw new RuntimeException("failed to delete load balancer");
				}
				return null;
			}
		});
	}

}
