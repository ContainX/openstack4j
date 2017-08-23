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
package com.huawei.openstack4j.openstack.murano.v1.internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.murano.v1.MuranoApplicationService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.murano.v1.domain.Application;
import com.huawei.openstack4j.openstack.common.MapEntity;
import com.huawei.openstack4j.openstack.murano.v1.domain.MuranoApplication;
import com.huawei.openstack4j.openstack.murano.v1.utils.MuranoApplicationUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Mahotkin.
 */
public class MuranoApplicationServiceImpl extends BaseMuranoServices implements MuranoApplicationService {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Application> list(String environmentId, String sessionId) {
        Invocation<MuranoApplication.ApplicationList> invocation;

        invocation = get(
            MuranoApplication.ApplicationList.class,
            uri("/environments/%s/services", environmentId)
        );

        if (sessionId != null) {
            invocation.header("X-Configuration-Session", sessionId);
        }

        return invocation.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Application> list(String environmentId) {
        return list(environmentId, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Application> get(String environmentId, String path, String sessionId) {
        Invocation<JsonNode> invocation;

        invocation = get(
            JsonNode.class,
            uri("/environments/%s/services/%s", environmentId, path)
        );

        if (sessionId != null) {
            invocation.header("X-Configuration-Session", sessionId);
        }

        return MuranoApplicationUtils.toApplications(invocation.execute());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Application> get(String environmentId, String path) {
        return get(environmentId, path, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application create(String environmentId, String sessionId, Map<String, Object> data) {
        MapEntity entity = new MapEntity();
        entity.putAll(data);

        Invocation<MuranoApplication> invocation;

        invocation = post(MuranoApplication.class, uri("/environments/%s/services//", environmentId))
            .header("X-Configuration-Session", sessionId)
            .entity(entity);

        return invocation.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Application> create(String environmentId, String sessionId, String jsonString) {
        Invocation<MuranoApplication.ApplicationList> invocation;
        MuranoApplication.ApplicationList toCreate = MuranoApplicationUtils.toApplications(jsonString);

        invocation = post(MuranoApplication.ApplicationList.class, uri("/environments/%s/services//", environmentId))
            .header("X-Configuration-Session", sessionId)
            .entity(toCreate);

        return invocation.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application update(String environmentId, String sessionId, Map<String, Object> data) {
        MapEntity entity = new MapEntity();
        entity.putAll(data);

        Invocation<MuranoApplication> invocation;

        invocation = put(MuranoApplication.class, uri("/environments/%s/services//", environmentId))
            .header("X-Configuration-Session", sessionId)
            .entity(entity);

        return invocation.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Application> update(String environmentId, String sessionId, String jsonString) {
        Invocation<MuranoApplication.ApplicationList> invocation;
        MuranoApplication.ApplicationList toUpdate = MuranoApplicationUtils.toApplications(jsonString);

        invocation = put(MuranoApplication.ApplicationList.class, uri("/environments/%s/services//", environmentId))
            .header("X-Configuration-Session", sessionId)
            .entity(toUpdate);

        return invocation.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String environmentId, String path, String sessionId) {
        return deleteWithResponse(uri("/environments/%s/services/%s", environmentId, path))
            .header("X-Configuration-Session", sessionId)
            .execute();
    }
}
