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

import org.openstack4j.openstack.trove.domain.TroveInstanceFlavor;
import org.openstack4j.openstack.trove.domain.TroveInstanceFlavor.Flavors;

/**
 * The implementation of manipulation of {@link TroveInstanceFlavor}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:13:41
 */
public class TroveInstanceFlavorService extends BaseTroveServices {

	/**
	 * Returns all the available database instance flavors
	 * @return the list of available flavors
	 */
	public List<TroveInstanceFlavor> list() {
		return get(Flavors.class, uri("/flavors")).execute().getList();
	}

	/**
	 * Get the instance flavor specified by ID
	 * @param id
	 * @return the flavor or null if not found
	 */
	public TroveInstanceFlavor get(String id) {
		return get(TroveInstanceFlavor.class, uri("/flavors/%s", id)).execute();
	}

}
