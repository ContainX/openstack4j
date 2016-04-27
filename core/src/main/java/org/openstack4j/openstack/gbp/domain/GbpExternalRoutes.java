package org.openstack4j.openstack.gbp.domain;

import org.openstack4j.model.gbp.ExternalRoutes;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
/**
 * Model implementation for External Routes
 * 
 * @author vinod borole
 */
@JsonRootName("external_routes")
public class GbpExternalRoutes implements ExternalRoutes {
    private static final long serialVersionUID = 1L;

    private String destination;
    private String nexthop;
    
    public GbpExternalRoutes() {
    }
    
    public GbpExternalRoutes(String destination, String nexthop){
        this.destination=destination;
        this.nexthop=nexthop;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNexthop() {
        return nexthop;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                     .add("destination", destination).add("nexthop", nexthop).toString();
    }
}
