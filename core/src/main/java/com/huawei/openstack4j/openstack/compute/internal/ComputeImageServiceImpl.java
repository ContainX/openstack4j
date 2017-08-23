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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.compute.ComputeImageService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Image;
import com.huawei.openstack4j.openstack.compute.domain.MetaDataWrapper;
import com.huawei.openstack4j.openstack.compute.domain.NovaImage;
import com.huawei.openstack4j.openstack.compute.domain.NovaImage.NovaImages;

/**
 * Provides access to Compute Images.
 *
 * @author Jeremy Unruh
 */
public class ComputeImageServiceImpl extends BaseComputeServices implements ComputeImageService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Image> list() {
		return list(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Image> list(boolean detailed) {
		String uri = (detailed) ? "/images/detail" : "/images";
		return get(NovaImages.class, uri(uri)).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image get(String imageId) {
		checkNotNull(imageId);
		return get(NovaImage.class, uri("/images/%s", imageId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String imageId) {
		checkNotNull(imageId);
		return deleteWithResponse(uri("/images/%s", imageId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> setMetaData(String imageId, Map<String, String> metadata) {
		checkNotNull(imageId);
		checkNotNull(metadata);
		return post(MetaDataWrapper.class, uri("/images/%s/metadata", imageId)).entity(MetaDataWrapper.wrap(metadata)).execute().getMetaData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse deleteMetaData(String imageId, String... keys) {
		checkNotNull(imageId);
		for (String k : keys)
		{
			ActionResponse resp  = deleteWithResponse(uri("/images/%s/metadata/%s", imageId, k)).execute();
			if (!resp.isSuccess())
			    return resp;
		}
		return ActionResponse.actionSuccess();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> getMetaData(String imageId) {
		checkNotNull(imageId);
		return get(MetaDataWrapper.class, uri("/images/%s/metadata", imageId)).execute().getMetaData();
	}

}
