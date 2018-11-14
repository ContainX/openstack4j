package org.openstack4j.api.compute.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.InterfaceAttachment;

/**
 * API to Create, list, get details for, and delete port interfaces on a Server Instance
 * 
 * @author Jeremy Unruh
 */
public interface InterfaceService extends RestService {

    /**
     * Creates and uses a port interface to attach the port to a server instance.
     * 
     * @param serverId the server id
     * @param portId the port id to attach
     * @return the attached interface
     */
    InterfaceAttachment create(String serverId, String portId);

    /**
     * Creates a port interface and uses it to attach a port to a server.
     * @param serverId：instance id
     * @param portId： The ID of the port for which you want to create an interface. The net_id and port_id parameters are mutually exclusive. If you do not specify the port_id parameter, the OpenStack Networking API v2.0 allocates a port and creates an interface for it on the network.
     * @param netId：The ID of the network for which you want to create a port interface. The net_id and port_id parameters are mutually exclusive. If you do not specify the net_id parameter, the OpenStack Networking API v2.0 uses the network information cache that is associated with the instance.
     * @return
     */
    InterfaceAttachment create(String serverId, String portId, String netId);
    
    /**
     * List the port interfaces for the specified {@code serverId}
     * @param serverId the server id
     * @return List of interface attachments
     */
    List<? extends InterfaceAttachment> list(String serverId);
    
    /**
     * Shows information about a specified port interface
     * 
     * @param serverId the server id
     * @param attachmentId the attachment identifier
     * @return the interface attachment
     */
    InterfaceAttachment get(String serverId, String attachmentId);
    
    /**
     * Detaches a specified port interface
     * 
     * @param serverId the server id
     * @param attachmentId the attachment identifier
     * @return the action response indicating success or failure
     */
    ActionResponse detach(String serverId, String attachmentId);
    
}
