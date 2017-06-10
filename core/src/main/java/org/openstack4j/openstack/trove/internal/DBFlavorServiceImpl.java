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
package org.openstack4j.openstack.trove.internal;

import org.openstack4j.api.trove.InstanceFlavorService;
import org.openstack4j.model.trove.Flavor;
import org.openstack4j.openstack.trove.domain.TroveInstanceFlavor;
import org.openstack4j.openstack.trove.domain.TroveInstanceFlavor.Flavors;

import java.util.List;

/**
 * Flavor API Implementation
 *
 * @author sumit gandhi
 */
public class DBFlavorServiceImpl extends BaseTroveServices implements InstanceFlavorService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Flavor> list() {
        return get(Flavors.class, uri("/flavors")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flavor get(String id) {
        return get(TroveInstanceFlavor.class, uri("/flavors/%s", id)).execute();
    }

}
