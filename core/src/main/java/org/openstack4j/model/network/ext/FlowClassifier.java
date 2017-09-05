package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.FlowClassifierBuilder;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;

/**
 * a vip of a pool
 * 
 * @author Massimiliano Romano
 */

/*
	{
		"flow_classifier": {
			"source_port_range_min": null,
			"destination_ip_prefix": "192.168.100.11/32",
			"protocol": "tcp",
			"description": "HTTP traffic from 192.168.100.9 to 192.168.100.11",
			"l7_parameters": {

			},
			"source_port_range_max": null,
			"id": "3e70c929-eb89-4647-b48a-c6829632a08b",
			"name": "FC1",
			"ethertype": "IPv4",
			"tenant_id": "fa30ea019b0d43d7a917b31f28a5efdb",
			"source_ip_prefix": null,
			"logical_destination_port": null,
			"destination_port_range_min": 80,
			"destination_port_range_max": 80,
			"project_id": "fa30ea019b0d43d7a917b31f28a5efdb",
			"logical_source_port": "9c40cd5a-2c98-4b5d-a220-dc73d7003bee"
		}
	}
*/

public interface FlowClassifier extends ModelEntity, Buildable<FlowClassifierBuilder> {


	/**
	 * @return Human-readable description for the Flow Classifier
	 */
	public String getDescription();

	/**
	 *
	 *
	 * @return The unique ID for the Flow Classifier.
	 */
	public String getId();



	/**
	 *
	 *
	 * @return Human-readable name for the VIP. Does not have to be unique.
	 */
	public String getName();

	/**
	 *
	 * @return The Project Id.Owner of the Port Pair.
	 */
	public String getProjectId();



	/**
	 * 
	 * @return The source port range min
	 */
	public Integer getSourcePortRangeMin();

	/**
	 *
	 * @return The source port range max
	 */
	public Integer getSourcePortRangeMax();

	/**
	 *
	 * @return The destination port range min
	 */
	public Integer getDestinationPortRangeMin();

	/**
	 *
	 * @return The destination port range max
	 */
	public Integer getDestinationPortRangeMax();


	/**
	 *
	 * @return The destination ip prefix
	 */
	public String getDestinationIpPrefix();

	/**
	 *
	 * @return The protocol (eg. tcp)
	 */
	public FlowClassifierProtocol getProtocol();

	/**
	 *
	 * @return The ethertype like IPv4 or IPv6
	 */
	public String getEthertype();


	/**
	 *
	 * @return The id of source port
	 */
	public String getLogicalSourcePort();

	/**
	 *
	 * @return The id of destination port
	 */
	public String getLogicalDestinationPort();

	/**
	 *
	 * @return The ip prefix of source
	 */
	public String getSourceIpPrefix();









	/**
	 * Not yet implemented, from openstack documentation:
	 * The l7_parameters attribute is a place holder that may be used to support flow classification using layer 7 fields, such as a URL.
	 * @return l7_parameters

	public FlowClassifierL7Parameters getFlowClassifierL7Parameters();
	*/


	/**
	 * 
	 * @return The Tenant Id.Owner of the Port Pair.
	 */
	public String getTenantId();



	
	
}
