package org.openstack4j.openstack.image.v2;

import org.openstack4j.api.image.v2.ImagesService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.image.v2.Image;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * OpenStack (Glance) V2 Image based Operations
 * @author emjburns
 */
public class ImagesServiceImpl extends BaseImageServices implements ImagesService {

    private static final int DEFAULT_PAGE_SIZE = 25;

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
        Invocation<GlanceImage.Images> req = get(GlanceImage.Images.class, uri("/images"));
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
    public List<? extends Image> listAll() {
        return listAll(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Image> listAll(Map<String, String> filteringParams) {
        //TODO: does this exist in the new api?
        return null;

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
}
