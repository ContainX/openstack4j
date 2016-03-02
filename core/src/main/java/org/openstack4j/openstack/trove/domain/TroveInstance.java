package org.openstack4j.openstack.trove.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.trove.Flavor;
import org.openstack4j.model.trove.Instance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 *
 * @author Craig Vyvial
 */

@JsonRootName("instance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TroveInstance implements Instance {

    public static final long serialVersionUID = 1L;
    public String id;
    public String name;
    public Status status;
    public TroveFlavor flavor;

    private String uuid;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFlavorId() {
        return (flavor != null) ? flavor.getId() : null;
    }

    @Override
    public Flavor getFlavor() {
        return null;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id",id).add("name", name).add("flavor", flavor)
                .add("status", status).add("uuid", uuid)
//                .add("image", image).add("diskconfig", diskConfig).add("userId", userId)
//                .add("admin-pass", adminPass).add("created", created).add("updated", updated)
//                .add("launched at", launchedAt).add("tenantId", tenantId).add("hostId", hostId)
//                .add("addresses", addresses).add("hypervisor host", hypervisorHostname)
//                .add("powerstate", powerState).add("fault", fault).add("instanceName", instanceName)
//                .add("vmState", vmState).add("metadata", metadata)
                .toString();
    }

    public static class Instances extends ListResult<TroveInstance> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("instances")
        private List<TroveInstance> instances;

        public List<TroveInstance> value() { return instances; }
    }
}
