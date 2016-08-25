package org.openstack4j.openstack.tacker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
@JsonRootName("attributes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VnfAttributes {
	
	@JsonProperty("service_type")
	private String serviceType;
	
	@JsonProperty("param_values")
	private String paramValues;
	
	@JsonProperty("heat_template")
	private String heatTemplate;
	
	@JsonProperty("monitoring_policy")
	private String monitoringPolicy;
	
	@JsonProperty("failure_policy")
	private String failurePolicy;
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("serviceType", serviceType)
				.add("paramValues", paramValues)
				.add("heatTemplate", heatTemplate)
				.add("monitoringPolicy", monitoringPolicy)
				.add("failurePolicy", failurePolicy)
				.toString();
	}

	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the paramValues
	 */
	public String getParamValues() {
		return paramValues;
	}

	/**
	 * @param paramValues the paramValues to set
	 */
	public void setParamValues(String paramValues) {
		this.paramValues = paramValues;
	}

	/**
	 * @return the heatTemplate
	 */
	public String getHeatTemplate() {
		return heatTemplate;
	}

	/**
	 * @param heatTemplate the heatTemplate to set
	 */
	public void setHeatTemplate(String heatTemplate) {
		this.heatTemplate = heatTemplate;
	}

	/**
	 * @return the monitoringPolicy
	 */
	public String getMonitoringPolicy() {
		return monitoringPolicy;
	}

	/**
	 * @param monitoringPolicy the monitoringPolicy to set
	 */
	public void setMonitoringPolicy(String monitoringPolicy) {
		this.monitoringPolicy = monitoringPolicy;
	}

	/**
	 * @return the failurePolicy
	 */
	public String getFailurePolicy() {
		return failurePolicy;
	}

	/**
	 * @param failurePolicy the failurePolicy to set
	 */
	public void setFailurePolicy(String failurePolicy) {
		this.failurePolicy = failurePolicy;
	}
}
