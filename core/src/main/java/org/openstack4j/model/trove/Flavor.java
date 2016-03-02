package org.openstack4j.model.trove;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.Link;

import java.util.List;

/**
 * Created by cp16net on 2/10/16.
 */
public interface Flavor extends ModelEntity {
    /**
     * @return the id for this flavor
     */
    String getId();

    /**
     * @return the descriptive name of the flavor
     */
    String getName();

    /**
     * @return the Memory in MB for the flavor
     */
    int getRam();

    /**
     * Gets the links.
     *
     * @return the links
     */
    List<? extends Link> getLinks();
}
