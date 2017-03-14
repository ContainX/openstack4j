package org.openstack4j.model.magnum;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;

public interface PodBuilder extends Builder<PodBuilder, Pod> {
    /**
     * @see Pod#getId
     */
    PodBuilder id(String id);

    /**
     * @see Pod#getUuid
     */
    PodBuilder uuid(String uuid);

    /**
     * @see Pod#getName
     */
    PodBuilder name(String name);

    /**
     * @see Pod#getDesc
     */
    PodBuilder desc(String desc);

    /**
     * @see Pod#getBayUuid
     */
    PodBuilder bayUuid(String bayUuid);

    /**
     * @see Pod#getImages
     */
    PodBuilder images(List<String> images);

    /**
     * @see Pod#getLabels
     */
    PodBuilder labels(Label labels);

    /**
     * @see Pod#getStatus
     */
    PodBuilder status(String status);

}
