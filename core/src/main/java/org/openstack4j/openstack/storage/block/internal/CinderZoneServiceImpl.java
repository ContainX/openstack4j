package org.openstack4j.openstack.storage.block.internal;

import java.util.List;

import org.openstack4j.api.storage.CinderZoneService;
import org.openstack4j.openstack.storage.block.domain.AvailabilityZone;
import org.openstack4j.openstack.storage.block.domain.ExtAvailabilityZone.AvailabilityZones;

public class CinderZoneServiceImpl  extends BaseBlockStorageServices implements CinderZoneService
{

    @Override
    public List<? extends AvailabilityZone> list()
    {
        String uri =  "/os-availability-zone";
        return get(AvailabilityZones.class, uri).execute().getList();
    }


}
