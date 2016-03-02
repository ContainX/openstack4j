package org.openstack4j.openstack.trove.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.trove.Flavor;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Created by cp16net on 2/14/16.
 */
@JsonRootName("flavor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TroveFlavor implements Flavor {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Integer ram;
    private List<GenericLink> links;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRam() {
        return ram;
    }

    @Override
    public List<? extends Link> getLinks() {
        return links;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id).add("name", name).add("ram", ram)
                .add("links", links).addValue("\n")
                .toString();
    }

    public static class Flavors extends ListResult<TroveFlavor> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("flavors")
        List<TroveFlavor> flavors;

        public List<TroveFlavor> value() {
            return flavors;
        }
    }

}
