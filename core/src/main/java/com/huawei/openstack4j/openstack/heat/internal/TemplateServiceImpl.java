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
package com.huawei.openstack4j.openstack.heat.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.heat.TemplateService;
import com.huawei.openstack4j.model.heat.Template;
import com.huawei.openstack4j.model.heat.TemplateResponse;
import com.huawei.openstack4j.openstack.heat.domain.HeatTemplate;

/**
 * This class implements all methods for manipulation of {@link HeatTemplate}
 * objects. The non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html#stack-templates
 * 
 * 
 * @author Matthias Reisser
 * 
 */
public class TemplateServiceImpl extends BaseHeatServices implements TemplateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateResponse validateTemplate(String template) {
        checkNotNull(template);
        return validateTemplate(Builders.template().templateJson(template).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateResponse validateTemplateByURL(String templateURL) {
        checkNotNull(templateURL);
        return validateTemplate(Builders.template().templateURL(templateURL).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateResponse validateTemplate(Template template) {
        checkNotNull(template);

        try {
            post(String.class, uri("/validate")).entity(template).execute();
        } catch (RuntimeException re) {
            return TemplateResponse.fail(re.getMessage());
        }
        return TemplateResponse.success();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateAsString(String stackName, String stackId) {
        checkNotNull(stackName);
        checkNotNull(stackId);
        return get(String.class, uri("/stacks/%s/%s/template", stackName, stackId)).execute();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTemplateAsMap(String stackName, String stackId) {
        checkNotNull(stackName);
        checkNotNull(stackId);
        return get(Map.class, uri("/stacks/%s/%s/template", stackName, stackId)).execute();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTemplateAsMap(String stackNameOrId) {
        checkNotNull(stackNameOrId);
        return get(Map.class, uri("/stacks/%s/template", stackNameOrId)).execute();
    }
    
    
}
