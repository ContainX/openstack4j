package org.openstack4j.openstack.heat.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.heat.ResourcesService;
import org.openstack4j.model.heat.Resource;
import org.openstack4j.openstack.heat.domain.HeatResource;
import org.openstack4j.openstack.heat.domain.HeatResource.Resources;

/**
 * This class implements some methods for manipulation of {@link HeatResources} objects. The
 * non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author Octopus Zhang
 * 
 */
public class ResourcesServiceImpl extends BaseHeatServices implements ResourcesService {

	@Override
	public List<? extends Resource> list(String stackName, String stackId) {
	    checkNotNull(stackName);
        checkNotNull(stackId);
		return get(Resources.class, uri("/stacks/%s/%s/resources", stackName, stackId)).execute().getList();
	}
	
	@Override
	public Resource show(String stackName, String stackId ,String resourceName) {
	    checkNotNull(stackName);
        checkNotNull(stackId);
        checkNotNull(resourceName);
		return get(HeatResource.class, uri("/stacks/%s/%s/resources/%s", stackName, stackId, resourceName)).execute();
	}
}
