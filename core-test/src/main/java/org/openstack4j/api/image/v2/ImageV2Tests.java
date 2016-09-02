package org.openstack4j.api.image.v2;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.MemberCreate;
import org.openstack4j.model.image.v2.MemberUpdate;
import org.openstack4j.model.image.v2.Task;
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
    private static final String IMAGES_JSON = "/image/v2/images.json";
    private static final String IMAGE_JSON = "/image/v2/image.json";
    private static final String IMAGE_UPDATE_JSON = "/image/v2/image-update.json";
    private static final String MEMBER_JSON = "/image/v2/member.json";
    private static final String MEMBER_UPDATE_JSON = "/image/v2/member-update.json";
    private static final String MEMBERS_JSON = "/image/v2/members.json";
    private static final String TASK_JSON = "/image/v2/task.json";
    private static final String TASKS_JSON = "/image/v2/tasks.json";
    private static final String TASKS_FILTERED_JSON = "/image/v2/tasks-filtered.json";

    public void testListImages() throws IOException {
        respondWith(IMAGES_JSON);
        List<? extends Image> list = osv3().imagesV2().images().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "7541b8be-c62b-46c3-b5a5-5bb5ce43ce01");
    }

    public void testListImagesFilter() throws IOException {
        respondWith(IMAGES_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("container_format", "bare");
        List<? extends Image> list = osv3().imagesV2().images().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetImage() throws IOException {
        respondWith(IMAGE_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        Image image = osv3().imagesV2().images().get(id);
        assertNotNull(image);
        assertNotNull(image.getId());
        assertEquals(image.getId(),id);
    }

    public void testCreateImage() throws IOException {
        respondWith(IMAGE_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        String name = "amphora-x64-haproxy";
        ContainerFormat cf = ContainerFormat.BARE;
        DiskFormat df = DiskFormat.QCOW2;
        Integer mindisk = 0;
        Integer minram = 0;
        Image.ImageVisibility vis = Image.ImageVisibility.PUBLIC;
        Image im = Builders.imageV2()
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

    public void testCreateMember() throws IOException {
        respondWith(MEMBER_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        MemberCreate mc = Builders.imageMemberV2().member(memberId).build();
        Member member = osv3().imagesV2().images().createMember(imageId, mc);
        assertEquals(member.getStatus(), Member.MemberStatus.PENDING);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);

    }

    public void testGetMember() throws IOException {
        respondWith(MEMBER_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member member = osv3().imagesV2().images().getMember(imageId, memberId);
        assertEquals(member.getStatus(), Member.MemberStatus.PENDING);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);
    }

    public void testListMembers() throws IOException {
        respondWith(MEMBERS_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        List<? extends Member> members = osv3().imagesV2().images().listMembers(imageId);
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
        MemberUpdate memberUpdate = Builders.imageMemberUpdateV2().status(ms).build();
        Member member = osv3().imagesV2().images().updateMember(imageId, memberId, memberUpdate);
        assertNotNull(member);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);
        assertEquals(member.getStatus(), ms);
    }

    public void testDeleteMember() throws IOException {
        respondWith(204);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        ActionResponse deleteMember = osv3().imagesV2().images().deleteMember(imageId, memberId);
        assertTrue(deleteMember.isSuccess());
    }

    public void testUpdateImageTag() throws IOException {
        respondWith(204);
        String tag = "tag1";
        String imageId = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        ActionResponse ur = osv3().imagesV2().images().updateTag(imageId, tag);
        assertTrue(ur.isSuccess());
    }

    public void testDeleteImageTag() throws IOException {
        respondWith(204);
        String tag = "tag1";
        String imageId = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        ActionResponse deleteTag = osv3().imagesV2().images().deleteTag(imageId, tag);
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

    @Override
    protected Service service() {
        return Service.IMAGE;
    }
}
