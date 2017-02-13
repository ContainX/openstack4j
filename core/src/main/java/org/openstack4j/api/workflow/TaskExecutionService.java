package org.openstack4j.api.workflow;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.TaskExecution;

import java.util.List;

/**
 * Service that provides CRUD operations for task executions.
 *
 * @author Renat Akhmerov
 */
public interface TaskExecutionService extends RestService {
    /**
     * List all task executions with details.
     *
     * @return List of task executions.
     */
    List<? extends TaskExecution> list();

    /**
     * Create a new task execution.
     *
     * @param taskExecution Task execution to create.
     * @return Created task execution.
     */
    TaskExecution create(TaskExecution taskExecution);

    /**
     * Get task execution by its ID.
     *
     * @param id Task execution ID.
     * @return Task execution.
     */
    TaskExecution get(String id);

    /**
     * Delete task execution by its ID.
     *
     * @param id Task execution ID.
     * @return Action response from the server.
     */
    ActionResponse delete(String id);
}
