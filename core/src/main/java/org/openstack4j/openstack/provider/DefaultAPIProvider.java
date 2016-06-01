package org.openstack4j.openstack.provider;

import java.util.Map;

import org.openstack4j.api.APIProvider;
import org.openstack4j.api.compute.ComputeFloatingIPService;
import org.openstack4j.api.compute.ComputeImageService;
import org.openstack4j.api.compute.ComputeSecurityGroupService;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.api.compute.HostAggregateService;
import org.openstack4j.api.compute.KeypairService;
import org.openstack4j.api.compute.QuotaSetService;
import org.openstack4j.api.compute.ServerGroupService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.api.compute.ext.FloatingIPDNSDomainService;
import org.openstack4j.api.compute.ext.FloatingIPDNSEntryService;
import org.openstack4j.api.compute.ext.FloatingIPDNSService;
import org.openstack4j.api.compute.ext.HypervisorService;
import org.openstack4j.api.compute.ext.InterfaceService;
import org.openstack4j.api.compute.ext.MigrationService;
import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.api.exceptions.ApiNotFoundException;
import org.openstack4j.api.gbp.ExternalPolicyService;
import org.openstack4j.api.gbp.ExternalSegmentService;
import org.openstack4j.api.gbp.GbpService;
import org.openstack4j.api.gbp.L2policyService;
import org.openstack4j.api.gbp.L3policyService;
import org.openstack4j.api.gbp.NatPoolService;
import org.openstack4j.api.gbp.PolicyActionService;
import org.openstack4j.api.gbp.PolicyClassifierService;
import org.openstack4j.api.gbp.PolicyRuleService;
import org.openstack4j.api.gbp.PolicyRuleSetService;
import org.openstack4j.api.gbp.PolicyTargetService;
import org.openstack4j.api.gbp.ServiceProfileService;
import org.openstack4j.api.gbp.ServicechainService;
import org.openstack4j.api.heat.EventsService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.heat.ResourcesService;
import org.openstack4j.api.heat.SoftwareConfigService;
import org.openstack4j.api.heat.StackService;
import org.openstack4j.api.heat.TemplateService;
import org.openstack4j.api.identity.v2.ServiceManagerService;
import org.openstack4j.api.identity.v2.TenantService;
import org.openstack4j.api.identity.v3.CredentialService;
import org.openstack4j.api.identity.v3.DomainService;
import org.openstack4j.api.identity.v3.GroupService;
import org.openstack4j.api.identity.v3.PolicyService;
import org.openstack4j.api.identity.v3.ProjectService;
import org.openstack4j.api.identity.v3.RegionService;
import org.openstack4j.api.identity.v3.RoleService;
import org.openstack4j.api.identity.v3.ServiceEndpointService;
import org.openstack4j.api.identity.v3.TokenService;
import org.openstack4j.api.identity.v3.UserService;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.manila.SchedulerStatsService;
import org.openstack4j.api.manila.SecurityServiceService;
import org.openstack4j.api.manila.ShareInstanceService;
import org.openstack4j.api.manila.ShareNetworkService;
import org.openstack4j.api.manila.ShareServerService;
import org.openstack4j.api.manila.ShareService;
import org.openstack4j.api.manila.ShareSnapshotService;
import org.openstack4j.api.manila.ShareTypeService;
import org.openstack4j.api.manila.SharesService;
import org.openstack4j.api.networking.NetFloatingIPService;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.networking.PortService;
import org.openstack4j.api.networking.RouterService;
import org.openstack4j.api.networking.SecurityGroupRuleService;
import org.openstack4j.api.networking.SecurityGroupService;
import org.openstack4j.api.networking.SubnetService;
import org.openstack4j.api.networking.ext.FirewallAsService;
import org.openstack4j.api.networking.ext.FirewallPolicyService;
import org.openstack4j.api.networking.ext.FirewallRuleService;
import org.openstack4j.api.networking.ext.FirewallService;
import org.openstack4j.api.networking.ext.HealthMonitorService;
import org.openstack4j.api.networking.ext.LbPoolService;
import org.openstack4j.api.networking.ext.LoadBalancerService;
import org.openstack4j.api.networking.ext.MemberService;
import org.openstack4j.api.networking.ext.NetQuotaService;
import org.openstack4j.api.networking.ext.VipService;
import org.openstack4j.api.sahara.ClusterService;
import org.openstack4j.api.sahara.ClusterTemplateService;
import org.openstack4j.api.sahara.DataSourceService;
import org.openstack4j.api.sahara.JobBinaryInternalService;
import org.openstack4j.api.sahara.JobBinaryService;
import org.openstack4j.api.sahara.JobExecutionService;
import org.openstack4j.api.sahara.JobService;
import org.openstack4j.api.sahara.NodeGroupTemplateService;
import org.openstack4j.api.sahara.SaharaImageService;
import org.openstack4j.api.sahara.SaharaPluginService;
import org.openstack4j.api.sahara.SaharaService;
import org.openstack4j.api.storage.BlockQuotaSetService;
import org.openstack4j.api.storage.BlockStorageService;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.api.storage.BlockVolumeSnapshotService;
import org.openstack4j.api.storage.BlockVolumeTransferService;
import org.openstack4j.api.storage.CinderZoneService;
import org.openstack4j.api.storage.ObjectStorageAccountService;
import org.openstack4j.api.storage.ObjectStorageContainerService;
import org.openstack4j.api.storage.ObjectStorageObjectService;
import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.api.telemetry.AlarmService;
import org.openstack4j.api.telemetry.EventService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.TelemetryService;
import org.openstack4j.openstack.compute.internal.ComputeFloatingIPServiceImpl;
import org.openstack4j.openstack.compute.internal.ComputeImageServiceImpl;
import org.openstack4j.openstack.compute.internal.ComputeSecurityGroupServiceImpl;
import org.openstack4j.openstack.compute.internal.ComputeServiceImpl;
import org.openstack4j.openstack.compute.internal.FlavorServiceImpl;
import org.openstack4j.openstack.compute.internal.HostAggregateServiceImpl;
import org.openstack4j.openstack.compute.internal.KeypairServiceImpl;
import org.openstack4j.openstack.compute.internal.QuotaSetServiceImpl;
import org.openstack4j.openstack.compute.internal.ServerGroupServiceImpl;
import org.openstack4j.openstack.compute.internal.ServerServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.FloatingIPDNSDomainServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.FloatingIPDNSEntryServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.FloatingIPDNSServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.HypervisorServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.InterfaceServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.MigrationServiceImpl;
import org.openstack4j.openstack.compute.internal.ext.ZoneServiceImpl;
import org.openstack4j.openstack.gbp.internal.ExternalPolicyServiceImpl;
import org.openstack4j.openstack.gbp.internal.ExternalSegmentServiceImpl;
import org.openstack4j.openstack.gbp.internal.GbpServiceImpl;
import org.openstack4j.openstack.gbp.internal.L2policyServiceImpl;
import org.openstack4j.openstack.gbp.internal.L3policyServiceImpl;
import org.openstack4j.openstack.gbp.internal.NatPoolServiceImpl;
import org.openstack4j.openstack.gbp.internal.PolicyActionServiceImpl;
import org.openstack4j.openstack.gbp.internal.PolicyClassifierServiceImpl;
import org.openstack4j.openstack.gbp.internal.PolicyRuleServiceImpl;
import org.openstack4j.openstack.gbp.internal.PolicyRuleSetServiceImpl;
import org.openstack4j.openstack.gbp.internal.PolicyTargetServiceImpl;
import org.openstack4j.openstack.gbp.internal.ServiceProfileServiceImpl;
import org.openstack4j.openstack.gbp.internal.ServicechainServiceImpl;
import org.openstack4j.openstack.heat.internal.EventsServiceImpl;
import org.openstack4j.openstack.heat.internal.HeatServiceImpl;
import org.openstack4j.openstack.heat.internal.ResourcesServiceImpl;
import org.openstack4j.openstack.heat.internal.SoftwareConfigServiceImpl;
import org.openstack4j.openstack.heat.internal.StackServiceImpl;
import org.openstack4j.openstack.heat.internal.TemplateServiceImpl;
import org.openstack4j.openstack.identity.v2.internal.ServiceManagerServiceImpl;
import org.openstack4j.openstack.identity.v2.internal.TenantServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.CredentialServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.DomainServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.GroupServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.PolicyServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.ProjectServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.RegionServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.RoleServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.ServiceEndpointServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.TokenServiceImpl;
import org.openstack4j.openstack.identity.v3.internal.UserServiceImpl;
import org.openstack4j.openstack.image.internal.ImageServiceImpl;
import org.openstack4j.openstack.manila.internal.SchedulerStatsServiceImpl;
import org.openstack4j.openstack.manila.internal.SecurityServiceServiceImpl;
import org.openstack4j.openstack.manila.internal.ShareInstanceServiceImpl;
import org.openstack4j.openstack.manila.internal.ShareNetworkServiceImpl;
import org.openstack4j.openstack.manila.internal.ShareServerServiceImpl;
import org.openstack4j.openstack.manila.internal.ShareServiceImpl;
import org.openstack4j.openstack.manila.internal.ShareSnapshotServiceImpl;
import org.openstack4j.openstack.manila.internal.ShareTypeServiceImpl;
import org.openstack4j.openstack.manila.internal.SharesServiceImpl;
import org.openstack4j.openstack.networking.internal.FloatingIPServiceImpl;
import org.openstack4j.openstack.networking.internal.NetworkServiceImpl;
import org.openstack4j.openstack.networking.internal.NetworkingServiceImpl;
import org.openstack4j.openstack.networking.internal.PortServiceImpl;
import org.openstack4j.openstack.networking.internal.RouterServiceImpl;
import org.openstack4j.openstack.networking.internal.SecurityGroupRuleServiceImpl;
import org.openstack4j.openstack.networking.internal.SecurityGroupServiceImpl;
import org.openstack4j.openstack.networking.internal.SubnetServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.FirewallAsServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.FirewallPolicyServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.FirewallRuleServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.FirewallServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.HealthMonitorServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.LbPoolServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.LoadBalancerServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.MemberServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.NetQuotaServiceImpl;
import org.openstack4j.openstack.networking.internal.ext.VipServiceImpl;
import org.openstack4j.openstack.sahara.internal.ClusterServiceImpl;
import org.openstack4j.openstack.sahara.internal.ClusterTemplateServiceImpl;
import org.openstack4j.openstack.sahara.internal.DataSourceServiceImpl;
import org.openstack4j.openstack.sahara.internal.JobBinaryInternalServiceImpl;
import org.openstack4j.openstack.sahara.internal.JobBinaryServiceImpl;
import org.openstack4j.openstack.sahara.internal.JobExecutionServiceImpl;
import org.openstack4j.openstack.sahara.internal.JobServiceImpl;
import org.openstack4j.openstack.sahara.internal.NodeGroupTemplateServiceImpl;
import org.openstack4j.openstack.sahara.internal.SaharaImageServiceImpl;
import org.openstack4j.openstack.sahara.internal.SaharaPluginServiceImpl;
import org.openstack4j.openstack.sahara.internal.SaharaServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockQuotaSetServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockStorageServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockVolumeServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockVolumeSnapshotServiceImpl;
import org.openstack4j.openstack.storage.block.internal.BlockVolumeTransferServiceImpl;
import org.openstack4j.openstack.storage.block.internal.CinderZoneServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageAccountServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageContainerServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageObjectServiceImpl;
import org.openstack4j.openstack.storage.object.internal.ObjectStorageServiceImpl;
import org.openstack4j.openstack.telemetry.internal.AlarmServiceImpl;
import org.openstack4j.openstack.telemetry.internal.EventServiceImpl;
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
        bind(org.openstack4j.api.identity.v2.IdentityService.class, org.openstack4j.openstack.identity.v2.internal.IdentityServiceImpl.class);
        bind(TenantService.class, TenantServiceImpl.class);
        bind(ServiceManagerService.class, ServiceManagerServiceImpl.class);
        bind(org.openstack4j.api.identity.v2.UserService.class, org.openstack4j.openstack.identity.v2.internal.UserServiceImpl.class);
        bind(org.openstack4j.api.identity.v2.RoleService.class, org.openstack4j.openstack.identity.v2.internal.RoleServiceImpl.class);
        bind(org.openstack4j.api.identity.v3.IdentityService.class, org.openstack4j.openstack.identity.v3.internal.IdentityServiceImpl.class);
        bind(ServiceEndpointService.class, ServiceEndpointServiceImpl.class);
        bind(CredentialService.class, CredentialServiceImpl.class);
        bind(UserService.class, UserServiceImpl.class);
        bind(ProjectService.class, ProjectServiceImpl.class);
        bind(RoleService.class, RoleServiceImpl.class);
        bind(DomainService.class, DomainServiceImpl.class);
        bind(GroupService.class, GroupServiceImpl.class);
        bind(PolicyService.class, PolicyServiceImpl.class);
        bind(RegionService.class, RegionServiceImpl.class);
        bind(TokenService.class, TokenServiceImpl.class);
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
        bind(HostAggregateService.class, HostAggregateServiceImpl.class);
        bind(MemberService.class, MemberServiceImpl.class);
        bind(VipService.class, VipServiceImpl.class);
        bind(HealthMonitorService.class, HealthMonitorServiceImpl.class);
        bind(LbPoolService.class, LbPoolServiceImpl.class);
        bind(LoadBalancerService.class, LoadBalancerServiceImpl.class);
        bind(BlockVolumeTransferService.class, BlockVolumeTransferServiceImpl.class);
        bind(SaharaPluginService.class, SaharaPluginServiceImpl.class);
        bind(SaharaImageService.class, SaharaImageServiceImpl.class);
        bind(SaharaService.class, SaharaServiceImpl.class);
        bind(ClusterService.class, ClusterServiceImpl.class);
        bind(ClusterTemplateService.class, ClusterTemplateServiceImpl.class);
        bind(NodeGroupTemplateService.class, NodeGroupTemplateServiceImpl.class);
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
		bind(GbpService.class,GbpServiceImpl.class);
		bind(ExternalPolicyService.class,ExternalPolicyServiceImpl.class);
		bind(ExternalSegmentService.class,ExternalSegmentServiceImpl.class);
		bind(org.openstack4j.api.gbp.GroupService.class,org.openstack4j.openstack.gbp.internal.GroupServiceImpl.class);
		bind(L2policyService.class,L2policyServiceImpl.class);
		bind(L3policyService.class,L3policyServiceImpl.class); 
		bind(NatPoolService.class,NatPoolServiceImpl.class);
		bind(NetworkService.class,NetworkServiceImpl.class);
		bind(PolicyActionService.class,PolicyActionServiceImpl.class);
		bind(PolicyRuleService.class,PolicyRuleServiceImpl.class);
		bind(PolicyRuleSetService.class,PolicyRuleSetServiceImpl.class);
		bind(PolicyTargetService.class,PolicyTargetServiceImpl.class);
		bind(PolicyClassifierService.class,PolicyClassifierServiceImpl.class);
		bind(ServicechainService.class,ServicechainServiceImpl.class);
		bind(ServiceProfileService.class,ServiceProfileServiceImpl.class);
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
            } catch (Exception e) {
                throw new ApiNotFoundException("API Not found for: " + api.getName(), e);
            }
        }
        throw new ApiNotFoundException("API Not found for: " + api.getName());
    }

    private void bind(Class<?> api, Class<?> impl) {
        bindings.put(api, impl);
    }
}
