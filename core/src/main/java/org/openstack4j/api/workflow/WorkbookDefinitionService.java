package org.openstack4j.api.workflow;

import org.openstack4j.common.RestService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.workflow.WorkbookDefinition;

import java.util.List;

/**
 * Service that provides CRUD operations for workbook definitions.
 * 
 * @author Renat Akhmerov
 */
public interface WorkbookDefinitionService extends RestService {
    /**
     * List all workbook definitions with details.
     *
     * @return List of workbook definitions.
     */
    List<? extends WorkbookDefinition> list();

    /**
     * Create a new workbook definition.
     *
     * @param workbookDefinition Workbook definition to create.
     * @return Created workbook definition.
     */
    WorkbookDefinition create(WorkbookDefinition workbookDefinition);

    /**
     * Get workbook definition by its identifier.
     *
     * @param identifier Workbook definition identifier (either ID or name).
     * @return Workbook definition.
     */
    WorkbookDefinition get(String identifier);

    /**
     * Delete workbook definition by its identifier.
     *
     * @param identifier Workbook definition identifier (either ID or name).
     * @return HTTP response from the server.
     */
    HttpResponse delete(String identifier);
}
