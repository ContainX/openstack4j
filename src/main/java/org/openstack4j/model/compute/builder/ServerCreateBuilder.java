package org.openstack4j.model.compute.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.ServerCreate;

public interface ServerCreateBuilder extends Buildable.Builder<ServerCreateBuilder, ServerCreate>{

	ServerCreateBuilder name(String name);
	
	ServerCreateBuilder flavor(String flavorId);
	
	ServerCreateBuilder flavor(Flavor flavor);
	
	ServerCreateBuilder image(String imageId);
	
	ServerCreateBuilder image(Image image);
	
}
