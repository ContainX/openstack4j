package org.openstack4j.api.workflow;

import org.openstack4j.api.AbstractTest;

import java.util.UUID;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Base class for all Workflow Service tests.
 *
 * @author Renat Akhmerov
 */
public class WorkflowBaseTest extends AbstractTest {

    static void assertIsUUID(String value) {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new AssertionError(e);
        }
    }

    static void assertNotEmptyString(String value) {
        assertNotNull(value);
        assertTrue(value.length() > 0);
    }

    @Override
    protected Service service() {
        return Service.WORKFLOW;
    }
}
