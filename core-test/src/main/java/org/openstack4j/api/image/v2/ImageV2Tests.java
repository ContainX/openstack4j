package org.openstack4j.api.image.v2;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author emjburns
 */
@Test(suiteName="Image/imagesv2", enabled=true)
public class ImageV2Tests extends AbstractTest {
    private static final String IMAGESV2_JSON = "/image/v2/images.json";
    private static final String IMAGEV2_JSON = "/image/v2/image.json";

    public void testListImages() throws IOException {
        respondWith(IMAGESV2_JSON);
        List<? extends Image> list = osv3().imagesV2().images().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "7541b8be-c62b-46c3-b5a5-5bb5ce43ce01");
    }

    public void testListImagesFilter() throws IOException {
        respondWith(IMAGESV2_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("container_format", "bare");
        List<? extends Image> list = osv3().imagesV2().images().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetImage() throws IOException {
        respondWith(IMAGEV2_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        Image image = osv3().imagesV2().images().get(id);
        assertNotNull(image);
        assertNotNull(image.getId());
        assertEquals(image.getId(),id);
    }

    public void testCreateImage() throws IOException {
        respondWith(IMAGEV2_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        String name = "amphora-x64-haproxy";
        ContainerFormat cf = ContainerFormat.BARE;
        DiskFormat df = DiskFormat.QCOW2;
        Integer mindisk = 0;
        Integer minram = 0;
        String vis = "public";
        Image im = Builders.imageBuilder()
                .id(id)
                .name(name)
                .containerFormat(cf)
                .diskFormat(df)
                .minDisk(mindisk)
                .minRam(minram)
                .visibility(vis)
                .build();
        Image image = osv3().imagesV2().images().create(im);
        assertNotNull(image);
        assertEquals(image.getId(), id);
        assertEquals(image.getName(), name);
        assertEquals(image.getContainerFormat(), cf);
        assertEquals(image.getDiskFormat(), df);
        assertEquals(image.getVisibility(), vis);
        assertEquals(image.getMinDisk(), mindisk);
        assertEquals(image.getMinRam(), minram);
    }

    public void testDeleteImage() throws IOException {
        respondWith(204);
        ActionResponse delete = osv3().imagesV2().images().delete("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(delete.isSuccess());
    }

    public void testDeactivateImage() throws IOException {
        respondWith(204);
        ActionResponse deactivate = osv3().imagesV2().images().deactivate("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(deactivate.isSuccess());
    }

    public void testReactivateImage() throws IOException {
        respondWith(204);
        ActionResponse reactivate = osv3().imagesV2().images().reactivate("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(reactivate.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.IMAGE;
    }
}
