package org.openstack4j.model.magnum;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.openstack.common.GenericLink;

public interface CertificateBuilder extends Builder<CertificateBuilder, Certificate> {
    /**
     * @see Certificate#getPem
     */
    CertificateBuilder pem(String pem);

    /**
     * @see Certificate#getBayUuid
     */
    CertificateBuilder bayUuid(String bayUuid);

    /**
     * @see Certificate#getLinks
     */
    CertificateBuilder links(List<GenericLink> links);

}
