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

import org.openstack4j.api.murano.v1.MuranoDeploymentService;
import org.openstack4j.model.murano.v1.domain.Deployment;
import org.openstack4j.model.murano.v1.domain.Report;
import org.openstack4j.openstack.murano.v1.domain.MuranoDeployment;
import org.openstack4j.openstack.murano.v1.domain.MuranoReport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Mahotkin.
 */
public class MuranoDeploymentServiceImpl extends BaseMuranoServices implements MuranoDeploymentService {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Deployment> list(String environmentId) {
        return get(
            MuranoDeployment.MuranoDeployments.class,
            uri("/environments/%s/deployments", environmentId)
        ).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Report> reports(String environmentId, String deploymentId, List<String> serviceIds) {
        String path = String.format("/environments/%s/deployments/%s", environmentId, deploymentId);

        Invocation<MuranoReport.MuranoReports> invocation = get(MuranoReport.MuranoReports.class, path);

        for (String serviceId : serviceIds) {
            invocation.param("service_id", serviceId);
        }

        return invocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Report> reports(String environmentId, String deploymentId) {
        return reports(environmentId, deploymentId, new ArrayList<String>());
    }
}
