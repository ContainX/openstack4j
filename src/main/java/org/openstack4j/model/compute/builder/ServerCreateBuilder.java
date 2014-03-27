package org.openstack4j.model.compute.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;

/**
 * Builds a Server used for a Create Action
 * @author Jeremy Unruh
 */
public interface ServerCreateBuilder extends Buildable.Builder<ServerCreateBuilder, ServerCreate>{

	/**
	 * @see Server#getName()
	 */
	ServerCreateBuilder name(String name);
	
	/**
	 * @see Server#getFlavor()
	 */
	ServerCreateBuilder flavor(String flavorId);
	
	/**
	 * @see Server#getFlavor()
	 */
	ServerCreateBuilder flavor(Flavor flavor);
	
	/**
	 * @see Server#getImage()
	 */
	ServerCreateBuilder image(String imageId);
	
	/**
	 * @see Server#getImage()
	 */
	ServerCreateBuilder image(Image image);
	
}
