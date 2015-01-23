package org.openstack4j.openstack.sahara.internal;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.api.sahara.SaharaService;
import org.openstack4j.api.sahara.ClusterService;

/**
 * Sahara (Data Processing) Operations API implementation
 * 
 * @author Ekasit Kijsipongse
 */
public class SaharaServiceImpl extends BaseSaharaServices implements SaharaService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClusterService clusters() {
		return Apis.get(ClusterService.class);
	}

}
