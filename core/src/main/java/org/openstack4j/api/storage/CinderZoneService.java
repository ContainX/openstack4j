package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.storage.block.domain.AvailabilityZone;

/**
  * @author Jeff Hu
  * list all available cinder zones
  */
public interface CinderZoneService extends RestService {

	List<? extends AvailabilityZone> list();
}
