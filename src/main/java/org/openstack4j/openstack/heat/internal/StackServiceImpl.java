package org.openstack4j.openstack.heat.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.Builders;
import org.openstack4j.api.heat.StackService;
import org.openstack4j.model.heat.Stack;
import org.openstack4j.model.heat.StackCreate;
import org.openstack4j.openstack.heat.domain.HeatStack;
import org.openstack4j.openstack.heat.domain.HeatStack.Stacks;

/**
 * This class implements all methods for manipulation of {@link HeatStack} objects. The
 * non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html#stacks
 * 
 * @author Matthias Reisser
 * 
 */
public class StackServiceImpl extends BaseHeatServices implements StackService {

	@Override
	public Stack create(StackCreate newStack) {
		checkNotNull(newStack);
		return post(HeatStack.class, uri("/stacks")).entity(newStack).execute();
	}

	@Override
	public Stack create(String name, String template,
			Map<String, String> parameters, String disableRollback,
			Long timeoutMins) {
		checkNotNull(name);
		checkNotNull(template);
		checkNotNull(parameters);
		checkNotNull(disableRollback);
		checkNotNull(timeoutMins);

		return create(Builders.stack().name(name).jsonTemplate(template)
				.parameters(parameters).timeoutMins(timeoutMins).build());
	}

	@Override
	public List<? extends Stack> list() {
		return get(Stacks.class, uri("/stacks")).execute().getList();
	}

	@Override
	public void delete(String stackName, String stackId) {
		checkNotNull(stackId);
		delete(Void.class, uri("/stacks/%s/%s", stackName, stackId)).execute();
	}

	@Override
	public Stack getDetails(String stackName, String stackId) {
		checkNotNull(stackName);
		checkNotNull(stackId);
		return get(HeatStack.class, uri("/stacks/%s/%s", stackName, stackId))
				.execute();
	}


}
