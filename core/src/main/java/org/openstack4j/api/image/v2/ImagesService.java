package org.openstack4j.api.image.v2;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.image.v2.ImageUpdate;
import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.MemberCreate;
import org.openstack4j.model.image.v2.MemberUpdate;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Image (Glance) V2 Images Api
 * @author emjburns
 */
public interface ImagesService extends RestService {

    /**
     * List all available operating system images
     * @return list of images
     */
    List<? extends Image> list();

    /**
     * Returns list of images filtered by parameters.
     * For filtering guidelines, see http://developer.openstack.org/api-ref/image/v2/index.html#show-images
     * To paginate, use "limit" and "marker" parameters
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of images fitered by filteringParams
     */
    List<? extends Image> list(Map<String, String> filteringParams);

    /*
    TODO: support pagination using first and next response fields instead
    of using self pagination (limit and marker params)
     */

    /**
     * Show details for an image by imageid.
     * The image must exist
     * @param imageId
     * @return the image
     */
    Image get(String imageId);

    /**
     * Creates a catalog record for an operating system disk image.
     * @param image
     * @return Image
     */
    Image create(Image image);

    /**
     * Update image by providing the changed image object.
     * @param image
     * @return image
     */
    Image update(Image image);

    /**
     * Update an image by building the string of json operations
     * that represent the json transformation
     * instead of the building the whole image
     * @param imageId
     * @param imageUpdate
     * @return Image
     */
    Image update(String imageId, ImageUpdate imageUpdate);

    /**
     * Deletes an image.
     * You cannot delete images with the protected attribute set to true (boolean).
     * @param imageId
     */
    ActionResponse delete(String imageId);

    /**
     * Deactivate an image
     * If you try to download a deactivated image, you will receive a 403 (Forbidden) response code.
     * Additionally, only administrative users can view image locations for deactivated images.
     * @param imageId
     */
    ActionResponse deactivate(String imageId);

    /**
     * Reactivate an image
     * @param imageId
     * @return
     */
    ActionResponse reactivate(String imageId);

    /**
     * List members of a particular image.
     * These members are projects or tenants that can see the image.
     * @param imageId
     * @return List of members
     */
    List<? extends Member> listMembers(String imageId);

    /**
     * List members of a particular image.
     * These members are projects or tenants that can see the image.
     * @param imageId
     * @return List of members
     */
    List<? extends Member> listMembers(String imageId, Map<String, String> filteringParams);

    /**
     * The image must exist, be private, and be owned by the author of the request.
     * Otherwise, this will fail.
     * @param imageId the image to share
     * @param memberCreate
     * @return  member
     */
    Member createMember(String imageId, MemberCreate memberCreate);

    /**
     * Get details about a member
     * @param imageId
     * @param memberId
     * @return member
     */
    Member getMember(String imageId, String memberId);

    /**
     * This call is for an image member to change their member status.
     * For more details see http://specs.openstack.org/openstack/glance-specs/specs/api/v2/sharing-image-api-v2.html
     * @param imageId
     * @param memberId
     * @param memberUpdate
     * @return member
     */
    Member updateMember(String imageId, String memberId, MemberUpdate memberUpdate);

    /**
     * You must be the owner of the image to delete the member
     * @param imageId
     * @param memberId
     */
    ActionResponse deleteMember(String imageId, String memberId);

    /**
     * Add tag to image.
     * Can also be done with ImagesService#update(image)
     * @param imageId
     * @param tag
     * @return
     */
    ActionResponse updateTag(String imageId, String tag);

    /**
     * Delete tag from image.
     * Can also be done with ImagesService#update(image)
     * @param imageId
     * @param tag
     * @return
     */
    ActionResponse deleteTag(String imageId, String tag);

//    UPLOADS AND DOWNLOADS raw image data.
//
//    PUT/v2/images/​{image_id}​/fileUpload binary image data
//            (Since Image API v2.0) Uploads binary image data.
//    curl -i -X PUT -H "X-Auth-Token: $token" \
//            -H "Content-Type: application/octet-stream" \
//            -d @/home/glance/ubuntu-12.10.qcow2 \
//            $image_url/v2/images/{image_id}/file
//
//    GET/v2/images/​{image_id}​/fileDownload binary image data
//            (Since Image API v2.0) Downloads binary image data.

    //need content-type header of: application/octet-stream
    ActionResponse upload(String imageId, Payload<?> payload, @Nullable Image image);

    ActionResponse download(String imageId, File filename);
}
