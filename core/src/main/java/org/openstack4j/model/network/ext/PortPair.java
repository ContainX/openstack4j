package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;


/**
 * a port pair
 * 
 * @author Massimiliano Romano
 */

/*
		# neutron port-pair-show PP12
		+-----------------------------+--------------------------------------+
		| Field                       | Value                                |
		+-----------------------------+--------------------------------------+
		| description                 | Firewall SF instance 1               |
		| egress                      | 73e26bc3-c30c-4453-aa63-e517ed54f5e9 |
		| id                          | 40621501-a46f-4472-b902-757b3a8c9b88 |
		| ingress                     | d6484a19-4a2b-4b8c-af16-5c37a7fb74ff |
		| name                        | PP12                                 |
		| project_id                  | fa30ea019b0d43d7a917b31f28a5efdb     |
		| service_function_parameters | {"weight": 1, "correlation": null}   |
		| tenant_id                   | fa30ea019b0d43d7a917b31f28a5efdb     |
		+-----------------------------+--------------------------------------+
*/

public interface PortPair extends ModelEntity, Buildable<PortPairBuilder> {
	/**
	 * 
	 * @return The egress port id.
	 */
	public String getEgressPortId();

	/**
	 *
	 * @return The ingress port id.
	 */
	public String getIngressPortId();


	/**
	 * @return Human-readable description for the Port Pair
	 */
	public String getDescription();

	/**
	 *
	 *
	 * @return The unique ID for the Port Pair.
	 */
	public String getId();

	/**
	 *
	 * 
	 * @return Human-readable name for the Port Pair. Does not have to be unique.
	 */
	public String getName();



	/**
	 * 
	 * @return PortPairServiceFunctionParameters
	 */
	public PortPairServiceFunctionParameters getServiceFunctionParameters();



	/**
	 * 
	 * @return The Tenant Id.Owner of the Port Pair.
	 */
	public String getTenantId();


	/**
	 *
	 * @return The Project Id.Owner of the Port Pair.
	 */
	public String getProjectId();
	
	
}
