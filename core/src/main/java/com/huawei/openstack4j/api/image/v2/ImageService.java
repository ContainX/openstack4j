/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.image.v2;

import javax.annotation.Nullable;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.image.v2.Image;
import com.huawei.openstack4j.model.image.v2.ImageUpdate;
import com.huawei.openstack4j.model.image.v2.Member;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * OpenStack (Glance) Image V2 support
 * @author emjburns
 */
public interface ImageService extends RestService {

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
     * @param memberId
     * @return  member
     */
    Member createMember(String imageId, String memberId);

    /**
     * Get details about a member
     * @param imageId
     * @param memberId
     * @return member
     */
    Member getMember(String imageId, String memberId);

    /**
     * Change status of an image member
     * For more details see http://specs.openstack.org/openstack/glance-specs/specs/api/v2/sharing-image-api-v2.html
     * @param imageId
     * @param memberId
     * @param memberStatus
     * @return member
     */
    Member updateMember(String imageId, String memberId, Member.MemberStatus memberStatus);

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

    /**
     * Uploads binary image data
     * @param imageId
     * @param payload
     * @param image
     * @return
     */
    ActionResponse upload(String imageId, Payload<?> payload, @Nullable Image image);

    /**
     * Downloads binary image data
     * @param imageId
     * @param filename
     * @return
     */
    ActionResponse download(String imageId, File filename);

    /**
     * @return the image v2 tasks service
     */
    TaskService tasks();
}
