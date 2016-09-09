package org.openstack4j.api.manila;

import com.beust.jcommander.internal.Maps;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.ExtraSpecs;
import org.openstack4j.model.manila.ShareType;
import org.openstack4j.model.manila.ShareTypeAccess;
import org.openstack4j.model.manila.ShareTypeCreate;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Test cases for share type services
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="ShareType")
public class ShareTypeTests extends AbstractTest {
    private static final String JSON_EXTRA_SPECS_SET = "/manila/extra_specs_set.json";
    private static final String JSON_EXTRA_SPECS = "/manila/extra_specs.json";
    private static final String JSON_SHARE_TYPE_ACCESS_DETAILS = "/manila/share_type_access_details.json";
    private static final String JSON_SHARE_TYPE_CREATE = "/manila/share_type_create.json";
    private static final String JSON_SHARE_TYPES = "/manila/share_types.json";
    private static final String JSON_SHARE_TYPES_DEFAULT = "/manila/share_types_default.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_SHARE_TYPE_CREATE);

        ShareTypeCreate shareTypeCreate = Builders.shareType()
                .osShareTypeAccessIsPublic(true)
                .addExtraSpec("driver_handles_share_servers", "True")
                .addExtraSpec("snapshot_support", "True")
                .name("my_new_volume_type")
                .build();

        ShareType shareType = osv3().share().shareTypes().create(shareTypeCreate);
        
        assertTrue(shareType.getOsShareTypeAccessIsPublic());
        assertEquals(shareType.getRequiredExtraSpecs().get("driver_handles_share_servers"), "true");
        assertEquals(shareType.getExtraSpecs().get("snapshot_support"), "True");
        assertEquals(shareType.getExtraSpecs().get("driver_handles_share_servers"), "True");
        assertEquals(shareType.getName(), "my_new_volume_type");
        assertEquals(shareType.getId(), "1d600d02-26a7-4b23-af3d-7d51860fe858");
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SHARE_TYPES);

        List<? extends ShareType> shareTypes = osv3().share().shareTypes().list();
        assertEquals(shareTypes.size(), 2);

        ShareType shareType1 = shareTypes.get(0);
        ShareType shareType2 = shareTypes.get(1);

        assertTrue(shareType1.getOsShareTypeAccessIsPublic());
        assertEquals(shareType1.getRequiredExtraSpecs().get("driver_handles_share_servers"), "True");
        assertEquals(shareType1.getExtraSpecs().get("snapshot_support"), "True");
        assertEquals(shareType1.getExtraSpecs().get("driver_handles_share_servers"), "True");
        assertEquals(shareType1.getName(), "default");
        assertEquals(shareType1.getId(), "be27425c-f807-4500-a056-d00721db45cf");

        assertTrue(shareType2.getOsShareTypeAccessIsPublic());
        assertEquals(shareType2.getRequiredExtraSpecs().get("driver_handles_share_servers"), "false");
        assertEquals(shareType2.getExtraSpecs().get("snapshot_support"), "True");
        assertEquals(shareType2.getExtraSpecs().get("driver_handles_share_servers"), "false");
        assertEquals(shareType2.getName(), "d");
        assertEquals(shareType2.getId(), "f015bebe-c38b-4c49-8832-00143b10253b");
    }

    @Test
    public void listDefault() throws Exception {
        respondWith(JSON_SHARE_TYPES_DEFAULT);

        ShareType shareType = osv3().share().shareTypes().listDefaults();

        assertNull(shareType.getOsShareTypeAccessIsPublic());
        assertNull(shareType.getRequiredExtraSpecs());
        assertEquals(shareType.getExtraSpecs().get("snapshot_support"), "True");
        assertEquals(shareType.getExtraSpecs().get("driver_handles_share_servers"), "True");
        assertEquals(shareType.getName(), "default");
        assertEquals(shareType.getId(), "be27425c-f807-4500-a056-d00721db45cf");
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareTypes().delete("be27425c-f807-4500-a056-d00721db45cf");
        assertTrue(response.isSuccess());
    }

    @Test
    public void listExtraSpecs() throws Exception {
        respondWith(JSON_EXTRA_SPECS);

        ExtraSpecs extraSpecs = osv3().share().shareTypes().listExtraSpecs("be27425c-f807-4500-a056-d00721db45cf");

        assertEquals(extraSpecs.size(), 2);
        assertEquals(extraSpecs.get("snapshot_support"), "True");
        assertEquals(extraSpecs.get("driver_handles_share_servers"), "True");
    }

    @Test
    public void setExtraSpec() throws Exception {
        respondWith(JSON_EXTRA_SPECS_SET);

        Map<String, String> extraSpecMap = Maps.newHashMap();
        extraSpecMap.put("my_key", "my_value");

        ExtraSpecs extraSpecs = osv3().share().shareTypes().setExtraSpec(
                "be27425c-f807-4500-a056-d00721db45cf",
                ExtraSpecs.toExtraSpecs(extraSpecMap));

        assertEquals(extraSpecs.size(), 1);
        assertEquals(extraSpecs.get("my_key"), "my_value");
    }

    @Test
    public void unsetExtraSpec() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareTypes().unsetExtraSpec(
                "be27425c-f807-4500-a056-d00721db45cf",
                "my_key");
        assertTrue(response.isSuccess());
    }

    @Test
    public void addShareTypeAccess() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareTypes().addShareTypeAccess(
                "be27425c-f807-4500-a056-d00721db45cf",
                "e1284adea3ee4d2482af5ed214f3ad90");
        assertTrue(response.isSuccess());
    }

    @Test
    public void removeShareTypeAccess() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareTypes().removeShareTypeAccess(
                "be27425c-f807-4500-a056-d00721db45cf",
                "e1284adea3ee4d2482af5ed214f3ad90");
        assertTrue(response.isSuccess());
    }

    @Test
    public void shareTypeAccessDetails() throws Exception {
        respondWith(JSON_SHARE_TYPE_ACCESS_DETAILS);

        List<? extends ShareTypeAccess> shareTypeAccessList = osv3().share().shareTypes().shareTypeAccessDetails(
                "be27425c-f807-4500-a056-d00721db45cf");
        assertEquals(shareTypeAccessList.size(), 2);

        ShareTypeAccess shareTypeAccess1 = shareTypeAccessList.get(0);
        ShareTypeAccess shareTypeAccess2 = shareTypeAccessList.get(1);

        assertEquals(shareTypeAccess1.getShareTypeId(), "1732f284-401d-41d9-a494-425451e8b4b8");
        assertEquals(shareTypeAccess1.getProjectId(), "818a3f48dcd644909b3fa2e45a399a27");

        assertEquals(shareTypeAccess2.getShareTypeId(), "1732f284-401d-41d9-a494-425451e8b4b8");
        assertEquals(shareTypeAccess2.getProjectId(), "e1284adea3ee4d2482af5ed214f3ad90");
    }
}
