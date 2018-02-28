package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.Resource;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/rating", enabled = true)
public class RatingServiceTests extends AbstractTest {

    private final String RATING_QUOTE = "/cloudkitty/rating-quote.txt";

    private final float quote = 1.12f;

    public void testRatingQuote() throws IOException {
        respondWith(RATING_QUOTE);

        List<Resource> resources = new ArrayList<>();
        resources.add(Builders.cloudkitty().resource()
                .description(new HashMap<String, String>())
                .service("service_id")
                .volume(1)
                .build()
        );

        float result = osv3().rating().quote(resources);

        assertEquals(result, quote);
    }

    public void testReloadModules() throws IOException {
        respondWith(204);

        ActionResponse response = osv3().rating().reload();

        assertTrue(response.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
