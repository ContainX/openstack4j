package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.senlin.ProfileType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for profileType on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/profileType")
public class ProfileTypeServiceTest extends AbstractTest {

    private static final String PROFILETYPES="/senlin/v1/profile_types.json";
    private static final String PROFILETYPE="/senlin/v1/profile_type.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListProfileType() throws Exception{
        respondWith(PROFILETYPES);
        List<? extends ProfileType> profileTypeList = osv3().senlin().profileType().list();
        assertEquals(3, profileTypeList.size());
        Preconditions.checkNotNull(profileTypeList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : ProfileType from List : "+ profileTypeList.get(0));
        assertEquals(profileTypeList.get(0).getName(), "os.heat.stack");
    }
    @Test
    public void testGetProfileType() throws Exception{
        respondWith(PROFILETYPE);
        ProfileType profileType = osv3().senlin().profileType().get("os.heat.stack");
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : ProfileType by name : "+ profileType);
        assertNotNull(profileType);
        assertEquals("os.heat.stack", profileType.getName());
    }

}
