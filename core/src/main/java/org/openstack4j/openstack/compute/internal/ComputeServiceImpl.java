package org.openstack4j.openstack.compute.internal;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.api.compute.ComputeFloatingIPService;
import org.openstack4j.api.compute.ComputeImageService;
import org.openstack4j.api.compute.ComputeSecurityGroupService;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.api.compute.HostAggregateService;
import org.openstack4j.api.compute.HostService;
import org.openstack4j.api.compute.KeypairService;
import org.openstack4j.api.compute.QuotaSetService;
import org.openstack4j.api.compute.ServerGroupService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.api.compute.ServerTagService;
import org.openstack4j.api.compute.ext.FloatingIPDNSService;
import org.openstack4j.api.compute.ext.HypervisorService;
import org.openstack4j.api.compute.ext.MigrationService;
import org.openstack4j.api.compute.ext.ServicesService;
import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.openstack.common.ExtensionValue.Extensions;

/**
 * Compute (Nova) Operations API implementation
 * 
 * @author Jeremy Unruh
 */
public class ComputeServiceImpl extends BaseComputeServices implements ComputeService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FlavorService flavors() {
		return Apis.get(FlavorService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeImageService images() {
		return Apis.get(ComputeImageService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServerService servers() {
		return Apis.get(ServerService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuotaSetService quotaSets() {
		return Apis.get(QuotaSetService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostService host() {
		return Apis.get(HostService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Extension> listExtensions() {
		return get(Extensions.class, uri("/extensions")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeFloatingIPService floatingIps() {
		return Apis.get(ComputeFloatingIPService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeSecurityGroupService securityGroups() {
		return Apis.get(ComputeSecurityGroupService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KeypairService keypairs() {
		return Apis.get(KeypairService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HypervisorService hypervisors() {
		return Apis.get(HypervisorService.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ZoneService zones() {
		return Apis.get(ZoneService.class);
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public MigrationService migrations() {
        return Apis.get(MigrationService.class);
    }

	@Override
	public ServerGroupService serverGroups() {
		 return Apis.get(ServerGroupService.class);
	}

    @Override
    public FloatingIPDNSService floatingIPDNS() {
        return Apis.get(FloatingIPDNSService.class);
    }

	@Override
	public HostAggregateService hostAggregates() {
		
		return Apis.get(HostAggregateService.class);
	}

	@Override
	public ServerTagService serverTags() {
		return Apis.get(ServerTagService.class);
	}
	
    @Override
    public ServicesService services() {
        return Apis.get(ServicesService.class);
    }
    
}
