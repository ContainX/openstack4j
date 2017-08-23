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
package com.huawei.openstack4j.model.image;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * @author esommar on 5/8/2017.
 * @see https://docs.openstack.org/developer/glance/cache.html
 */
public interface CachedImage extends ModelEntity {

    /**
     *
     * @return the image id of the cached image
     */
    String getImageId();

    /**
     *
     * @return date when this image was last accessed in the cache
     */
    Date getLastAccessed();

    /**
     *
     * @return date when the image was last modified in the cache
     */
    Date getLastModified();

    /**
     *
     * @return nr of cache hits
     */
    Integer getHits();

    /**
     *
     * @return the image size
     */
    Long getSize();
}
