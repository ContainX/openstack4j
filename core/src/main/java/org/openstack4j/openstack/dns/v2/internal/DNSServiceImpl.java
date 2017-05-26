package org.openstack4j.openstack.dns.v2.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.dns.v2.DNSService;
import org.openstack4j.api.dns.v2.RecordsetService;
import org.openstack4j.api.dns.v2.ZoneService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.openstack.common.ExtensionValue.ExtensionList;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.List;


/**
 * DNS/Designate V2 service implementation
 *
 */
public class DNSServiceImpl extends BaseDNSServices implements DNSService {

    @Override
    public ZoneService zones() {
        return Apis.get(ZoneService.class);
    }

    @Override
    public RecordsetService recordsets() {
        return Apis.get(RecordsetService.class);
    }
}
