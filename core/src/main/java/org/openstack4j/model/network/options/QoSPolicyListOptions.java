package org.openstack4j.model.network.options;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

public class QoSPolicyListOptions {

    private Map<String, Object> queryParams = Maps.newHashMap();

    private QoSPolicyListOptions() { }

    public static QoSPolicyListOptions create() {
        return new QoSPolicyListOptions();
    }

    /**
     * The human-readable description of the resource
     *
     * @param description QoS policy description
     * @return options
     */
    public QoSPolicyListOptions description(String description) {
        return add("description", description);
    }

    /**
     * ID of the project that owns the resource
     *
     * @param tenantId ID of the project
     * @return options
     */
    public QoSPolicyListOptions tenantId(String tenantId) {
        return add("tenant_id", tenantId);
    }

    /**
     * ID of the project that owns the resource
     *
     * @param projectId ID of the project
     * @return options
     */
    public QoSPolicyListOptions projectId(String projectId) {
        return add("project_id", projectId);
    }

    /**
     * Revision number of the resource
     *
     * @param revisionNumber revision number
     * @return options
     */
    public QoSPolicyListOptions revisionNumber(Integer revisionNumber) {
        return add("revision_number", revisionNumber);
    }

    /**
     * Whether a policy is shared across all projects.
     *
     * @param shared whether this policy is shared across all projects.
     * @return options
     */
    public QoSPolicyListOptions shared(Boolean shared) {
        return add("shared", shared);
    }

    /**
     * ID of the resource
     *
     * @param id QoS policy ID
     * @return options
     */
    public QoSPolicyListOptions id(String id) {
        return add("id", id);
    }

    /**
     * Whether a policy is the default policy
     *
     * @param isDefault a policy is the default policy
     * @return options
     */
    public QoSPolicyListOptions isDefault(Boolean isDefault) {
        return add("is_default", isDefault);
    }

    /**
     * Name of a policy
     *
     * @param name policy name
     * @return options
     */
    public QoSPolicyListOptions name(String name) {
        return add("name", name);
    }

    /**
     * Set of tags that all must be associated to a policy
     *
     * @param tags set of tags
     * @return options
     */
    public QoSPolicyListOptions tags(Set<String> tags) {
        return add("tags", String.join(",", tags));
    }

    /**
     * Set of tags that only some must be associated to a policy
     *
     * @param tags set that only some must be associated to a policy
     * @return options
     */
    public QoSPolicyListOptions tagsAny(Set<String> tags) {
        return add("tags-any", String.join(",", tags));
    }

    /**
     * Set of tags that must not all be associated to a policy.
     *
     * @param tags set that must not all be associated to a policy
     * @return options
     */
    public QoSPolicyListOptions notTags(Set<String> tags) {
        return add("not-tags", String.join(",", tags));
    }

    /**
     * Set of tags where no subset should be associated to a policy
     *
     * @param tags no subset should be associated to a policy
     * @return options
     */
    public QoSPolicyListOptions notTagsAny(Set<String> tags) {
        return add("not-tags-any", String.join(",", tags));
    }

    /**
     * Sort direction:
     *  - asc (ascending)
     *  - desc (descending)
     *
     * @param direction sort direction
     * @return options
     */
    public QoSPolicyListOptions sortDir(String direction) {
        return add("sort_dir", direction);
    }

    /**
     * Name of field by which to to sort
     *
     * @param key sort key
     * @return options
     */
    public QoSPolicyListOptions sortKey(String key) {
        return add("sort_key", key);
    }

    /**
     * Set of field names to include in the list of QoS policies
     *
     * @param fields set of field names to include in the list of QoS policies
     * @return options
     */
    public QoSPolicyListOptions fields(Set<String> fields) {
        return add("fields", fields);
    }

    private QoSPolicyListOptions add(String param, Object value) {
        if (value != null)
            this.queryParams.put(param, value);
        return this;
    }

    public Map<String, Object> getOptions() {
        return queryParams;
    }
}
