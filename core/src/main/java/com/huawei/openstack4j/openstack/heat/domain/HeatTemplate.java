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
package com.huawei.openstack4j.openstack.heat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.huawei.openstack4j.model.heat.Template;
import com.huawei.openstack4j.model.heat.builder.TemplateBuilder;

/**
 * This class represents a HeatTemplate. It uses jackson for (de)serialization
 * of contents.
 * 
 * @author Matthias Reisser
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeatTemplate implements Template {
    private static final long serialVersionUID = 4056106815634314225L;

    @JsonRawValue
    @JsonProperty("template")
    private String templateJson;

    @JsonProperty("template_url")
    private String templateURL;


    @Override
    public String getTemplateJson() {
        return templateJson;
    }

    @Override
    public String getTemplateURL() {
        return templateURL;
    }


    /**
     * returns a {@link HeatTemplateConcreteBuilder} for configuration and
     * creation of a {@link HeatTemplate}
     * 
     * @return a {@link HeatTemplateConcreteBuilder}
     */
    public static HeatTemplateConcreteBuilder build() {
        return new HeatTemplateConcreteBuilder();
    }

    @Override
    public TemplateBuilder toBuilder() {
        return new HeatTemplateConcreteBuilder(this);
    }

    /**
     * builder class for configuration and creation of {@link HeatTemplate}
     * objects. Use {@link #build()} to create the {@link HeatTemplate} object.
     * 
     * @author Matthias Reisser
     * 
     */
    public static class HeatTemplateConcreteBuilder implements TemplateBuilder {
        HeatTemplate model;

        /**
         * Constructor to create a {@link HeatTemplateConcreteBuilder} object
         * with a new, empty {@link HeatTemplate} object.
         */
        public HeatTemplateConcreteBuilder() {
            this(new HeatTemplate());
        }

        /**
         * Constructor to create a {@link HeatTemplateConcreteBuilder} object
         * for an existing {@link HeatTemplate} object.
         * 
         * @param template
         *            existing {@link HeatTemplate} object.
         */
        public HeatTemplateConcreteBuilder(HeatTemplate template) {
            this.model = template;
        }

        @Override
        public Template build() {
            return model;
        }

        @Override
        public TemplateBuilder from(Template in) {
            model = (HeatTemplate) in;
            return this;
        }

        @Override
        public TemplateBuilder templateJson(String template) {
            model.templateJson = template;
            return this;
        }

        @Override
        public TemplateBuilder templateURL(String templateURL) {
            model.templateURL = templateURL;
            return this;
        }
    }
}