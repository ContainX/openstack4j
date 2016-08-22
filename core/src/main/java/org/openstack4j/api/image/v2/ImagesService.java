package org.openstack4j.api.image.v2;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.image.v2.Image;

import java.util.List;
import java.util.Map;

/**
 * Image (Glance) V2 Images Api
 * @author emjburns
 */
public interface ImagesService extends RestService {
//    The possible status values for images are:
//            (Image status)
//    Status: Description
//    queued: The Image service reserved an image ID for the image in the registry but did not yet upload any image data.
//            saving: The Image service is currently uploading the raw data for the image.
//    active: The image is active and fully available in the Image service.
//            killed: An image data upload error occurred.
//    deleted: The Image service retains information about the image but the image is no longer available for use.
//            pending_delete: Similar to the deleted status. An image in this state is not recoverable.


    List<? extends Image> list();

    List<? extends Image> list(Map<String, String> filteringParams);

    List<? extends Image> listAll();

    List<? extends Image> listAll(Map<String, String> filteringParams);

    Image get(String imageId);

    Image create(Image image);

    //TODO: is update different?
//    http://developer.openstack.org/api-ref-image-v2.html#updateImage-v2
    Image update(Image image);

    ActionResponse delete(String imageId);

    ActionResponse deactivate(String imageId);

    ActionResponse reactivate(String imageId);

//    GET/v2/imagesList images
//            (Since Image API v2.0) Lists public virtual machine (VM) images.
//
//    POST/v2/imagesCreate image
//            (Since Image API v2.0) Creates a virtual machine (VM) image.
//
//    GET/v2/images/​{image_id}​Show image details
//            (Since Image API v2.0) Shows details for an image.
//
//    PATCH/v2/images/​{image_id}​Update image
//            (Since Image API v2.0) Updates an image.
//
//    DELETE/v2/images/​{image_id}​Delete image
//            (Since Image API v2.0) Deletes an image.
//
//    POST/v2/images/​{image_id}​/actions/reactivateReactivate image
//            (Since Image API v2.0) Reactivates an image.
//
//    POST/v2/images/​{image_id}​/actions/deactivateDeactivate image
//            (Since Image API v2.0) Deactivates an image.
//


//    h4. Image data (images, file)
//    Uploads and downloads raw image data.
//
//    PUT/v2/images/​{image_id}​/fileUpload binary image data
//            (Since Image API v2.0) Uploads binary image data.
//
//    GET/v2/images/​{image_id}​/fileDownload binary image data
//            (Since Image API v2.0) Downloads binary image data.
//
//    PUT/v2/images/​{image_id}​/tags/​{tag}​Add image tag
//            (Since Image API v2.0) Adds a tag to an image.
//
//    DELETE/v2/images/​{image_id}​/tags/​{tag}​Delete image tag
//            (Since Image API v2.0) Deletes a tag from an image.
//
//    GET/v2/images/​{image_id}​/membersList image members
//            (Since Image API v2.1) Lists the tenants that share this image.
//
//    POST/v2/images/​{image_id}​/membersCreate image member
//            (Since Image API v2.1) Adds a tenant ID as an image member.
//
//    GET/v2/images/​{image_id}​/members/​{member_id}​Show image member details
//            (Since Image API v2.2) Shows image member details.
//
//    PUT/v2/images/​{image_id}​/members/​{member_id}​Update image member
//            (Since Image API v2.1) Sets the status for an image member.
//
//    DELETE/v2/images/​{image_id}​/members/​{member_id}​Delete image member
//            (Since Image API v2.1) Deletes a tenant ID from the member list of an image.
}
