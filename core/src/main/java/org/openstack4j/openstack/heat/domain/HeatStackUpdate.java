package org.openstack4j.openstack.heat.domain;

import java.util.Map;

import org.openstack4j.model.heat.StackUpdate;
import org.openstack4j.model.heat.builder.StackUpdateBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model Entity used for updating a Stack
 * 
 * @author Jeremy Unruh
 */
public class HeatStackUpdate implements StackUpdate {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("template")
    private String template;
    @JsonProperty("template_url")
    private String templateURL;
    @JsonProperty("parameters")
    private Map<String, String> parameters;
    @JsonProperty("timeout_mins")
    private Long timeoutMins;
 
    public static StackUpdateBuilder builder() {
        return new HeatStackUpdateConcreteBuilder();
    }
    
    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    public String getTempateURL() {
        return templateURL;
    }

    @Override
    public StackUpdateBuilder toBuilder() {
        return new HeatStackUpdateConcreteBuilder(this);
    }
    
    public static class HeatStackUpdateConcreteBuilder implements StackUpdateBuilder {

        private HeatStackUpdate model;
        
        public HeatStackUpdateConcreteBuilder() {
            this(new HeatStackUpdate());
        }
        
        public HeatStackUpdateConcreteBuilder(HeatStackUpdate model) {
            this.model = model;
        }
        
        @Override
        public StackUpdate build() {
            return model;
        }

        @Override
        public StackUpdateBuilder from(StackUpdate in) {
            model = (HeatStackUpdate) in;
            return this;
        }

        @Override
        public StackUpdateBuilder template(String template) {
            model.template = template;
            return this;
        }

        @Override
        public StackUpdateBuilder templateURL(String templateURL) {
            model.templateURL = templateURL;
            return this;
        }

        @Override
        public StackUpdateBuilder parameters(Map<String, String> parameters) {
            model.parameters = parameters;
            return this;
        }

        @Override
        public StackUpdateBuilder timeoutMins(Long timeoutMins) {
            model.timeoutMins = timeoutMins;
            return this;
        }
    }
}
