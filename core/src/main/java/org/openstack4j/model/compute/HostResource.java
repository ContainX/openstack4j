package org.openstack4j.model.compute;

import org.openstack4j.model.ModelEntity;

/**
 * OS Host describes capabilities of each compute host where Nova servers are running on
 *
 * @author Qin An
 */
public interface HostResource extends ModelEntity {
    /**
     * @return the number of CPUs of the compute host
     */
    int getCpu();

    /**
     * @return the size of Disk the compute host has, in GB
     */
    int getDiskInGb();

    /**
     * @return the Hostname of the compute host
     */
    String getHost();

    /**
     * @return the size of Memory of the compute host has, in MB
     */
    int getMemoryInMb();

    /**
     * @return the project id (or special name like total, used_now, used_max)
     */
    String getProject();


    /**
     * @return service
     */
    String getService();

    /**
     * @return zone
     */
    String getZone();

    /**
     * @return host name
     */
    String getHostName();

}
