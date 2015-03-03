package org.openstack4j.model.compute.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.compute.QuotaSetUpdate;

/**
 * A builder which creates a QuotaSetUpdate object
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSetUpdateBuilder extends Builder<QuotaSetUpdateBuilder, QuotaSetUpdate> {
    
    /**
     * Metadata items permitted
     */
    QuotaSetUpdateBuilder metadataItems(int metadataitems);

    /**
     * Injected file maximum length
     */
    QuotaSetUpdateBuilder injectedFileContentBytes(int injectedFileContentBytes);

    /**
     * Number of inject-able files
     */
    QuotaSetUpdateBuilder injectedFiles(int injectedFiles);

    /**
     * the number of gigabytes allowed
     */
    QuotaSetUpdateBuilder gigabytes(int gigabytes);

    /**
     * Quantity of instanceable RAM (MBytes)
     */
    QuotaSetUpdateBuilder ram(int ram);

    /**
     * Number of floating IP
     */
    QuotaSetUpdateBuilder floatingIps(int floatingIps);

    /**
     * Number of permitted instances
     */
    QuotaSetUpdateBuilder instances(int instances);

    /**
     * Number of permitted volumes
     */
    QuotaSetUpdateBuilder volumes(int volumes);

    /**
     * Number of instanceable cores
     */
    QuotaSetUpdateBuilder cores(int cores);

    /**
     * Number of security groups permitted
     */
    QuotaSetUpdateBuilder securityGroups(int securityGroups);

    /**
     * Number of rules per security group permitted
     */
    QuotaSetUpdateBuilder securityGroupRules(int securityGroupRules);

    /**
     * Injected file path name maximum length
     */
    QuotaSetUpdateBuilder injectedFilePathBytes(int injectedFilePathBytes);

    /**
     * Number of keypairs
     */
    QuotaSetUpdateBuilder keyPairs(int keyPairs);

}
