package org.openstack4j.model.compute.builder;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;
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
	
	/**
	 * Adds list of networks (by id) to server
	 *
	 * @param networks list of network ids
	 * @return this builder
	 */
	ServerCreateBuilder networks(List<String> networks);
	
	/**
	 * Adds a Personality to the Server.  A personality is a path to a file and the contents to be replaced on the new 
	 * VM.
	 * @param path the path (max is 255 bytes)
	 * @param contents the contents of the file {@code path}
	 * @return this builder
	 */
	ServerCreateBuilder addPersonality(String path, String contents);
	
	/**
	 * Adds the security group.
	 *
	 * @param name the name
	 */
	ServerCreateBuilder addSecurityGroup(String name);
	
	/**
	 * Associates this Server with a public keypair name
	 * @param name the name of the public keypair
	 */
	ServerCreateBuilder keypairName(String name);
        
        ServerCreateBuilder blockDevice(BlockDeviceMappingCreate blockDevice);
	
}
