/*******************************************************************************
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
package org.openstack4j.openstack.trove.internal;

import java.util.List;

import org.openstack4j.openstack.trove.domain.InstanceFlavor;
import org.openstack4j.openstack.trove.domain.InstanceFlavor.Flavors;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * The implementation of manipulation of {@link InstanceFlavor}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:13:41
 */
public class TroveInstanceFlavorService extends BaseTroveServices {

	/**
	 * Returns all the available database instance flavors
	 * @return the list of available flavors
	 */
	public List<InstanceFlavor> list() {
		return get(Flavors.class, uri("/flavors")).execute().getList();
	}

	/**
	 * Get the instance flavor specified by ID
	 * @param flavorId	the flavor identifier
	 * @return the flavor or null if not found
	 */
	public InstanceFlavor get(String flavorId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(flavorId), "parameter `flavorId` should not be empty");
		return get(InstanceFlavor.class, uri("/flavors/%s", flavorId)).execute();
	}

}
