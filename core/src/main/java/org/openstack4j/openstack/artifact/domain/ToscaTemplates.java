package org.openstack4j.openstack.artifact.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.artifact.Metadata;
import org.openstack4j.model.artifact.Template;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;
import org.openstack4j.model.artifact.builder.ToscaTemplatesArtifactBuilder;
import org.openstack4j.model.common.builder.BasicResourceBuilder;

import java.util.List;

/**
 * A Glare Tosca Templates Artifact implementation model
 *
 * @author Pavan Vadavi
 */
public class ToscaTemplates implements ToscaTemplatesArtifact {

    @JsonProperty("status")
    private String status;
    @JsonProperty("icon")
    private Object icon;
    @JsonProperty("name")
    private String name;
    @JsonProperty("license")
    private Object license;
    @JsonProperty("template_format")
    private String templateFormat;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("activated_at")
    private String activatedAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("provided_by")
    private Object providedBy;
    @JsonProperty("version")
    private String version;
    @JsonProperty("license_url")
    private Object licenseUrl;
    @JsonProperty("supported_by")
    private Object supportedBy;
    @JsonProperty("template")
    private TemplateImpl template;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("release")
    private List<Object> release = null;
    @JsonProperty("metadata")
    private MetadataImpl metadata;
    @JsonProperty("id")
    private String id;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("description")
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public MetadataImpl getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataImpl metadata) {
        this.metadata = metadata;
    }

    public List<Object> getRelease() {
        return release;
    }

    public void setRelease(List<Object> release) {
        this.release = release;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public TemplateImpl getTemplate() {
        return template;
    }

    public void setTemplate(TemplateImpl template) {
        this.template = template;
    }

    public Object getSupportedBy() {
        return supportedBy;
    }

    public void setSupportedBy(Object supportedBy) {
        this.supportedBy = supportedBy;
    }

    public Object getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(Object licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getProvidedBy() {
        return providedBy;
    }

    public void setProvidedBy(Object providedBy) {
        this.providedBy = providedBy;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(String activatedAt) {
        this.activatedAt = activatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTemplateFormat() {
        return templateFormat;
    }

    public void setTemplateFormat(String templateFormat) {
        this.templateFormat = templateFormat;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public ToscaTemplatesArtifactBuilder toBuilder() {
        return new ToscaTemplatesConcreteBuilder(this);
    }

    public static ToscaTemplatesArtifactBuilder builder() {
        return new ToscaTemplatesConcreteBuilder();
    }

    public static class ToscaTemplatesConcreteBuilder extends BasicResourceBuilder<ToscaTemplatesArtifact,ToscaTemplatesConcreteBuilder> implements ToscaTemplatesArtifactBuilder {

        private ToscaTemplates artifact;

        ToscaTemplatesConcreteBuilder() {
            this(new ToscaTemplates());
        }

        ToscaTemplatesConcreteBuilder(ToscaTemplates artifact) {
            this.artifact = artifact;
        }

        @Override
        public ToscaTemplatesArtifactBuilder template(Template template) {
            artifact.template = (TemplateImpl) template;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder templateFormat(String templateFormat) {
            artifact.templateFormat = templateFormat;
            return this;
        }

        @Override
        public ToscaTemplatesArtifact build() {
            return artifact;
        }

        @Override
        public ToscaTemplatesArtifactBuilder from(ToscaTemplatesArtifact in) {
            this.artifact = (ToscaTemplates) in;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder description(String description) {
            artifact.description = description;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder tags(List<Object> tags) {
            artifact.tags = tags;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder metadata(Metadata metadata) {
            artifact.metadata = (MetadataImpl) metadata;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder release(List<Object> release) {
            artifact.release = release;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder owner(String owner) {
            artifact.owner = owner;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder supportedBy(Object supportedBy) {
            artifact.supportedBy = supportedBy;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder licenseUrl(Object licenseUrl) {
            artifact.licenseUrl = licenseUrl;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder version(String version) {
            artifact.version = version;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder providedBy(Object providedBy) {
            artifact.providedBy = providedBy;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder visibility(String visibility) {
            artifact.visibility = visibility;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder updatedAt(String updatedAt) {
            artifact.updatedAt = updatedAt;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder activatedAt(String activatedAt) {
            artifact.activatedAt = activatedAt;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder createdAt(String createdAt) {
            artifact.createdAt = createdAt;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder license(Object license) {
            artifact.license = license;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder icon(Object icon) {
            artifact.icon = icon;
            return this;
        }

        @Override
        public ToscaTemplatesArtifactBuilder status(String status) {
            artifact.status = status;
            return this;
        }

        @Override
        protected ToscaTemplatesArtifact reference() {
            return artifact;
        }
    }

}
