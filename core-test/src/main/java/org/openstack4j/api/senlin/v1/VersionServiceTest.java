package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.senlin.Version;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * Test cases for version on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/version")
public class VersionServiceTest extends AbstractTest {

    private static final String VERSIONS="/senlin/version.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListVersion() throws Exception{
        respondWith(VERSIONS);
        List<? extends Version> versionList = osv3().senlin().version().list();
        assertEquals(1, versionList.size());
        Preconditions.checkNotNull(versionList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Version from List : " + versionList.get(0));
        assertEquals(versionList.get(0).getId(), "v1.0");
    }

}
