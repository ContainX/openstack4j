package org.openstack4j.api.compute;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.RebootType;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.compute.VNCConsole.Type;
import org.openstack4j.model.compute.VolumeAttachment;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;

/**
 * Server Operations API
 * 
 * @author Jeremy Unruh
 */
public interface ServerService {

	/**
	 * List all servers (detailed) that the current tenant has access to
	 *
	 * @return list of all servers
	 */
	List<? extends Server> list();
	
	/**
	 * List all servers (detailed / brief) that the current tenant has access to
	 *
	 * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
	 * @return list of all servers
	 */
	List<? extends Server> list(boolean detail);
	
	/**
	 * List all servers for all tenants (detailed / brief) 
	 *
	 * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
	 * @return list of all servers
	 */
	List<? extends Server> listAll(boolean detail);
	
	/**
	 * Get the specified server by ID
	 *
	 * @param serverId the server id
	 * @return the server or null if not found
	 */
	Server get(String serverId);
	
	/**
	 * Create (boot) a new Server
	 *
	 * @param server the server to boot
	 * @return the newly created server
	 */
	Server boot(ServerCreate server);
	
	/**
	 * Create (boot) a new Server
	 *
	 * @param server the server to boot
	 * @param maxWaitTime the max time to wait in milliseconds for the server to become ACTIVE
	 * @return the newly created server
	 */
	Server bootAndWaitActive(ServerCreate server, int maxWaitTime);
	
	/**
	 * Delete (i.e shut down and delete the image) of the server
	 * @param serverId the server identifier
	 */
	void delete(String serverId);
	
	/**
	 * Executes the specified Action such as RESUME, PAUSE, START, STOP ... see (@link {@link Action} for
	 * all possible actions
	 *
	 * @param serverId the server identifier to execute the action against
	 * @param action the action the specified action
	 * @return the action response
	 */
	ActionResponse action(String serverId, Action action);
	
	/**
	 * Reboot a server by SOFT (software-level) or HARD (hardware power cycle)
	 *
	 * @param serverId the server id
	 * @param type the type of reboot
	 * @return the action response
	 */
	ActionResponse reboot(String serverId, RebootType type);
	
	/**
	 * Resize a server's resources.  Until a resize event is confirmed {@link #confirmResize(String)}, the old server
	 * will be kept around and you'll be able to roll back to the old flavor quick with {@link #revertResize(String)}.  All resizes
	 * will be automatically confirmed after 24 hours.
	 * 
	 * @param serverId the server identifier
	 * @param flavorId the new flavor id to resize to
	 * @return the action response
	 */
	ActionResponse resize(String serverId, String flavorId);

	/**
	 * Confirm that the resize worked, thus removing the original server
	 * 
	 * @param serverId the server identifier
	 * @return the action response
	 */
	ActionResponse confirmResize(String serverId);
	
	/**
	 * Revert a previous resize, switching back to the old server
	 * 
	 * @param serverId the server identifier
	 * @return the action response
	 */
	ActionResponse revertResize(String serverId);
	
	/**
	 * Creates the snapshot for a Server
	 *
	 * @param serverId the server id
	 * @param snapshotName the snapshot name
	 * @return the newly created snapshot UUID
	 */
	String createSnapshot(String serverId, String snapshotName);
	
	/**
	 * Associates the specified Server Group by name to the Server by it's identifier
	 * 
	 * @param serverId the server identifier
	 * @param secGroupName the security group name
	 * @return the action response
	 */
	ActionResponse addSecurityGroup(String serverId, String secGroupName);
	
	/**
	 * Removes the specified Server Group by name from the Server by it's identifier
	 * 
	 * @param serverId the server identifier
	 * @param secGroupName the security group name
	 * @return the action response
	 */
	ActionResponse removeSecurityGroup(String serverId, String secGroupName);
	
	/**
	 * Gets usage information about the server.  Usage includes CPU, Memory, IO.  Information
	 * is dependent on the hypervisor used by the OpenStack installation and whether that hypervisor
	 * supports diagnostics
	 *
	 * @param serverId the server id
	 * @return Map of collected usage statistics organized by key and value
	 */
	Map<String, ? extends Number> diagnostics(String serverId);
	
	/**
	 * @return a builder to create a ServerCreate
	 */
	ServerCreateBuilder serverBuilder();
	
	/**
	 * Will attempt to tail and return the last {@code numLines} from the given servers console.
	 * @param serverId the server identifier
	 * @param numLines the number of console lines to return
	 * @return console output as string or null
	 */
	String getConsoleOutput(String serverId, int numLines);
	
	/**
	 * Obtains the VNC Console connection information for the given server and VNC Console Type
	 * 
	 * @param serverId the server identifier
	 * @param type the VNC Console type
	 * @return VNCConsole or null if not applicable
	 */
	VNCConsole getVNCConsole(String serverId, Type type);
	/**
	 * attach the volume  to the given server 
	 * 
	 * @param serverId the server identifier
	 * @param volumeId the volume identifier
	 * @author octopus zhang
	 * @return volumeAttachment or null if not applicable
	 */
	VolumeAttachment attachVolume(String serverId,String volumeId);
	/**
	 * detach the volume to the given server
	 * @param serverId the server identifier
	 * @param attachmentId the attachment identifier
	 * @author octopus zhang
	 */
	void detachVolume(String serverId,String attachmentId);
}
