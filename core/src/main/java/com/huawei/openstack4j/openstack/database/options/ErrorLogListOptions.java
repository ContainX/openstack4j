package com.huawei.openstack4j.openstack.database.options;

import java.util.Date;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.common.DateTimeUtils;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class ErrorLogListOptions {

	/**
	 * database instance id
	 */
	String instanceId;
	private Map<String, Object> queryParams = Maps.newHashMap();

	// startDate
	// endDate
	// curPage
	// perPage

	/**
	 * set database instance id filter option
	 * 
	 * @param instanceId database instance id
	 * @return
	 */
	public ErrorLogListOptions instanceId(String instanceId) {
		this.instanceId = instanceId;
		return this;
	}

	/**
	 * set log start date filter option
	 * 
	 * @param startDate log start date
	 * @return {@link ErrorLogListOptions} instance
	 */
	public ErrorLogListOptions startDate(Date startDate) {
		Preconditions.checkArgument(startDate != null, "parameter `startDate` should not be null");
		String format = DateTimeUtils.format(startDate, DateTimeUtils.FORMAT_YMD_HM);
		return add("startDate", format);
	}

	/**
	 * set log end date filter option
	 * 
	 * @param endDate log end date
	 * @return {@link ErrorLogListOptions} instance
	 */
	public ErrorLogListOptions endDate(Date endDate) {
		Preconditions.checkArgument(endDate != null, "parameter `endDate` should not be null");
		String format = DateTimeUtils.format(endDate, DateTimeUtils.FORMAT_YMD_HM);
		return add("endDate", format);
	}

	/**
	 * the pagination page number, 1 by default
	 * 
	 * @param page the pagination page number
	 * @return {@link ErrorLogListOptions} instance
	 */
	public ErrorLogListOptions curPage(Integer page) {
		return add("curPage", page);
	}

	/**
	 * the pagination page size, the value range is 1~100, 10 by default
	 * 
	 * @param page the pagination page size
	 * @return {@link ErrorLogListOptions} instance
	 */
	public ErrorLogListOptions perPage(Integer pageSize) {
		return add("perPage", pageSize);
	}

	private ErrorLogListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
	
	public String getInstanceId() {
		return instanceId;
	}

	public static ErrorLogListOptions create() {
		return new ErrorLogListOptions();
	}

}
