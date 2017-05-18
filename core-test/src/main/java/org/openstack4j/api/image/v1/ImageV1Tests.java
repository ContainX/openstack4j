package org.openstack4j.api.image.v1;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.image.CachedImage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by esommar on 5/8/2017.
 */
@Test(suiteName="Image/imagesv1", enabled=true)
public class ImageV1Tests extends AbstractTest {

    private static final String CACHED_IMAGES_JSON = "/image/cachedImages.json";
    private static final String EMPTY_CACHED_IMAGES_JSON = "/image/emptyCachedImages.json";

    public void testListCachedImages() throws IOException, InterruptedException {
        respondWith(CACHED_IMAGES_JSON);
        List<? extends CachedImage> list = osv3().images().listChachedImages();
        assertEquals(list.size(), 2);
        CachedImage first = list.get(0);
        CachedImage second = list.get(1);

        assertEquals(first.getImageId(), "fd3baf5f-4041-44f6-aa67-e8edaff6768a");
        assertEquals(second.getImageId(), "fd3baf5f-4041-44f6-aa67-jdkg78439jf4");

        assertEquals(first.getSize().longValue(), 50901);
        assertEquals(second.getSize().longValue(), 70109);

        assertEquals(first.getHits().intValue(), 0);
        assertEquals(second.getHits().intValue(), 23);

        Date firstDate = new Date((long)Double.parseDouble("1492607597169.914"));
        Date secondDate = new Date((long)Double.parseDouble("1494248723169"));

        assertEquals(first.getLastAccessed().equals(firstDate), true);
        assertEquals(second.getLastAccessed().equals(secondDate), true);

        assertEquals(first.getLastModified().equals(firstDate), true);
        assertEquals(second.getLastModified().equals(firstDate), true);
    }

    public void testEmptyCache() throws IOException {
        respondWith(EMPTY_CACHED_IMAGES_JSON);
        List<? extends CachedImage> list = osv3().images().listChachedImages();
        assertEquals(list.size(), 0);
    }

    public void testCacheNotEnabled() {
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type","text/html");
        respondWith(headers, 404, "");
        List<? extends CachedImage> list = osv3().images().listChachedImages();
        assertNull(list);
    }

    @Override
    protected Service service() {
        return Service.IMAGE;
    }
}
