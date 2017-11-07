package com.huawei.openstack4j.model.map.reduce.options;

import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.map.reduce.constants.JobState;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-04 09:30:34
 */
public class JobExeListOptions {


	private Map<String, Object> queryParams = Maps.newHashMap();
	
	public JobExeListOptions id(String id) {
		return add("id", id);
	}
	
	public JobExeListOptions clusterId(String clusterId) {
		return add("cluster_id", clusterId);
	}
	
	public JobExeListOptions jobName(String jobName) {
		return add("job_name", jobName);
	}
	
	public JobExeListOptions state(JobState state) {
		return add("state", state.value());
	}

	
	public JobExeListOptions pageSize(Integer pageSize) {
		return add("page_size", pageSize);
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public JobExeListOptions page(Integer page) {
		return add("current_page", page);
	}

	private JobExeListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static JobExeListOptions create() {
		return new JobExeListOptions();
	}

}
