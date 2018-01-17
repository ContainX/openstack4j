package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.ext.builder.FlowClassifierBuilder;

/**
 * A Flow Classifier Entity.
 * 
 * @author Dmitry Gerenrot.
 *
 */
public interface FlowClassifier extends Resource, Buildable<FlowClassifierBuilder> {
	
	/**
	 * @return id : Flow Classifier identifer
	 */
	String getId();

	/**
	 * @return name : Human readable name for the flow classifier
	 */
	String getName();
	
	/**
	 * @return description : Human readable description for the flow classifier
	 */
	String getDescription();
	
	/**
	 * @return protocol : Short name for the protocol (TCP, UDP, etc)
	 */
	String getProtocol();

	/**
	 * @return rangeMin : Minimum value for the source port, converted to String
	 */
	String getSourcePortRangeMin();

	/**
	 * @return rangeMax : Maximum value for the source port, converted to String
	 */
	String getSourcePortRangeMax();

	/**
	 * @return rangeMin : Minimum value for the destination port, converted to String
	 */
	String getDestinationPortRangeMin();
	
	/**
	 * @return rangeMax : Maximum value for the destination port, converted to String
	 * 
	 */
	String getDestinationPortRangeMax();
	
	/**
	 * @return sourcePrefix : Prefix for the source ip addresses
	 */
	String getSourceIpPrefix();

	/**
	 * @return destinationPrefix : Prefix for the destination ip addresses
	 */
	String getDestinationIpPrefix();
}
