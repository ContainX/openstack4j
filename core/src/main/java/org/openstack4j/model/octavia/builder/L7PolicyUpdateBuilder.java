package org.openstack4j.model.octavia.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.octavia.L7PolicyUpdate;

public interface L7PolicyUpdateBuilder extends Buildable.Builder<L7PolicyUpdateBuilder, L7PolicyUpdate> {
	L7PolicyUpdateBuilder action(String action);

	L7PolicyUpdateBuilder adminStateUp(boolean adminStateUp);

	L7PolicyUpdateBuilder description(String description);
	
	L7PolicyUpdateBuilder name(String name);

	L7PolicyUpdateBuilder position(int position);

	L7PolicyUpdateBuilder redirectPoolId (String redirectPoolId );

	L7PolicyUpdateBuilder redirectUrl (String redirectUrl );
}
