package org.openstack4j.model.network.ext.builder;

import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.Ethertype;
import org.openstack4j.model.network.ext.FlowClassifier;

/**
 * 	A builder to create a flow classifier
 * @author Dmitry Gerenrot
 *
 */
public interface FlowClassifierBuilder extends Builder<FlowClassifierBuilder, FlowClassifier> {

	/**
	 * @param id : Flow Classifier identifer
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder id(String id);

	/**
	 * @param name : Human readable name for the flow classifier
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder name(String name);

	/**
	 * @param tenantId : Tenant (project) identifier
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder tenandId(String tenantId);

	/**
	 * @param description : Human readable description for the flow classifier
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder description(String description);

	/**
	 *
	 * @param protocol : Short name for the protocol (TCP, UDP, etc)
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder protocol(String protocol);

	/**
	 * @param sourcePortRangeMin : Minimum value for the source port, converted to String
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder sourcePortRangeMin(String sourcePortRangeMin);

	/**
	 * @param sourcePortRangeMax : Maximum value for the source port, converted to String
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder sourcePortRangeMax(String sourcePortRangeMax);

	/**
	 * @param destinationPortRangeMin : Minimum value for the destination port, converted to String
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder destinationPortRangeMin(String destinationPortRangeMin);

	/**
	 * @param destinationPortRangeMax : Maximum value for the destination port, converted to String
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder destinationPortRangeMax(String destinationPortRangeMax);

	/**
	 * @param sourceIpPrefix
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder sourceIpPrefix(String sourceIpPrefix);

	/**
	 * @param destinationIpPrefix
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder destinationIpPrefix(String destinationIpPrefix);

	/**
	 * @param logicalSourcePort
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder logicalSourcePort(String logicalSourcePort);

	/**
	 * @param logicalDestinationPort
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder logicalDestinationPort(String logicalDestinationPort);

	/**
	 * @param l7Parameters
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder l7Parameters(Map<String, String> l7Parameters);

	/**
	 * @param ethertype
	 * @return FlowClassifierBuilder
	 */
	FlowClassifierBuilder ethertype(Ethertype ethertype);
}
