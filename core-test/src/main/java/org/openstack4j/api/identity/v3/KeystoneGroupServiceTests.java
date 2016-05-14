package org.openstack4j.api.identity.v3;

import static org.testng.AssertJUnit.assertNull;
import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.identity.v3.Group;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 GroupService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneGroupServiceTests extends AbstractTest {

    private static final String JSON_GROUPS_CREATE = "/identity/v3/groups_create_response.json";
    private static final String JSON_GROUPS_GET_BYID = "/identity/v3/groups_get_byId.json";
    private static final String JSON_GROUPS_UPDATE = "/identity/v3/groups_update_response.json";
    private static final String JSON_GROUPS_EMPTY_LIST = "/identity/v3/groups_getByName_empty.json";
    private static final String GROUP_NAME = "GROUP_CRUD";
    private static final String GROUP_DOMAIN_ID = "default";
    private static final String GROUP_DESCRIPTION = "Group used for CRUD tests";
    private static final String GROUP_DESCRIPTION_UPDATE = "An updated group used for CRUD tests";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Group Tests ------------

    // The following tests are to verify the update() method of the GroupService
    // using HTTP PATCH, which is not supported by betamax.
    // Find more tests in KeystoneGroupServiceSpec in core-integration-test
    // module.

    public void group_crud_test() throws Exception {

        Group group = Builders.group()
                .name(GROUP_NAME)
                .description(GROUP_DESCRIPTION)
                .domainId(GROUP_DOMAIN_ID)
                .build();

        respondWith(JSON_GROUPS_CREATE);

        Group newGroup = osv3().identity().groups().create(group);

        assertEquals(newGroup.getName(), GROUP_NAME);
        assertEquals(newGroup.getDomainId(), GROUP_DOMAIN_ID);
        assertEquals(newGroup.getDescription(), GROUP_DESCRIPTION);

        String GROUP_ID = newGroup.getId();

        respondWith(JSON_GROUPS_GET_BYID);

        Group group_setToUpdate = osv3().identity().groups().get(GROUP_ID);

        respondWith(JSON_GROUPS_UPDATE);

        Group updatedGroup = osv3.identity().groups().update(group_setToUpdate.toBuilder().description(GROUP_DESCRIPTION_UPDATE).build());

        assertEquals(updatedGroup.getId(), GROUP_ID);
        assertEquals(updatedGroup.getName(), GROUP_NAME);
        assertEquals(updatedGroup.getDomainId(), GROUP_DOMAIN_ID);
        assertEquals(updatedGroup.getDescription(), GROUP_DESCRIPTION_UPDATE);

    }
    
    
    public void group_get_byName_byDomainId_NotExist_Test() throws Exception {    	
    	respondWith(JSON_GROUPS_EMPTY_LIST);    
    	Group group = osv3().identity().groups().getByName(GROUP_NAME, GROUP_DOMAIN_ID);    	
    	assertNull(group);
    	
    }

}
