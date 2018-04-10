package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.cloudkitty.Module;
import org.openstack4j.model.cloudkitty.builder.ModuleBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * A module object
 * @author mariusleu
 */
public class CloudkittyModule implements Module {

    @JsonProperty("module_id")
    private String id;
    private String description;
    private boolean enabled;
    @JsonProperty("hot-config")
    private boolean hotConfig;
    private int priority;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isHotConfig() {
        return hotConfig;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public ModuleBuilder toBuilder() {
        return new CloudkittyModuleConcreteBuilder(this);
    }

    public static ModuleBuilder builder() {
        return new CloudkittyModuleConcreteBuilder();
    }

    public static class CloudkittyModuleConcreteBuilder implements  ModuleBuilder {

        private CloudkittyModule model;

        public CloudkittyModuleConcreteBuilder(CloudkittyModule model) {
            this.model = model;
        }

        public CloudkittyModuleConcreteBuilder() {
            this(new CloudkittyModule());
        }

        @Override
        public ModuleBuilder id(String moduleId) {
            model.id = moduleId;
            return this;
        }

        @Override
        public ModuleBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public ModuleBuilder enabled(boolean enabled) {
            model.enabled = enabled;
            return this;
        }

        @Override
        public ModuleBuilder supportsHotConfig(boolean hotConfig) {
            model.hotConfig = hotConfig;
            return this;
        }

        @Override
        public ModuleBuilder priority(int priority) {
            model.priority = priority;
            return this;
        }

        @Override
        public Module build() {
            return model;
        }

        @Override
        public ModuleBuilder from(Module in) {
            model = (CloudkittyModule) in;
            return this;
        }
    }

    public static class Modules extends ListResult<CloudkittyModule> {

        @JsonProperty
        List<CloudkittyModule> modules;

        @Override
        protected List<CloudkittyModule> value() {
            return modules;
        }
    }
}
