package org.openstack4j.model.magnum;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.openstack.common.GenericLink;

public interface BayBuilder extends Builder<BayBuilder, Bay> {
    /**
     * @see Bay#getStatus
     */
    BayBuilder status(String status);


    /**
     * @see Bay#getUuid
     */
    BayBuilder uuid(String uuid);


    /**
     * @see Bay#getLinks
     */
    BayBuilder links(List<GenericLink> links);


    /**
     * @see Bay#getStackId
     */
    BayBuilder stackId(String stackId);


    /**
     * @see Bay#getMasterCount
     */
    BayBuilder masterCount(Integer masterCount);


    /**
     * @see Bay#getBaymodelId
     */
    BayBuilder baymodelId(String baymodelId);


    /**
     * @see Bay#getNodeCount
     */
    BayBuilder nodeCount(Integer nodeCount);


    /**
     * @see Bay#getBayCreateTimeout
     */
    BayBuilder bayCreateTimeout(String bayCreateTimeout);


    /**
     * @see Bay#getName
     */
    BayBuilder name(String name);


}