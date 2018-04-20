package org.openstack4j.openstack.gnocchi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.gnocchi.Resource;
import org.openstack4j.model.gnocchi.builder.ResourceBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * gnocchi resource
 *
 * @author zhangliang
 */
public class GnocchiResource implements Resource {

    @JsonProperty
    private String id;

    @Override
    public ResourceBuilder toBuilder() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public void setTenantId(String tenantId) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static class Resources extends ListResult<GnocchiResource> {

        private List<GnocchiResource> resources;

        @Override
        protected List<GnocchiResource> value() {
            return resources;
        }
    }
}
