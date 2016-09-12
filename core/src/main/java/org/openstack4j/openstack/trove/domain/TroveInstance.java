package org.openstack4j.openstack.trove.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.trove.Flavor;
import org.openstack4j.model.trove.Instance;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Model implementation for Database instance
 *
 * @author Shital Patil
 */
@JsonRootName("instance")
public class TroveInstance implements Instance {

    private static final long serialVersionUID = 1L;

    private Date created;

    private TroveInstanceFlavor flavor;

    private String hostname;

    private String id;

    private String name;

    private String status;

    private Date updated;

    private Volume volume;

    public class Volume {

        private String type;

        private Integer size;

        /**
         *
         * @return
         *         The type
         */

        public String getType() {
            return type;
        }

        /**
         *
         * @param size
         *            The size
         */

        public int getSize() {
            return size;
        }

    }

    @Override
    public int getVolumeSize() {
        return getVolume().getSize();
    }

    @Override
    public String getVolumeType() {
        return getVolume().getType();
    }

    @Override
    public Flavor getFlavor() {
        return flavor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    public Volume getVolume() {
        return volume;
    }

    public static class DBInstances extends ListResult<TroveInstance> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("instances")
        private List<TroveInstance> instances;

        @Override
        protected List<TroveInstance> value() {
            return instances;
        }
    }
}
