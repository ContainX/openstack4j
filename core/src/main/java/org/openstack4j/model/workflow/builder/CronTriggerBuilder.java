package org.openstack4j.model.workflow.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.workflow.CronTrigger;

/**
 * Builder for a {@link CronTrigger} model class
 * 
 * @author Renat Akhmerov
 */
public interface CronTriggerBuilder extends Builder<CronTriggerBuilder, CronTrigger> {

	/**
	 * @see CronTrigger#getId()
	 */
	CronTriggerBuilder id(String id);

	/**
	 * @see CronTrigger#getName()
	 */
	CronTriggerBuilder name(String name);

	// TODO(rakhmerov): add all methods
}
