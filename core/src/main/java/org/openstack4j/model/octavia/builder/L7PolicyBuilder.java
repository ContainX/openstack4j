package org.openstack4j.model.octavia.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.octavia.L7Policy;

public interface L7PolicyBuilder extends Buildable.Builder<L7PolicyBuilder, L7Policy> {

	L7PolicyBuilder action(String action);

	L7PolicyBuilder adminStateUp(boolean adminStateUp);

	L7PolicyBuilder description(String description);

	L7PolicyBuilder listenerId(String listenerId);

	L7PolicyBuilder position(int position);

	L7PolicyBuilder projectId(String projectId);

	L7PolicyBuilder redirectPoolId (String redirectPoolId );

	L7PolicyBuilder redirectUrl (String redirectUrl );
	
	L7PolicyBuilder name (String name );
}
