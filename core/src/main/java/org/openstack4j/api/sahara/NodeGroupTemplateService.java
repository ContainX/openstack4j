package org.openstack4j.api.sahara;

import java.util.List;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.common.RestService;
import org.openstack4j.model.sahara.NodeGroupTemplate;

/**
 * Sahara Data Processing Operations
 * 
 * @author Ekasit Kijsipongse
 */
public interface NodeGroupTemplateService extends RestService {

    /**
     * Lists all node group templates
     * 
     * @return list of node group templates or empty
     */
    List<? extends NodeGroupTemplate> list();

    /**
     * Gets a node group template by ID
     * @param templateId the template identifier
     * @return the node group template or null if not found
     */
     NodeGroupTemplate get(String templateId);

    /**
     * Creates a new node group template
     * 
     * @param template the node group template to create
     * @return the created node group template
     */
    NodeGroupTemplate create(NodeGroupTemplate template);

    /**
     * Deletes the specified node group template
     * 
     * @param templateId the template identifier
     * @return the action response
     */
     ActionResponse delete(String templateId);

}
