package org.openstack4j.openstack.provider;

import com.google.common.collect.Maps;
import org.openstack4j.api.APIProvider;
import org.openstack4j.api.compute.*;
import org.openstack4j.api.compute.QuotaSetService;
import org.openstack4j.api.compute.ext.*;
import org.openstack4j.api.exceptions.ApiNotFoundException;
import org.openstack4j.api.heat.*;
import org.openstack4j.api.identity.*;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.manila.*;
import org.openstack4j.api.networking.*;
import org.openstack4j.api.networking.ext.*;
import org.openstack4j.api.sahara.*;
import org.openstack4j.api.storage.*;
import org.openstack4j.api.telemetry.AlarmService;
import org.openstack4j.api.telemetry.EventService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.TelemetryService;
import org.openstack4j.openstack.compute.internal.*;
import org.openstack4j.openstack.compute.internal.QuotaSetServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.*;
import org.openstack4j.openstack.heat.internal.*;
import org.openstack4j.openstack.identity.internal.*;
import org.openstack4j.openstack.image.internal.ImageServiceImpl;
import org.openstack4j.openstack.manila.internal.*;
import org.openstack4j.openstack.networking.internal.*;
import org.openstack4j.openstack.networking.internal.ext.*;
import org.openstack4j.openstack.sahara.internal.*;
import org.openstack4j.openstack.storage.block.internal.*;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageAccountServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageContainerServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageObjectServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageServiceImpl;
import org.openstack4j.openstack.telemetry.internal.AlarmServiceImpl;
import org.openstack4j.openstack.telemetry.internal.EventServiceImpl;
import org.openstack4j.openstack.telemetry.internal.MeterServiceImpl;
import org.openstack4j.openstack.telemetry.internal.TelemetryServiceImpl;

import java.util.Map;

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
		bind(AlarmService.class, AlarmServiceImpl.class);
		bind(EventService.class, EventServiceImpl.class);
		bind(HypervisorService.class, HypervisorServiceImpl.class);
		bind(ZoneService.class, ZoneServiceImpl.class);
		bind(CinderZoneService.class, CinderZoneServiceImpl.class);
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
		bind(NetQuotaService.class, NetQuotaServiceImpl.class);
		bind(InterfaceService.class, InterfaceServiceImpl.class);
		bind(FloatingIPDNSService.class, FloatingIPDNSServiceImpl.class);
		bind(FloatingIPDNSDomainService.class, FloatingIPDNSDomainServiceImpl.class);
		bind(FloatingIPDNSEntryService.class, FloatingIPDNSEntryServiceImpl.class);
		bind(HostAggregateService.class,HostAggregateServiceImpl.class);
		bind(MemberService.class,MemberServiceImpl.class);
		bind(VipService.class,VipServiceImpl.class);
		bind(HealthMonitorService.class,HealthMonitorServiceImpl.class);
		bind(LbPoolService.class,LbPoolServiceImpl.class);
		bind(LoadBalancerService.class, LoadBalancerServiceImpl.class);
		bind(BlockVolumeTransferService.class, BlockVolumeTransferServiceImpl.class);
		bind(SaharaPluginService.class,SaharaPluginServiceImpl.class);
		bind(SaharaImageService.class,SaharaImageServiceImpl.class);
		bind(SaharaService.class,SaharaServiceImpl.class);
		bind(ClusterService.class,ClusterServiceImpl.class);
		bind(ClusterTemplateService.class,ClusterTemplateServiceImpl.class);
		bind(NodeGroupTemplateService.class,NodeGroupTemplateServiceImpl.class);
		bind(DataSourceService.class, DataSourceServiceImpl.class);
		bind(JobBinaryInternalService.class, JobBinaryInternalServiceImpl.class);
		bind(JobBinaryService.class, JobBinaryServiceImpl.class);
		bind(JobService.class, JobServiceImpl.class);
		bind(JobExecutionService.class, JobExecutionServiceImpl.class);
		bind(ShareService.class, ShareServiceImpl.class);
		bind(SecurityServiceService.class, SecurityServiceServiceImpl.class);
		bind(ShareSnapshotService.class, ShareSnapshotServiceImpl.class);
		bind(ShareNetworkService.class, ShareNetworkServiceImpl.class);
		bind(SharesService.class, SharesServiceImpl.class);
		bind(ShareServerService.class, ShareServerServiceImpl.class);
		bind(ShareInstanceService.class, ShareInstanceServiceImpl.class);
		bind(ShareTypeService.class, ShareTypeServiceImpl.class);
		bind(SchedulerStatsService.class, SchedulerStatsServiceImpl.class);
		bind(org.openstack4j.api.manila.QuotaSetService.class,
				org.openstack4j.openstack.manila.internal.QuotaSetServiceImpl.class);

		bind(BlockQuotaSetService.class, BlockQuotaSetServiceImpl.class);
		bind(FirewallAsService.class, FirewallAsServiceImpl.class);
		bind(FirewallService.class, FirewallServiceImpl.class);
		bind(FirewallRuleService.class, FirewallRuleServiceImpl.class);
		bind(FirewallPolicyService.class, FirewallPolicyServiceImpl.class);
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
