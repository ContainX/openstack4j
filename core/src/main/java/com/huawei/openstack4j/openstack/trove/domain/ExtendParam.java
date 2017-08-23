package com.huawei.openstack4j.openstack.trove.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-08-07 16:09:16
 */
@JsonRootName("extendparam")
public class ExtendParam {

	/**
	 * asynchronous jobs for DB instance creation
	 */
	@JsonProperty("jobs")
	List<IdResourceEntity> jobs;

	public List<IdResourceEntity> getJobs() {
		return jobs;
	}

	public void setJobs(List<IdResourceEntity> jobs) {
		this.jobs = jobs;
	}

}
