package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.cloudkitty.DataFrame;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/storage", enabled = true)
public class StorageServiceTests extends AbstractTest {

    public final String JSON_STORAGE_DATAFRAMES = "/cloudkitty/storage-dataframes.json";

    public void testDataFrames() throws IOException {
        respondWith(JSON_STORAGE_DATAFRAMES);

        List<? extends DataFrame> list = osv3().rating().storage().dataFrames(
                null, null, null, null
        );

        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getResources().size(), 1);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
