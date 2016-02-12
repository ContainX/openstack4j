package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.AbsoluteLimit;
import org.openstack4j.model.manila.Limits;
import org.openstack4j.model.manila.RateLimit;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for share services
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="Share")
public class ShareTests extends AbstractTest {
    private static final String JSON_EXTENSIONS = "/manila/extensions.json";
    private static final String JSON_LIMITS = "/manila/limits.json";

    private static final DateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void listExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = os().share().listExtensions();
        assertTrue(extensions.isEmpty());
    }

    @Test
    public void limits() throws Exception {
        respondWith(JSON_LIMITS);
        Limits limits = os().share().limits();

        RateLimit rateLimit = limits.getRate().get(0);
        RateLimit.Limit limit = rateLimit.getLimit().get(0);
        AbsoluteLimit absoluteLimit = limits.getAbsolute();

        // test the rate limit
        assertEquals(rateLimit.getRegex(), "^/shares");
        assertEquals(rateLimit.getUri(), "\"*/shares\"");
        assertEquals(limit.getNextAvailable(), ISO8601.parse("2016-02-11T14:30:06Z"));
        assertEquals(limit.getUnit(), RateLimit.TimeIntervalUnit.MINUTE);
        assertEquals(limit.getVerb(), HttpMethod.POST);
        assertEquals(limit.getRemaining(), 120);
        assertEquals(limit.getValue(), 120);

        // test the absolute limit
        assertEquals(absoluteLimit.getTotalShareNetworksUsed(), 0);
        assertEquals(absoluteLimit.getMaxTotalShareGigabytes(), 1000);
        assertEquals(absoluteLimit.getMaxTotalShareNetworks(), 10);
        assertEquals(absoluteLimit.getTotalSharesUsed(), 0);
        assertEquals(absoluteLimit.getTotalShareGigabytesUsed(), 0);
        assertEquals(absoluteLimit.getTotalShareSnapshotsUsed(), 0);
        assertEquals(absoluteLimit.getMaxTotalShares(), 50);
        assertEquals(absoluteLimit.getTotalSnapshotGigabytesUsed(), 0);
        assertEquals(absoluteLimit.getMaxTotalSnapshotGigabytes(), 1000);
        assertEquals(absoluteLimit.getMaxTotalShareSnapshots(), 50);
    }
}
