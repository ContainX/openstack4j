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
package com.huawei.openstack4j.openstack.map.reduce.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import com.huawei.openstack4j.api.map.reduce.MapReduceImageService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.Image;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceImage;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceImage.MapReduceImages;

/**
 * The implementation of manipulation of {@link Image}
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public class MapReduceImageServiceImpl extends BaseMapReduceServices implements MapReduceImageService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Image> list() {
        return get(MapReduceImages.class, uri("/images")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Image> list(String... tags) {
        Invocation<MapReduceImages> invoke = get(MapReduceImages.class, uri("/images"));
        for (String tag : tags) {
           invoke = invoke.param("tags",tag);
        }
        return invoke.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image get(String imageId) {
        checkNotNull(imageId);
        return get(MapReduceImage.class, uri("/images/%s", imageId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image register(String imageId, String username, String description) {
        checkNotNull(imageId);
        checkNotNull(username);
        RegisterImageRequest reg = new RegisterImageRequest(username,description);
        return post(MapReduceImage.class, uri("/images/%s",imageId))
                     .entity(reg)  // setup request
                     .execute(ExecutionOptions.<MapReduceImage>create(PropagateOnStatus.on(404))); // Use respongse progagation for "Not found" status to throw exception instead of return null
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse unregister(String imageId) {
        checkNotNull(imageId);
        return deleteWithResponse(uri("/images/%s", imageId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image tag(String imageId, String... tags) {
        checkNotNull(imageId);
        checkNotNull(tags);
        return post(MapReduceImage.class, uri("/images/%s/tag", imageId)).entity(new UpdateTagsRequest(tags)).execute(ExecutionOptions.<MapReduceImage>create(PropagateOnStatus.on(404))); // Use respongse progagation for "Not found" status to throw exception instead of return null
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image untag(String imageId, String... tags) {
        checkNotNull(imageId);
        checkNotNull(tags);
        return post(MapReduceImage.class, uri("/images/%s/untag", imageId)).entity(new UpdateTagsRequest(tags)).execute(ExecutionOptions.<MapReduceImage>create(PropagateOnStatus.on(404))); // Use respongse progagation for "Not found" status to throw exception instead of return null
    }

    public class RegisterImageRequest implements ModelEntity {
        public static final long serialVersionUID = 1L;

        private String username;
        private String description;

        public RegisterImageRequest(String username, String description) {
           this.username = username;
           this.description = description;
        }

        /**
         * @return username information
         */
        public String getUsername() {
           return username;
        }

        /**
         * @return description
         */
        public String getDescription() {
           return description;
        }

    }

    public class UpdateTagsRequest implements ModelEntity {
        public static final long serialVersionUID = 1L;

        private String[] tags;

        public UpdateTagsRequest(String... tags) {
           this.tags = tags;
        }

        /**
         * @return tags
         */
        public String[] getTags() {
           return tags;
        }

    }
}
