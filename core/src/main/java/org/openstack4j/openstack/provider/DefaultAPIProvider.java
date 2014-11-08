package org.openstack4j.openstack.provider;

import java.util.Map;

import org.openstack4j.api.APIProvider;
import org.openstack4j.api.compute.ComputeFloatingIPService;
import org.openstack4j.api.compute.ComputeImageService;
import org.openstack4j.api.compute.ComputeSecurityGroupService;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.api.compute.KeypairService;
import org.openstack4j.api.compute.QuotaSetService;
import org.openstack4j.api.compute.ServerGroupService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.api.compute.ext.HypervisorService;
import org.openstack4j.api.compute.ext.MigrationService;
import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.api.exceptions.ApiNotFoundException;
import org.openstack4j.api.heat.EventsService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.heat.ResourcesService;
import org.openstack4j.api.heat.SoftwareConfigService;
import org.openstack4j.api.heat.StackService;
import org.openstack4j.api.heat.TemplateService;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.identity.RoleService;
import org.openstack4j.api.identity.ServiceManagerService;
import org.openstack4j.api.identity.TenantService;
import org.openstack4j.api.identity.UserService;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.networking.NetFloatingIPService;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.networking.PortService;
import org.openstack4j.api.networking.RouterService;
import org.openstack4j.api.networking.SecurityGroupRuleService;
import org.openstack4j.api.networking.SecurityGroupService;
import org.openstack4j.api.networking.SubnetService;
import org.openstack4j.api.storage.BlockStorageService;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.api.storage.BlockVolumeSnapshotService;
import org.openstack4j.api.storage.ObjectStorageAccountService;
import org.openstack4j.api.storage.ObjectStorageContainerService;
import org.openstack4j.api.storage.ObjectStorageObjectService;
import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.TelemetryService;
import org.openstack4j.openstack.compute.internal.ComputeFloatingIPServiceImpl;
import org.openstack4j.openstack.compute.internal.ComputeImageServiceImpl;
import org.openstack4j.openstack.compute.internal.ComputeSecurityGroupServiceImpl;
import org.openstack4j.openstack.compute.internal.ComputeServiceImpl;
import org.openstack4j.openstack.compute.internal.FlavorServiceImpl;
import org.openstack4j.openstack.compute.internal.KeypairServiceImpl;
import org.openstack4j.openstack.compute.internal.QuotaSetServiceImpl;
import org.openstack4j.openstack.compute.internal.ServerGroupServiceImpl;
import org.openstack4j.openstack.compute.internal.ServerServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.HypervisorServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.MigrationServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.ZoneServiceImpl;
import org.openstack4j.openstack.heat.internal.EventsServiceImpl;
import org.openstack4j.openstack.heat.internal.HeatServiceImpl;
import org.openstack4j.openstack.heat.internal.ResourcesServiceImpl;
import org.openstack4j.openstack.heat.internal.SoftwareConfigServiceImpl;
import org.openstack4j.openstack.heat.internal.StackServiceImpl;
import org.openstack4j.openstack.heat.internal.TemplateServiceImpl;
import org.openstack4j.openstack.identity.internal.IdentityServiceImpl;
import org.openstack4j.openstack.identity.internal.RoleServiceImpl;
import org.openstack4j.openstack.identity.internal.ServiceManagerServiceImpl;
import org.openstack4j.openstack.identity.internal.TenantServiceImpl;
import org.openstack4j.openstack.identity.internal.UserServiceImpl;
import org.openstack4j.openstack.image.internal.ImageServiceImpl;
import org.openstack4j.openstack.networking.internal.FloatingIPServiceImpl;
import org.openstack4j.openstack.networking.internal.NetworkServiceImpl;
import org.openstack4j.openstack.networking.internal.NetworkingServiceImpl;
import org.openstack4j.openstack.networking.internal.PortServiceImpl;
import org.openstack4j.openstack.networking.internal.RouterServiceImpl;
import org.openstack4j.openstack.networking.internal.SecurityGroupRuleServiceImpl;
import org.openstack4j.openstack.networking.internal.SecurityGroupServiceImpl;
import org.openstack4j.openstack.networking.internal.SubnetServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockStorageServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockVolumeServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockVolumeSnapshotServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageAccountServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageContainerServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageObjectServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageServiceImpl;
import org.openstack4j.openstack.telemetry.internal.MeterServiceImpl;
import org.openstack4j.openstack.telemetry.internal.TelemetryServiceImpl;

import com.google.common.collect.Maps;

/**
 * Simple API Provider which keeps internally Maps interface implementations as singletons
 * 
 * @author Jeremy Unruh
 */
public class DefaultAPIProvider implements APIProvider {

	private static final Map<Class<?>, Class<?>> bindings = Maps.newHashMap();
	private static final Map<Class<?>, Object> instances = Maps.newConcurrentMap();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		bind(IdentityService.class, IdentityServiceImpl.class);
		bind(TenantService.class, TenantServiceImpl.class);
		bind(UserService.class, UserServiceImpl.class);
		bind(RoleService.class, RoleServiceImpl.class);
		bind(ServiceManagerService.class, ServiceManagerServiceImpl.class);
		bind(ComputeService.class, ComputeServiceImpl.class);
		bind(FlavorService.class, FlavorServiceImpl.class);
		bind(ComputeImageService.class, ComputeImageServiceImpl.class);
		bind(ServerService.class, ServerServiceImpl.class);
		bind(QuotaSetService.class, QuotaSetServiceImpl.class);
		bind(NetworkingService.class, NetworkingServiceImpl.class);
		bind(NetworkService.class, NetworkServiceImpl.class);
		bind(SubnetService.class, SubnetServiceImpl.class);
		bind(PortService.class, PortServiceImpl.class);
		bind(RouterService.class, RouterServiceImpl.class);
		bind(ImageService.class, ImageServiceImpl.class);
		bind(BlockStorageService.class, BlockStorageServiceImpl.class);
		bind(BlockVolumeService.class, BlockVolumeServiceImpl.class);
		bind(BlockVolumeSnapshotService.class, BlockVolumeSnapshotServiceImpl.class);
		bind(ComputeSecurityGroupService.class, ComputeSecurityGroupServiceImpl.class);
		bind(KeypairService.class, KeypairServiceImpl.class);
		bind(NetFloatingIPService.class, FloatingIPServiceImpl.class);
		bind(ComputeFloatingIPService.class, ComputeFloatingIPServiceImpl.class);
		bind(SecurityGroupService.class, SecurityGroupServiceImpl.class);
		bind(SecurityGroupRuleService.class, SecurityGroupRuleServiceImpl.class);
		bind(TelemetryService.class, TelemetryServiceImpl.class);
		bind(MeterService.class, MeterServiceImpl.class);
		bind(HypervisorService.class, HypervisorServiceImpl.class);
		bind(ZoneService.class, ZoneServiceImpl.class);
		bind(HeatService.class, HeatServiceImpl.class);
		bind(StackService.class, StackServiceImpl.class);
		bind(TemplateService.class, TemplateServiceImpl.class);
		bind(EventsService.class, EventsServiceImpl.class);
		bind(ResourcesService.class, ResourcesServiceImpl.class);
		bind(MigrationService.class, MigrationServiceImpl.class);
		bind(SoftwareConfigService.class, SoftwareConfigServiceImpl.class);
		bind(ObjectStorageService.class, ObjectStorageServiceImpl.class);
		bind(ObjectStorageAccountService.class, ObjectStorageAccountServiceImpl.class);
		bind(ObjectStorageContainerService.class, ObjectStorageContainerServiceImpl.class);
		bind(ServerGroupService.class, ServerGroupServiceImpl.class);
		bind(ObjectStorageObjectService.class, ObjectStorageObjectServiceImpl.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> api) {
		if (instances.containsKey(api))
			return (T) instances.get(api);
		
		if (bindings.containsKey(api)) {
			try {
				T impl = (T) bindings.get(api).newInstance();
				instances.put(api, impl);
				return impl;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ApiNotFoundException("API Not found for: " + api.getName(), e);
			}
		}
		throw new ApiNotFoundException("API Not found for: " + api.getName());
	}

	private void bind(Class<?> api, Class<?> impl)
	{
		bindings.put(api, impl);
	}
}
