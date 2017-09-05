package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.FlowClassifierProtocol;

/**
 * A Builder which creates a Flow Classifier
 * 
 * @author Massimiliano Romano
 */
public interface FlowClassifierBuilder extends Builder<FlowClassifierBuilder, FlowClassifier> {

	/**
	 * @see FlowClassifier#getName()
	 */
	FlowClassifierBuilder name(String name);




	/**
	 * @see FlowClassifier#getDescription()
	 */
	FlowClassifierBuilder description(String description);




	/**
	 * @see FlowClassifier#getProjectId()
	 */
	FlowClassifierBuilder projectId(String projectId);



	/**
	 * @see FlowClassifier#getSourcePortRangeMin()
	 */
	FlowClassifierBuilder sourcePortRangeMin(Integer sourcePortRangeMin);

	/**
	 * @see FlowClassifier#getSourcePortRangeMax()
	 */
	FlowClassifierBuilder sourcePortRangeMax(Integer sourcePortRangeMax);

	/**
	 * @see FlowClassifier#getDestinationPortRangeMin()
	 */
	FlowClassifierBuilder destinationPortRangeMin(Integer destinationPortRangeMin);

	/**
	 * @see FlowClassifier#getDestinationPortRangeMax()
	 */
	FlowClassifierBuilder destinationPortRangeMax(Integer destinationPortRangeMax);


	/**
	 * @see FlowClassifier#getDestinationIpPrefix()
	 */
	FlowClassifierBuilder destinationIpPrefix(String destinationIpPrefix);

	/**
	 * @see FlowClassifier#getProtocol()
	 */
	FlowClassifierBuilder protocol(FlowClassifierProtocol protocol);

	/**
	 * @see FlowClassifier#getEthertype()
	 */
	FlowClassifierBuilder ethertype(String ethertype);


	/**
	 * @see FlowClassifier#getLogicalSourcePort()
	 */
	FlowClassifierBuilder logicalSourcePort(String logicalSourcePort);

	/**
	 * @see FlowClassifier#getLogicalDestinationPort()
	 */
	FlowClassifierBuilder logicalDestinationPort(String logicalDestinationPort);

	/**
	 * @see FlowClassifier#getSourceIpPrefix()
	 */
	FlowClassifierBuilder sourceIpPrefix(String sourceIpPrefix);


	/**
	 * @see FlowClassifier#getTenantId()
	 */
	FlowClassifierBuilder tenantId(String tenantId);






}
