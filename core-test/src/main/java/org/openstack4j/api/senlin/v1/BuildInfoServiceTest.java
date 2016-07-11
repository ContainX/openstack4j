package org.openstack4j.api.senlin.v1;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.senlin.BuildInfo;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertNotNull;

/**
 * Test cases for buildInfo on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/buildInfo")
public class BuildInfoServiceTest extends AbstractTest {

    private static final String BUILDINFO="/senlin/v1/build_info.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testGetAction() throws Exception{
        respondWith(BUILDINFO);
        BuildInfo buildInfo = osv3().senlin().buildInfo().get();
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : BuildInfo : "+ buildInfo);
        assertNotNull(buildInfo);
    }

}
