package org.openstack4j.openstack.message.notification.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * A model contains represent request ID which could be used to trace a API request
 *
 * @author QianBiao.NG
 * @date   2017-07-17 10:42:14
 */
public class TracableRequest {
	
	/**
	 * API request ID
	 */
	@JsonProperty("request_id")
	String requestId;

	public String getRequestId() {
		return requestId;
	}

}
