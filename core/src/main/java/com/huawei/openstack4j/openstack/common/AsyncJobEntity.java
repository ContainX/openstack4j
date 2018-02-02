package com.huawei.openstack4j.openstack.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.common.AsyncJob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * HuaWei Resouce Quota
 *
 * @author QianBiao.NG
 * @date   2017-07-14 14:16:37
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsyncJobEntity implements AsyncJob {

	private static final long serialVersionUID = -8479016593614559914L;
	
	@JsonProperty("job_id")
	String jobId;

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return jobId;
	}

}
