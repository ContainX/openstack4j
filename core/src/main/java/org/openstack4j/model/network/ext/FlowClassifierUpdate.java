package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.FlowClassifierBuilder;
import org.openstack4j.model.network.ext.builder.FlowClassifierUpdateBuilder;

/**
 * Entity used to update an existing flow classifier. For an existing Flow Classifier you can update just the name and description
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

/**
 * Entity used to update an existing flow classifier. For an existing Flow Classifier you can update just the name and description
 */
public interface FlowClassifierUpdate extends ModelEntity, Buildable<FlowClassifierUpdateBuilder> {


	/**
	 * @return Human-readable description for the Flow Classifier
	 */
	public String getDescription();

	/**
	 *
	 *
	 * @return Human-readable name for the VIP. Does not have to be unique.
	 */
	public String getName();




	
	
}
