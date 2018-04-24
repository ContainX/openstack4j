package org.openstack4j.api.image.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.Task;
import org.testng.annotations.Test;

/**
 * @author emjburns
 */
@Test(suiteName="Image/imagesv2", enabled=true)
public class ImageV2Tests extends AbstractTest {
    private static final String IMAGES_JSON = "/image/v2/images.json";
    private static final String IMAGE_JSON = "/image/v2/image.json";
    private static final String IMAGE_WIHT_LOCATION_JSON = "/image/v2/image-with-locations.json";
    private static final String IMAGE_UPDATE_JSON = "/image/v2/image-update.json";
    private static final String MEMBER_JSON = "/image/v2/member.json";
    private static final String MEMBER_UPDATE_JSON = "/image/v2/member-update.json";
    private static final String MEMBERS_JSON = "/image/v2/members.json";
    private static final String TASK_JSON = "/image/v2/task.json";
    private static final String TASKS_JSON = "/image/v2/tasks.json";
    private static final String TASKS_FILTERED_JSON = "/image/v2/tasks-filtered.json";
    private static final String BINARY_IMAGE_DATA =
            "943c 7b3c 3ef4 eac8 e906 b220 1efb f01f\n" +
            "00b4 5b1b b4fa 0707 c2ac 378b e722 514d\n" +
            "5fb9 e9a0 7f9f fa4c 645d 113c 0524 b380\n" +
            "acee 6344 1f45 b58b 1eb2 8776 3e9b 9aef";

    public void testListImages() throws IOException {
        respondWith(IMAGES_JSON);
        List<? extends Image> list = osv3().imagesV2().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "7541b8be-c62b-46c3-b5a5-5bb5ce43ce01");
    }

    public void testListImagesFilter() throws IOException {
        respondWith(IMAGES_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("container_format", "bare");
        List<? extends Image> list = osv3().imagesV2().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetImage() throws IOException {
        respondWith(IMAGE_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        Image image = osv3().imagesV2().get(id);
        assertNotNull(image);
        assertNotNull(image.getId());
        assertEquals(image.getId(),id);
    }
    
    public void testGetImageWithLocations() throws IOException {
        respondWith(IMAGE_WIHT_LOCATION_JSON);
        String id = "c73056d6-c583-4d6c-9f70-04f3bfd8dff4";
        Image image = osv3().imagesV2().get(id);
        assertNotNull(image);
        assertNotNull(image.getId());
        assertEquals(image.getId(),id);
        assertEquals(2,image.getLocations().size());
    }

    public void testCreateImage() throws IOException {
        respondWith(IMAGE_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        String name = "amphora-x64-haproxy";
        ContainerFormat cf = ContainerFormat.BARE;
        DiskFormat df = DiskFormat.QCOW2;
        Long mindisk = 0L;
        Long minram = 0L;
        Image.ImageVisibility vis = Image.ImageVisibility.PUBLIC;
        String key1 = "test-key1";
        String key2 = "test-key2";
        String key3 = "id";
        String value1 = "test-value1";
        String value2 = "test-value2";
        String value3 = "test-value3";
        Image im = Builders.imageV2()
                .id(id)
                .name(name)
                .containerFormat(cf)
                .diskFormat(df)
                .minDisk(mindisk)
                .minRam(minram)
                .visibility(vis)
                .additionalProperty(key1, value1)
                .additionalProperty(key2, value2)
                .additionalProperty(key3, value3)
                .build();
        Image image = osv3().imagesV2().create(im);
        assertNotNull(image);
        assertEquals(image.getId(), id);
        assertEquals(image.getName(), name);
        assertEquals(image.getContainerFormat(), cf);
        assertEquals(image.getDiskFormat(), df);
        assertEquals(image.getVisibility(), vis);
        assertEquals(image.getMinDisk(), mindisk);
        assertEquals(image.getMinRam(), minram);
        assertEquals(image.getAdditionalPropertyValue(key1), value1);
        assertEquals(image.getAdditionalPropertyValue(key2), value2);
        assertNull(image.getAdditionalPropertyValue(key3));
    }

    public void testDeleteImage() throws IOException {
        respondWith(204);
        ActionResponse delete = osv3().imagesV2().delete("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(delete.isSuccess());
    }

    public void testDeactivateImage() throws IOException {
        respondWith(204);
        ActionResponse deactivate = osv3().imagesV2().deactivate("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(deactivate.isSuccess());
    }

    public void testReactivateImage() throws IOException {
        respondWith(204);
        ActionResponse reactivate = osv3().imagesV2().reactivate("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(reactivate.isSuccess());
    }

    public void testCreateMember() throws IOException {
        respondWith(MEMBER_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member member = osv3().imagesV2().createMember(imageId, memberId);
        assertEquals(member.getStatus(), Member.MemberStatus.PENDING);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);

    }

    public void testGetMember() throws IOException {
        respondWith(MEMBER_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member member = osv3().imagesV2().getMember(imageId, memberId);
        assertEquals(member.getStatus(), Member.MemberStatus.PENDING);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);
    }

    public void testListMembers() throws IOException {
        respondWith(MEMBERS_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        List<? extends Member> members = osv3().imagesV2().listMembers(imageId);
        assertNotNull(members);

        Member member = members.get(0);
        assertEquals(member.getImageId(), "4b434528-032b-4467-946c-b5880ce15c06");
        assertEquals(member.getMemberId(), "66cabdfb14bd48d48402f7464bda7733");
    }

    public void testUpdateMember() throws IOException {
        respondWith(MEMBER_UPDATE_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member.MemberStatus ms = Member.MemberStatus.ACCEPTED;
        Member member = osv3().imagesV2().updateMember(imageId, memberId, ms);
        assertNotNull(member);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);
        assertEquals(member.getStatus(), ms);
    }

    public void testDeleteMember() throws IOException {
        respondWith(204);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        ActionResponse deleteMember = osv3().imagesV2().deleteMember(imageId, memberId);
        assertTrue(deleteMember.isSuccess());
    }

    public void testUpdateImageTag() throws IOException {
        respondWith(204);
        String tag = "tag1";
        String imageId = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        ActionResponse ur = osv3().imagesV2().updateTag(imageId, tag);
        assertTrue(ur.isSuccess());
    }

    public void testDeleteImageTag() throws IOException {
        respondWith(204);
        String tag = "tag1";
        String imageId = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        ActionResponse deleteTag = osv3().imagesV2().deleteTag(imageId, tag);
        assertTrue(deleteTag.isSuccess());
    }

    public void getTask() throws IOException {
        respondWith(TASK_JSON);
        String id = "78925244-2951-462d-b979-773a49274d7f";
        Task task = osv3().imagesV2().tasks().get(id);
        assertNotNull(task);
        assertEquals(task.getId(), id);
        assertEquals(task.getType(), "import");
    }

    public void createTask() throws IOException {
        respondWith(TASK_JSON);
        Map<String, Object> input = new HashMap<>();
        input.put("test", "hi");
        String type = "import";
        Task t = Builders.taskBuilder().type(type).input(input).build();
        Task task = osv3().imagesV2().tasks().create(t);
        assertNotNull(task);
        assertEquals(task.getType(), type);
    }

    public void listTasks() throws IOException {
        respondWith(TASKS_JSON);
        List<? extends Task> list = osv3().imagesV2().tasks().list();
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    public void listTaskWithParams() throws IOException {
        respondWith(TASKS_FILTERED_JSON);
        String id = "78925244-2951-462d-b979-773a49274d7f";
        Map<String,String> params = new HashMap<>();
        params.put("id", id);
        List<? extends Task> list = osv3().imagesV2().tasks().list(params);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        assertEquals(list.get(0).getId(), id);
    }

    public void UploadImage() throws IOException {
        respondWith(204);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        InputStream s = new ByteArrayInputStream(BINARY_IMAGE_DATA.getBytes(StandardCharsets.UTF_8));
        Payload<InputStream> payload = Payloads.create(s);
        ActionResponse upload  = osv3().imagesV2().upload(imageId, payload, null);
        assertTrue(upload.isSuccess());
    }

     public void DownloadImage() throws IOException {
         respondWith(200);
         String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
         URI uri = null;
         try {
             uri = new URI("file:////test.iso");
         }catch (URISyntaxException e) {
             e.printStackTrace();
         }
         File file = new File(uri);
         ActionResponse download = osv3().imagesV2().download(imageId, file);
         // Should fail to write to file
         assertEquals(download.getCode(), 400);
     }

    @Override
    protected Service service() {
        return Service.IMAGE;
    }
}
