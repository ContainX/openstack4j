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
package org.openstack4j.openstack.murano.v1.internal;

import org.openstack4j.api.murano.v1.MuranoEnvironmentService;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.murano.v1.domain.Environment;
import org.openstack4j.openstack.murano.v1.domain.MuranoEnvironment;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
* This class implements all methods for manipulation of {@link MuranoEnvironment} objects.
*
* @author Nikolay Mahotkin
*
*/
public class MuranoEnvironmentServiceImpl extends BaseMuranoServices implements MuranoEnvironmentService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MuranoEnvironment> list() {
        return get(MuranoEnvironment.MuranoEnvironments.class, uri("/environments")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment get(String id) {
        checkNotNull(id);
        return get(MuranoEnvironment.class, uri("/environments/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment create(Environment env) {
        checkNotNull(env);
        return post(MuranoEnvironment.class, uri("/environments"))
                .entity(env)  // setup request
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/environments/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Environment rename(String id, String name) {
        checkNotNull(id);
        checkNotNull(name);
        return put(MuranoEnvironment.class, uri("/environments/%s", id))
                .entity(new RenameEnvironmentRequest(name))
                .execute();
    }

    private class RenameEnvironmentRequest implements ModelEntity {
        public static final long serialVersionUID = 1L;

        private String name;

        RenameEnvironmentRequest(String name) {
            this.name = name;
        }

        /**
         * @return name
         */
        public String getName() {
            return this.name;
        }
    }
}
