package org.openstack4j.openstack.identity.domain.v3;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.identity.builder.v3.ProjectBuilder;
import org.openstack4j.model.identity.v3.Domain;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * Project model class for identity.v3
 * 
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#projects-v3">API reference</a>
 */
@JsonRootName("project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneProject implements Project {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private KeystoneDomain domain;
    private String domainId;
    private String description;
    private Map<String, String> links;
    private String parentId;
    private String subtree;
    private String parents;
    private Boolean enabled = true;

    /**
     * @return the Project builder
     */
    public static ProjectBuilder builder() {
        return new ProjectConcreteBuilder();
    }

    @Override
    public ProjectBuilder toBuilder() {
        return new ProjectConcreteBuilder(this);
    }

    /**
     * @return the id of the project
     */
    public String getId() {
        return id;
    }

    /**
     * @return the domain the project belongs to
     */
    public Domain getDomain() {
        return domain;
    }

    /**
     * @return the domain id of the project
     */
    public String getDomainId() {
        if (domainId == null && domain != null && domain.getId() != null)
            domainId = domain.getId();
        return domainId;
    }

    /**
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * @return the links of the project
     */
    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * @return the parentId of the project
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the subtree of the project
     */
    public String getSubtree() {
        return subtree;
    }

    /**
     * @return the parents of the project
     */
    public String getParents() {
        return parents;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return (enabled != null && enabled);
    }

    /**
     * set project enabled
     * 
     * @param enabled
     *            the new enabled status
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("domainId", domain.getId())
                .add("description", description)
                .add("name", name)
                .add("links", links)
                .add("parentId", parentId)
                .add("subtree", subtree)
                .add("parents", parents)
                .add("enabled", enabled)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, domain.getId(), description, name, links, parentId, subtree, parents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeystoneProject that = KeystoneProject.class.cast(obj);
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.domain, that.domain)
                && Objects.equal(this.description, that.description)
                && Objects.equal(this.name, that.name)
                && Objects.equal(this.links, that.links)
                && Objects.equal(this.parentId, that.parentId)
                && Objects.equal(this.subtree, that.subtree)
                && Objects.equal(this.parents, that.parents);
    }

    public static class ProjectConcreteBuilder implements ProjectBuilder {

        KeystoneProject model;

        ProjectConcreteBuilder() {
            this(new KeystoneProject());
        }

        ProjectConcreteBuilder(KeystoneProject model) {
            this.model = model;
        }

        /**
         * @see KeystoneProject#getId()
         */
        public ProjectBuilder id(String id) {
            model.id = id;
            return this;
        }

        /**
         * @see KeystoneProject#getDomainId()
         */
        public ProjectBuilder domain(Domain domain) {
            if (domain != null && domain.getId() != null)
                model.domainId = domain.getId();
            return this;
        }

        /**
         * @see KeystoneProject#getDescription()
         */
        public ProjectBuilder description(String description) {
            model.description = description;
            return this;
        }

        /**
         * @see KeystoneProject#getName()
         */
        public ProjectBuilder name(String name) {
            model.name = name;
            return this;
        }

        /**
         * @see KeystoneProject#getLinks()
         */
        public ProjectBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        /**
         * @see KeystoneProject#getParentId()
         */
        public ProjectBuilder parentId(String parentId) {
            model.parentId = parentId;
            return this;
        }

        /**
         * @see KeystoneProject#getSubtree()
         */
        public ProjectBuilder subtree(String subtree) {
            model.subtree = subtree;
            return this;
        }

        /**
         * @see KeystoneProject#getParents()
         */
        public ProjectBuilder parents(String parents) {
            model.parents = parents;
            return this;
        }

        /**
         * @see KeystoneProject#isEnabled()
         */
        public ProjectBuilder enabled(boolean enabled) {
            model.enabled = enabled;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Project build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ProjectBuilder from(Project in) {
            if (in != null)
                this.model = (KeystoneProject) in;
            return this;
        }

        @Override
        public ProjectBuilder domainId(String domainId) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    public static class Projects extends ListResult<KeystoneProject> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("projects")
        protected List<KeystoneProject> list;

        public List<KeystoneProject> value() {
            return list;
        }
    }

}
