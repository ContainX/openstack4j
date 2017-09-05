package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortChainBuilder;
import org.openstack4j.model.network.ext.builder.PortChainUpdateBuilder;

import java.util.List;


/**
 * a port chain update
 * 
 * @author Massimiliano Romano
 */

/*
		Port Chain
        id - Port chain ID.
        tenant_id - Tenant ID.
        name - Readable name.
        description - Readable description.
        port_pair_groups - List of port-pair-group IDs.
        flow_classifiers - List of flow-classifier IDs.
        chain_parameters - Dict. of chain parameters.
        chain_id - Data-plane chain path ID.


    {
      "port_chain": {
        "tenant_id": "fa30ea019b0d43d7a917b31f28a5efdb",
        "name": "PC1",
        "chain_parameters": {
          "symmetric": false,
          "correlation": "mpls"
        },
        "port_pair_groups": ["bf587d66-4427-4f27-a4f6-e6dcb362f7c5"],
        "flow_classifiers": ["3e70c929-eb89-4647-b48a-c6829632a08b"],
        "project_id": "fa30ea019b0d43d7a917b31f28a5efdb",
        "chain_id": 1,
        "id": "f56ba0f9-4a0e-4252-8bff-fecd5d418fea",
        "description": ""
      }
    }



 */


public interface PortChainUpdate extends ModelEntity, Buildable<PortChainUpdateBuilder> {

	/**
	 *
	 * @return the id list of port pair groups
	 */
	public List<String> getPortPairGroups();

	/**
	 *
	 * @return the id list of flow classifiers
	 */
	public List<String> getFlowClassifiers();




	/**
	 * @return Human-readable description for the Port Pair
	 */
	public String getDescription();





	/**
	 *
	 * 
	 * @return Human-readable name for the Port Pair. Does not have to be unique.
	 */
	public String getName();



	
}
