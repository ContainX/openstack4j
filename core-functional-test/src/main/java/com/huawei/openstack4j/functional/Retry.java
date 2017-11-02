package com.huawei.openstack4j.functional;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 14:39:12
 */
public interface Retry {
	
	public Integer maxRetryTimes();
	
	public Integer delay();

	public Object run();
}
