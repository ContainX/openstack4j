package org.openstack4j.openstack.image.v2;

import org.openstack4j.api.image.v2.ImagesService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.MemberCreate;
import org.openstack4j.model.image.v2.MemberUpdate;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * OpenStack (Glance) V2 Image based Operations
 * @author emjburns
 */
public class ImagesServiceImpl extends BaseImageServices implements ImagesService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Image> list() {
        return get(GlanceImage.Images.class, uri("/images")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Image> list(Map<String, String> filteringParams) {
        return get(GlanceImage.Images.class, uri("/images")).params(filteringParams).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image get(String imageId) {
        checkNotNull(imageId);
        return get(GlanceImage.class, uri("/images/%s", imageId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image create(Image image) {
        checkNotNull(image);
        return post(GlanceImage.class, uri("/images")).entity(image).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image update(Image image) {
        //TODO: figure out patching
        //http://specs.openstack.org/openstack/glance-specs/specs/api/v2/http-patch-image-api-v2.html
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String imageId) {
        checkNotNull(imageId);
        return deleteWithResponse(uri("/images/%s", imageId)).param("format","json").execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deactivate(String imageId) {
        checkNotNull(imageId);
        return post(ActionResponse.class, uri("/images/%s/actions/deactivate",imageId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse reactivate(String imageId) {
        checkNotNull(imageId);
        return post(ActionResponse.class, uri("/images/%s/actions/reactivate",imageId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image upload(String imageId, Payload<?> payload, @Nullable Image image) {
        //TODO: this. see v1
        checkNotNull(imageId);
        checkNotNull(payload);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse download(String imageId, File filename) {
        //TODO: this. see v1
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse updateTag(String imageId, String tag) {
        checkNotNull(imageId);
        checkNotNull(tag);
        return put(ActionResponse.class, uri("/images/%s/tags/%s", imageId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteTag(String imageId, String tag) {
        checkNotNull(imageId);
        checkNotNull(tag);
        return deleteWithResponse(uri("/images/%s/tags/%s", imageId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Member> listMembers(String imageId) {
        checkNotNull(imageId);
        return get(GlanceMember.Members.class, uri("/images/%s/members", imageId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Member> listMembers(String imageId, Map<String, String> filteringParams) {
        checkNotNull(imageId);
        Invocation<GlanceMember.Members> req = get(GlanceMember.Members.class, uri("/images/%s/members", imageId));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Member getMember(String imageId, String memberId) {
        checkNotNull(imageId);
        checkNotNull(memberId);
        return get(Member.class, uri("/images/%s/members/%s", imageId, memberId)).execute();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Member createMember(String imageId, MemberCreate memberCreate) {
        checkNotNull(imageId);
        checkNotNull(memberCreate);
        return post(Member.class, uri("/images/%s/members",imageId)).entity(memberCreate).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Member updateMember(String imageId, String memberId, MemberUpdate memberUpdate) {
        checkNotNull(imageId);
        checkNotNull(memberId);
        return put(Member.class, uri("/images/%s/members/%s", imageId, memberId)).entity(memberUpdate).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteMember(String imageId, String memberId) {
        checkNotNull(imageId);
        checkNotNull(memberId);
        return deleteWithResponse(uri("/images/%s/members/%s",imageId, memberId)).execute();
    }
}
