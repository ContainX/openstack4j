/*******************************************************************************
 *  Copyright 2017 Huawei TLD
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.provider;

import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.APIProvider;
import com.huawei.openstack4j.api.artifact.ArtifactService;
import com.huawei.openstack4j.api.artifact.ToscaTemplatesArtifactService;
import com.huawei.openstack4j.api.barbican.BarbicanService;
import com.huawei.openstack4j.api.barbican.ContainerService;
import com.huawei.openstack4j.api.cloudeye.CloudEyeService;
import com.huawei.openstack4j.api.cloudeye.MetricDataService;
import com.huawei.openstack4j.api.cloudeye.MetricService;
import com.huawei.openstack4j.api.cloudeye.QuotaService;
import com.huawei.openstack4j.api.compute.ComputeFloatingIPService;
import com.huawei.openstack4j.api.compute.ComputeImageService;
import com.huawei.openstack4j.api.compute.ComputeSecurityGroupService;
import com.huawei.openstack4j.api.compute.ComputeService;
import com.huawei.openstack4j.api.compute.FlavorService;
import com.huawei.openstack4j.api.compute.HostAggregateService;
import com.huawei.openstack4j.api.compute.HostService;
import com.huawei.openstack4j.api.compute.KeypairService;
import com.huawei.openstack4j.api.compute.QuotaSetService;
import com.huawei.openstack4j.api.compute.ServerGroupService;
import com.huawei.openstack4j.api.compute.ServerService;
import com.huawei.openstack4j.api.compute.ServerTagService;
import com.huawei.openstack4j.api.compute.ServerV1Service;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSDomainService;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSEntryService;
import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSService;
import com.huawei.openstack4j.api.compute.ext.HypervisorService;
import com.huawei.openstack4j.api.compute.ext.InstanceActionsService;
import com.huawei.openstack4j.api.compute.ext.InterfaceService;
import com.huawei.openstack4j.api.compute.ext.MigrationService;
import com.huawei.openstack4j.api.compute.ext.ServicesService;
import com.huawei.openstack4j.api.compute.ext.ZoneService;
import com.huawei.openstack4j.api.dns.v2.DNSService;
import com.huawei.openstack4j.api.dns.v2.PTRService;
import com.huawei.openstack4j.api.dns.v2.RecordsetService;
import com.huawei.openstack4j.api.exceptions.ApiNotFoundException;
import com.huawei.openstack4j.api.gbp.ExternalPolicyService;
import com.huawei.openstack4j.api.gbp.ExternalSegmentService;
import com.huawei.openstack4j.api.gbp.GbpService;
import com.huawei.openstack4j.api.gbp.L2policyService;
import com.huawei.openstack4j.api.gbp.L3policyService;
import com.huawei.openstack4j.api.gbp.NatPoolService;
import com.huawei.openstack4j.api.gbp.NetworkPolicyService;
import com.huawei.openstack4j.api.gbp.PolicyActionService;
import com.huawei.openstack4j.api.gbp.PolicyClassifierService;
import com.huawei.openstack4j.api.gbp.PolicyRuleService;
import com.huawei.openstack4j.api.gbp.PolicyRuleSetService;
import com.huawei.openstack4j.api.gbp.PolicyTargetService;
import com.huawei.openstack4j.api.gbp.ServiceProfileService;
import com.huawei.openstack4j.api.gbp.ServicechainService;
import com.huawei.openstack4j.api.heat.EventsService;
import com.huawei.openstack4j.api.heat.HeatService;
import com.huawei.openstack4j.api.heat.ResourcesService;
import com.huawei.openstack4j.api.heat.SoftwareConfigService;
import com.huawei.openstack4j.api.heat.StackService;
import com.huawei.openstack4j.api.heat.TemplateService;
import com.huawei.openstack4j.api.identity.v2.ServiceManagerService;
import com.huawei.openstack4j.api.identity.v2.TenantService;
import com.huawei.openstack4j.api.identity.v3.CredentialService;
import com.huawei.openstack4j.api.identity.v3.DomainService;
import com.huawei.openstack4j.api.identity.v3.GroupService;
import com.huawei.openstack4j.api.identity.v3.PolicyService;
import com.huawei.openstack4j.api.identity.v3.ProjectService;
import com.huawei.openstack4j.api.identity.v3.RegionService;
import com.huawei.openstack4j.api.identity.v3.RoleService;
import com.huawei.openstack4j.api.identity.v3.ServiceEndpointService;
import com.huawei.openstack4j.api.identity.v3.TokenService;
import com.huawei.openstack4j.api.identity.v3.UserService;
import com.huawei.openstack4j.api.image.ImageService;
import com.huawei.openstack4j.api.image.v2.TaskService;
import com.huawei.openstack4j.api.loadbalance.AsyncJobService;
import com.huawei.openstack4j.api.loadbalance.ELBCertificateService;
import com.huawei.openstack4j.api.loadbalance.ELBHealthCheckService;
import com.huawei.openstack4j.api.loadbalance.ELBListenerService;
import com.huawei.openstack4j.api.loadbalance.ELBLoadBalancerService;
import com.huawei.openstack4j.api.loadbalance.ELBQuotaService;
import com.huawei.openstack4j.api.loadbalance.ELBServerService;
import com.huawei.openstack4j.api.loadbalance.ELBService;
import com.huawei.openstack4j.api.magnum.MagnumService;
import com.huawei.openstack4j.api.manila.SchedulerStatsService;
import com.huawei.openstack4j.api.manila.SecurityServiceService;
import com.huawei.openstack4j.api.manila.ShareInstanceService;
import com.huawei.openstack4j.api.manila.ShareNetworkService;
import com.huawei.openstack4j.api.manila.ShareServerService;
import com.huawei.openstack4j.api.manila.ShareService;
import com.huawei.openstack4j.api.manila.ShareSnapshotService;
import com.huawei.openstack4j.api.manila.ShareTypeService;
import com.huawei.openstack4j.api.manila.SharesService;
import com.huawei.openstack4j.api.map.reduce.ClusterService;
import com.huawei.openstack4j.api.map.reduce.ClusterTemplateService;
import com.huawei.openstack4j.api.map.reduce.DataSourceService;
import com.huawei.openstack4j.api.map.reduce.JobBinaryInternalService;
import com.huawei.openstack4j.api.map.reduce.JobBinaryService;
import com.huawei.openstack4j.api.map.reduce.JobExecutionService;
import com.huawei.openstack4j.api.map.reduce.JobService;
import com.huawei.openstack4j.api.map.reduce.MapReduceImageService;
import com.huawei.openstack4j.api.map.reduce.MapReducePluginService;
import com.huawei.openstack4j.api.map.reduce.MapReduceService;
import com.huawei.openstack4j.api.map.reduce.NodeGroupTemplateService;
import com.huawei.openstack4j.api.murano.v1.AppCatalogService;
import com.huawei.openstack4j.api.murano.v1.MuranoActionService;
import com.huawei.openstack4j.api.murano.v1.MuranoApplicationService;
import com.huawei.openstack4j.api.murano.v1.MuranoDeploymentService;
import com.huawei.openstack4j.api.murano.v1.MuranoEnvironmentService;
import com.huawei.openstack4j.api.murano.v1.MuranoSessionService;
import com.huawei.openstack4j.api.networking.NetFloatingIPService;
import com.huawei.openstack4j.api.networking.NetworkService;
import com.huawei.openstack4j.api.networking.NetworkingService;
import com.huawei.openstack4j.api.networking.PortService;
import com.huawei.openstack4j.api.networking.RouterService;
import com.huawei.openstack4j.api.networking.SecurityGroupRuleService;
import com.huawei.openstack4j.api.networking.SecurityGroupService;
import com.huawei.openstack4j.api.networking.SubnetService;
import com.huawei.openstack4j.api.networking.ext.AgentService;
import com.huawei.openstack4j.api.networking.ext.FirewallAsService;
import com.huawei.openstack4j.api.networking.ext.FirewallPolicyService;
import com.huawei.openstack4j.api.networking.ext.FirewallRuleService;
import com.huawei.openstack4j.api.networking.ext.FirewallService;
import com.huawei.openstack4j.api.networking.ext.HealthMonitorService;
import com.huawei.openstack4j.api.networking.ext.HealthMonitorV2Service;
import com.huawei.openstack4j.api.networking.ext.LbPoolService;
import com.huawei.openstack4j.api.networking.ext.LbPoolV2Service;
import com.huawei.openstack4j.api.networking.ext.LbaasV2Service;
import com.huawei.openstack4j.api.networking.ext.ListenerV2Service;
import com.huawei.openstack4j.api.networking.ext.LoadBalancerService;
import com.huawei.openstack4j.api.networking.ext.LoadBalancerV2Service;
import com.huawei.openstack4j.api.networking.ext.MemberService;
import com.huawei.openstack4j.api.networking.ext.NetQuotaService;
import com.huawei.openstack4j.api.networking.ext.VipService;
import com.huawei.openstack4j.api.scaling.AutoScalingActivityLogService;
import com.huawei.openstack4j.api.scaling.AutoScalingConfigService;
import com.huawei.openstack4j.api.scaling.AutoScalingGroupInstanceService;
import com.huawei.openstack4j.api.scaling.AutoScalingGroupService;
import com.huawei.openstack4j.api.scaling.AutoScalingPolicyService;
import com.huawei.openstack4j.api.scaling.AutoScalingQuotaService;
import com.huawei.openstack4j.api.scaling.AutoScalingService;
import com.huawei.openstack4j.api.senlin.SenlinActionService;
import com.huawei.openstack4j.api.senlin.SenlinBuildInfoService;
import com.huawei.openstack4j.api.senlin.SenlinClusterPolicyService;
import com.huawei.openstack4j.api.senlin.SenlinClusterService;
import com.huawei.openstack4j.api.senlin.SenlinEventService;
import com.huawei.openstack4j.api.senlin.SenlinNodeService;
import com.huawei.openstack4j.api.senlin.SenlinPolicyService;
import com.huawei.openstack4j.api.senlin.SenlinPolicyTypeService;
import com.huawei.openstack4j.api.senlin.SenlinProfileService;
import com.huawei.openstack4j.api.senlin.SenlinProfileTypeService;
import com.huawei.openstack4j.api.senlin.SenlinReceiverService;
import com.huawei.openstack4j.api.senlin.SenlinService;
import com.huawei.openstack4j.api.senlin.SenlinVersionService;
import com.huawei.openstack4j.api.senlin.SenlinWebHookService;
import com.huawei.openstack4j.api.storage.AsyncVolumeBackupJobService;
import com.huawei.openstack4j.api.storage.AsyncVolumeBackupService;
import com.huawei.openstack4j.api.storage.BlockQuotaSetService;
import com.huawei.openstack4j.api.storage.BlockStorageService;
import com.huawei.openstack4j.api.storage.BlockVolumeBackupPolicyService;
import com.huawei.openstack4j.api.storage.BlockVolumeBackupService;
import com.huawei.openstack4j.api.storage.BlockVolumeService;
import com.huawei.openstack4j.api.storage.BlockVolumeSnapshotService;
import com.huawei.openstack4j.api.storage.BlockVolumeTransferService;
import com.huawei.openstack4j.api.storage.CinderZoneService;
import com.huawei.openstack4j.api.storage.ObjectStorageAccountService;
import com.huawei.openstack4j.api.storage.ObjectStorageContainerService;
import com.huawei.openstack4j.api.storage.ObjectStorageObjectService;
import com.huawei.openstack4j.api.storage.ObjectStorageService;
import com.huawei.openstack4j.api.storage.SchedulerStatsGetPoolService;
import com.huawei.openstack4j.api.storage.ext.BlockStorageServiceService;
import com.huawei.openstack4j.api.tacker.TackerService;
import com.huawei.openstack4j.api.tacker.TackerServiceImpl;
import com.huawei.openstack4j.api.tacker.VimService;
import com.huawei.openstack4j.api.tacker.VnfService;
import com.huawei.openstack4j.api.tacker.VnfdService;
import com.huawei.openstack4j.api.telemetry.AlarmAodhService;
import com.huawei.openstack4j.api.telemetry.AlarmService;
import com.huawei.openstack4j.api.telemetry.CapabilitiesService;
import com.huawei.openstack4j.api.telemetry.EventService;
import com.huawei.openstack4j.api.telemetry.MeterService;
import com.huawei.openstack4j.api.telemetry.ResourceService;
import com.huawei.openstack4j.api.telemetry.SampleService;
import com.huawei.openstack4j.api.telemetry.TelemetryAodhService;
import com.huawei.openstack4j.api.telemetry.TelemetryService;
import com.huawei.openstack4j.api.workflow.ActionDefinitionService;
import com.huawei.openstack4j.api.workflow.WorkbookDefinitionService;
import com.huawei.openstack4j.api.workflow.WorkflowDefinitionService;
import com.huawei.openstack4j.api.workflow.WorkflowService;
import com.huawei.openstack4j.openstack.antiddos.internal.AntiDDoSService;
import com.huawei.openstack4j.openstack.antiddos.internal.AntiDDoSServices;
import com.huawei.openstack4j.openstack.antiddos.internal.AntiDDoSWarnService;
import com.huawei.openstack4j.openstack.artifact.internal.ArtifactServiceImpl;
import com.huawei.openstack4j.openstack.artifact.internal.ToscaTemplatesArtifactServiceImpl;
import com.huawei.openstack4j.openstack.barbican.internal.BarbicanServiceImpl;
import com.huawei.openstack4j.openstack.barbican.internal.ContainerServiceImpl;
import com.huawei.openstack4j.openstack.cloud.trace.v1.internal.CloudTraceV1Service;
import com.huawei.openstack4j.openstack.cloud.trace.v1.internal.TraceService;
import com.huawei.openstack4j.openstack.cloud.trace.v1.internal.TrackerService;
import com.huawei.openstack4j.openstack.cloud.trace.v2.internal.CloudTraceV2Service;
import com.huawei.openstack4j.openstack.cloudeye.internal.CloudEyeAlarmServiceImpl;
import com.huawei.openstack4j.openstack.cloudeye.internal.CloudEyeMetricDataServiceImpl;
import com.huawei.openstack4j.openstack.cloudeye.internal.CloudEyeMetricServiceImpl;
import com.huawei.openstack4j.openstack.cloudeye.internal.CloudEyeQuotaServiceImpl;
import com.huawei.openstack4j.openstack.cloudeye.internal.CloudEyeServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ComputeFloatingIPServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ComputeImageServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ComputeSecurityGroupServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ComputeServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.FlavorServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.HostAggregateServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.HostServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.KeypairServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.QuotaSetServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ServerGroupServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ServerServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ServerTagServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ServerV1ServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ServicesServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.FloatingIPDNSDomainServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.FloatingIPDNSEntryServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.FloatingIPDNSServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.HypervisorServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.InstanceActionsServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.InterfaceServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.MigrationServiceImpl;
import com.huawei.openstack4j.openstack.compute.internal.ext.ZoneServiceImpl;
import com.huawei.openstack4j.openstack.database.internal.DatabaseBackupService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseInstanceFlavorService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseInstanceService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseLogService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseParamService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseServiceVersionService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseServices;
import com.huawei.openstack4j.openstack.database.internal.DatastoreService;
import com.huawei.openstack4j.openstack.dns.v2.internal.DNSServiceImpl;
import com.huawei.openstack4j.openstack.dns.v2.internal.PTRServiceImpl;
import com.huawei.openstack4j.openstack.dns.v2.internal.RecordsetServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.ExternalPolicyServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.ExternalSegmentServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.GbpServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.L2policyServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.L3policyServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.NatPoolServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.NetworkPolicyServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.PolicyActionServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.PolicyClassifierServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.PolicyRuleServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.PolicyRuleSetServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.PolicyTargetServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.ServiceProfileServiceImpl;
import com.huawei.openstack4j.openstack.gbp.internal.ServicechainServiceImpl;
import com.huawei.openstack4j.openstack.heat.internal.EventsServiceImpl;
import com.huawei.openstack4j.openstack.heat.internal.HeatServiceImpl;
import com.huawei.openstack4j.openstack.heat.internal.ResourcesServiceImpl;
import com.huawei.openstack4j.openstack.heat.internal.SoftwareConfigServiceImpl;
import com.huawei.openstack4j.openstack.heat.internal.StackServiceImpl;
import com.huawei.openstack4j.openstack.heat.internal.TemplateServiceImpl;
import com.huawei.openstack4j.openstack.identity.v2.internal.ServiceManagerServiceImpl;
import com.huawei.openstack4j.openstack.identity.v2.internal.TenantServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.CredentialServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.DomainServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.GroupServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.PolicyServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.ProjectServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.RegionServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.RoleServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.ServiceEndpointServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.TokenServiceImpl;
import com.huawei.openstack4j.openstack.identity.v3.internal.UserServiceImpl;
import com.huawei.openstack4j.openstack.image.internal.ImageServiceImpl;
import com.huawei.openstack4j.openstack.image.v2.internal.TaskServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.AsyncJobServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBCertificateSeviceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBHealthCheckServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBListenerServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBLoadBalancerServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBQuotaServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBServerServiceImpl;
import com.huawei.openstack4j.openstack.loadbalance.internal.ELBServiceImpl;
import com.huawei.openstack4j.openstack.maas.internal.MaaSService;
import com.huawei.openstack4j.openstack.maas.internal.VersionService;
import com.huawei.openstack4j.openstack.magnum.internal.MagnumServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.SchedulerStatsServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.SecurityServiceServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.ShareInstanceServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.ShareNetworkServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.ShareServerServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.ShareServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.ShareSnapshotServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.ShareTypeServiceImpl;
import com.huawei.openstack4j.openstack.manila.internal.SharesServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.ClusterServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.ClusterServiceImpl2;
import com.huawei.openstack4j.openstack.map.reduce.internal.ClusterTemplateServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.DataSourceServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.JobBinaryInternalServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.JobBinaryServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.JobExeServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.JobExecutionServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.JobServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.MapReduceImageServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.MapReducePluginServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.MapReduceServiceImpl;
import com.huawei.openstack4j.openstack.map.reduce.internal.NodeGroupTemplateServiceImpl;
import com.huawei.openstack4j.openstack.message.notification.internal.MessageService;
import com.huawei.openstack4j.openstack.message.notification.internal.MessageTemplateService;
import com.huawei.openstack4j.openstack.message.notification.internal.NotificationService;
import com.huawei.openstack4j.openstack.message.notification.internal.SmsService;
import com.huawei.openstack4j.openstack.message.notification.internal.SubscriptionService;
import com.huawei.openstack4j.openstack.message.notification.internal.TopicService;
import com.huawei.openstack4j.openstack.message.queue.internal.ConsumerGroupService;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueQuotaService;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueService;
import com.huawei.openstack4j.openstack.message.queue.internal.QueueMessageService;
import com.huawei.openstack4j.openstack.message.queue.internal.QueueService;
import com.huawei.openstack4j.openstack.murano.v1.internal.MuranoActionServiceImpl;
import com.huawei.openstack4j.openstack.murano.v1.internal.MuranoApplicationServiceImpl;
import com.huawei.openstack4j.openstack.murano.v1.internal.MuranoDeploymentServiceImpl;
import com.huawei.openstack4j.openstack.murano.v1.internal.MuranoEnvironmentServiceImpl;
import com.huawei.openstack4j.openstack.murano.v1.internal.MuranoService;
import com.huawei.openstack4j.openstack.murano.v1.internal.MuranoSessionServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.FloatingIPServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.NetworkServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.NetworkingServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.PortServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.RouterServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.SecurityGroupRuleServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.SecurityGroupServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.SubnetServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.AgentServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.FirewallAsServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.FirewallPolicyServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.FirewallRuleServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.FirewallServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.HealthMonitorServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.HealthMonitorV2ServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.LbPoolServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.LbPoolV2ServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.LbaasV2ServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.ListenerV2ServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.LoadBalancerServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.LoadBalancerV2ServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.MemberServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.NetQuotaServiceImpl;
import com.huawei.openstack4j.openstack.networking.internal.ext.VipServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingActivityLogServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingConfigServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingGroupInstanceServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingGroupServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingPolicyServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingQuotaServiceImpl;
import com.huawei.openstack4j.openstack.scaling.internal.AutoScalingServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinActionServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinBuildInfoServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinClusterPolicyServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinClusterServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinEventServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinNodeServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinPolicyServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinPolicyTypeServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinProfileServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinProfileTypeServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinReceiverServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinVersionServiceImpl;
import com.huawei.openstack4j.openstack.senlin.internal.SenlinWebHookServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockQuotaSetServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockStorageServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockStorageServiceServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockVolumeBackupServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockVolumeServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockVolumeSnapshotServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.BlockVolumeTransferServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.CinderZoneServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.SchedulerStatsGetPoolServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.VBSVolumeBackupJobServiceImpl;
import com.huawei.openstack4j.openstack.storage.block.internal.VBSVolumeBackupPolicyService;
import com.huawei.openstack4j.openstack.storage.block.internal.VBSVolumeBackupServiceImpl;
import com.huawei.openstack4j.openstack.storage.object.internal.ObjectStorageAccountServiceImpl;
import com.huawei.openstack4j.openstack.storage.object.internal.ObjectStorageContainerServiceImpl;
import com.huawei.openstack4j.openstack.storage.object.internal.ObjectStorageObjectServiceImpl;
import com.huawei.openstack4j.openstack.storage.object.internal.ObjectStorageServiceImpl;
import com.huawei.openstack4j.openstack.tacker.internal.VimServiceImpl;
import com.huawei.openstack4j.openstack.tacker.internal.VnfServiceImpl;
import com.huawei.openstack4j.openstack.tacker.internal.VnfdServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.AlarmAodhServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.AlarmServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.CapabilitiesServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.EventServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.MeterServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.ResourceServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.SampleServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.TelemetryAodhServiceImpl;
import com.huawei.openstack4j.openstack.telemetry.internal.TelemetryServiceImpl;
import com.huawei.openstack4j.openstack.trove.internal.TroveDatabaseConfigService;
import com.huawei.openstack4j.openstack.trove.internal.TroveDatabaseInstanceService;
import com.huawei.openstack4j.openstack.trove.internal.TroveDatabaseParamService;
import com.huawei.openstack4j.openstack.trove.internal.TroveInstanceFlavorService;
import com.huawei.openstack4j.openstack.trove.internal.TroveService;
import com.huawei.openstack4j.openstack.trove.internal.TroveVersionService;
import com.huawei.openstack4j.openstack.workflow.internal.ActionDefinitionServiceImpl;
import com.huawei.openstack4j.openstack.workflow.internal.WorkbookDefinitionServiceImpl;
import com.huawei.openstack4j.openstack.workflow.internal.WorkflowDefinitionServiceImpl;
import com.huawei.openstack4j.openstack.workflow.internal.WorkflowServiceImpl;

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
		bind(com.huawei.openstack4j.api.identity.v2.IdentityService.class,
				com.huawei.openstack4j.openstack.identity.v2.internal.IdentityServiceImpl.class);
		bind(TenantService.class, TenantServiceImpl.class);
		bind(ServiceManagerService.class, ServiceManagerServiceImpl.class);
		bind(com.huawei.openstack4j.api.identity.v2.UserService.class,
				com.huawei.openstack4j.openstack.identity.v2.internal.UserServiceImpl.class);
		bind(com.huawei.openstack4j.api.identity.v2.RoleService.class,
				com.huawei.openstack4j.openstack.identity.v2.internal.RoleServiceImpl.class);
		bind(com.huawei.openstack4j.api.identity.v3.IdentityService.class,
				com.huawei.openstack4j.openstack.identity.v3.internal.IdentityServiceImpl.class);
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
		bind(ServerV1Service.class, ServerV1ServiceImpl.class);
		bind(QuotaSetService.class, QuotaSetServiceImpl.class);
		bind(HostService.class, HostServiceImpl.class);
		bind(NetworkingService.class, NetworkingServiceImpl.class);
		bind(NetworkService.class, NetworkServiceImpl.class);
		bind(ArtifactService.class, ArtifactServiceImpl.class);
		bind(ToscaTemplatesArtifactService.class, ToscaTemplatesArtifactServiceImpl.class);
		bind(SubnetService.class, SubnetServiceImpl.class);
		bind(PortService.class, PortServiceImpl.class);
		bind(RouterService.class, RouterServiceImpl.class);
		bind(ImageService.class, ImageServiceImpl.class);
		bind(BlockStorageService.class, BlockStorageServiceImpl.class);
		bind(BlockVolumeService.class, BlockVolumeServiceImpl.class);
		bind(BlockVolumeSnapshotService.class, BlockVolumeSnapshotServiceImpl.class);
		bind(BlockVolumeBackupService.class, BlockVolumeBackupServiceImpl.class);
		bind(ComputeSecurityGroupService.class, ComputeSecurityGroupServiceImpl.class);
		bind(KeypairService.class, KeypairServiceImpl.class);
		bind(NetFloatingIPService.class, FloatingIPServiceImpl.class);
		bind(ComputeFloatingIPService.class, ComputeFloatingIPServiceImpl.class);
		bind(SecurityGroupService.class, SecurityGroupServiceImpl.class);
		bind(SecurityGroupRuleService.class, SecurityGroupRuleServiceImpl.class);
		bind(TelemetryService.class, TelemetryServiceImpl.class);
		bind(MeterService.class, MeterServiceImpl.class);
		bind(SampleService.class, SampleServiceImpl.class);
		bind(AlarmService.class, AlarmServiceImpl.class);
		bind(EventService.class, EventServiceImpl.class);
		bind(CapabilitiesService.class, CapabilitiesServiceImpl.class);
		bind(ResourceService.class, ResourceServiceImpl.class);
		bind(HypervisorService.class, HypervisorServiceImpl.class);
		bind(ZoneService.class, ZoneServiceImpl.class);
		bind(CinderZoneService.class, CinderZoneServiceImpl.class);
		bind(HeatService.class, HeatServiceImpl.class);
		bind(SenlinService.class, SenlinServiceImpl.class);
		bind(SenlinPolicyService.class, SenlinPolicyServiceImpl.class);
		bind(SenlinVersionService.class, SenlinVersionServiceImpl.class);
		bind(SenlinActionService.class, SenlinActionServiceImpl.class);
		bind(SenlinBuildInfoService.class, SenlinBuildInfoServiceImpl.class);
		bind(SenlinClusterService.class, SenlinClusterServiceImpl.class);
		bind(SenlinClusterPolicyService.class, SenlinClusterPolicyServiceImpl.class);
		bind(SenlinEventService.class, SenlinEventServiceImpl.class);
		bind(SenlinNodeService.class, SenlinNodeServiceImpl.class);
		bind(SenlinProfileService.class, SenlinProfileServiceImpl.class);
		bind(SenlinProfileTypeService.class, SenlinProfileTypeServiceImpl.class);
		bind(SenlinPolicyTypeService.class, SenlinPolicyTypeServiceImpl.class);
		bind(SenlinReceiverService.class, SenlinReceiverServiceImpl.class);
		bind(SenlinWebHookService.class, SenlinWebHookServiceImpl.class);
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
		bind(InstanceActionsService.class, InstanceActionsServiceImpl.class);
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

		bind(MapReducePluginService.class, MapReducePluginServiceImpl.class);
		bind(MapReduceImageService.class, MapReduceImageServiceImpl.class);
		bind(MapReduceService.class, MapReduceServiceImpl.class);
		bind(ClusterService.class, ClusterServiceImpl.class);
		bind(ClusterServiceImpl2.class, ClusterServiceImpl2.class);
		bind(AppCatalogService.class, MuranoService.class);
		bind(MuranoEnvironmentService.class, MuranoEnvironmentServiceImpl.class);
		bind(MuranoSessionService.class, MuranoSessionServiceImpl.class);
		bind(MuranoApplicationService.class, MuranoApplicationServiceImpl.class);
		bind(MuranoDeploymentService.class, MuranoDeploymentServiceImpl.class);
		bind(MuranoActionService.class, MuranoActionServiceImpl.class);
		bind(ClusterTemplateService.class, ClusterTemplateServiceImpl.class);
		bind(NodeGroupTemplateService.class, NodeGroupTemplateServiceImpl.class);
		bind(DataSourceService.class, DataSourceServiceImpl.class);
		bind(JobBinaryInternalService.class, JobBinaryInternalServiceImpl.class);
		bind(JobBinaryService.class, JobBinaryServiceImpl.class);
		bind(JobService.class, JobServiceImpl.class);
		bind(JobExecutionService.class, JobExecutionServiceImpl.class);
		bind(JobExeServiceImpl.class, JobExeServiceImpl.class);
		bind(ShareService.class, ShareServiceImpl.class);
		bind(SecurityServiceService.class, SecurityServiceServiceImpl.class);
		bind(ShareSnapshotService.class, ShareSnapshotServiceImpl.class);
		bind(ShareNetworkService.class, ShareNetworkServiceImpl.class);
		bind(SharesService.class, SharesServiceImpl.class);
		bind(ShareServerService.class, ShareServerServiceImpl.class);
		bind(ShareInstanceService.class, ShareInstanceServiceImpl.class);
		bind(ShareTypeService.class, ShareTypeServiceImpl.class);
		bind(SchedulerStatsService.class, SchedulerStatsServiceImpl.class);
		bind(com.huawei.openstack4j.api.manila.QuotaSetService.class,
				com.huawei.openstack4j.openstack.manila.internal.QuotaSetServiceImpl.class);
		bind(GbpService.class, GbpServiceImpl.class);
		bind(ExternalPolicyService.class, ExternalPolicyServiceImpl.class);
		bind(ExternalSegmentService.class, ExternalSegmentServiceImpl.class);
		bind(com.huawei.openstack4j.api.gbp.GroupService.class, com.huawei.openstack4j.openstack.gbp.internal.GroupServiceImpl.class);
		bind(L2policyService.class, L2policyServiceImpl.class);
		bind(L3policyService.class, L3policyServiceImpl.class);
		bind(NatPoolService.class, NatPoolServiceImpl.class);
		bind(NetworkService.class, NetworkServiceImpl.class);
		bind(PolicyActionService.class, PolicyActionServiceImpl.class);
		bind(PolicyRuleService.class, PolicyRuleServiceImpl.class);
		bind(PolicyRuleSetService.class, PolicyRuleSetServiceImpl.class);
		bind(PolicyTargetService.class, PolicyTargetServiceImpl.class);
		bind(PolicyClassifierService.class, PolicyClassifierServiceImpl.class);
		bind(ServicechainService.class, ServicechainServiceImpl.class);
		bind(ServiceProfileService.class, ServiceProfileServiceImpl.class);
		bind(BlockQuotaSetService.class, BlockQuotaSetServiceImpl.class);
		bind(FirewallAsService.class, FirewallAsServiceImpl.class);
		bind(FirewallService.class, FirewallServiceImpl.class);
		bind(FirewallRuleService.class, FirewallRuleServiceImpl.class);
		bind(FirewallPolicyService.class, FirewallPolicyServiceImpl.class);
		bind(NetworkPolicyService.class, NetworkPolicyServiceImpl.class);
		bind(LbaasV2Service.class, LbaasV2ServiceImpl.class);
		bind(LoadBalancerV2Service.class, LoadBalancerV2ServiceImpl.class);
		bind(ListenerV2Service.class, ListenerV2ServiceImpl.class);
		bind(HealthMonitorV2Service.class, HealthMonitorV2ServiceImpl.class);
		bind(LbPoolV2Service.class, LbPoolV2ServiceImpl.class);

		// trove
		bind(TroveService.class, TroveService.class);
		bind(TroveInstanceFlavorService.class, TroveInstanceFlavorService.class);
		bind(TroveDatabaseInstanceService.class, TroveDatabaseInstanceService.class);
		bind(TroveVersionService.class, TroveVersionService.class);
		bind(TroveDatabaseConfigService.class, TroveDatabaseConfigService.class);
		bind(TroveDatabaseParamService.class, TroveDatabaseParamService.class);
		
		// database
		bind(DatabaseServices.class, DatabaseServices.class);
		bind(DatastoreService.class, DatastoreService.class);
		bind(DatabaseServiceVersionService.class, DatabaseServiceVersionService.class);
		bind(DatabaseParamService.class, DatabaseParamService.class);
		bind(DatabaseInstanceFlavorService.class, DatabaseInstanceFlavorService.class);
		bind(DatabaseInstanceService.class, DatabaseInstanceService.class);
		bind(DatabaseBackupService.class, DatabaseBackupService.class);
		bind(DatabaseLogService.class, DatabaseLogService.class);

		bind(SchedulerStatsGetPoolService.class, SchedulerStatsGetPoolServiceImpl.class);
		bind(BarbicanService.class, BarbicanServiceImpl.class);
		bind(ContainerService.class, ContainerServiceImpl.class);
		bind(TackerService.class, TackerServiceImpl.class);
		bind(VnfdService.class, VnfdServiceImpl.class);
		bind(VnfService.class, VnfServiceImpl.class);
		bind(VimService.class, VimServiceImpl.class);
		bind(AgentService.class, AgentServiceImpl.class);
		bind(com.huawei.openstack4j.api.image.v2.ImageService.class,
				com.huawei.openstack4j.openstack.image.v2.internal.ImageServiceImpl.class);
		bind(TaskService.class, TaskServiceImpl.class);
		bind(TaskService.class, TaskServiceImpl.class);
		bind(ServerTagService.class, ServerTagServiceImpl.class);
		bind(TelemetryAodhService.class, TelemetryAodhServiceImpl.class);
		bind(AlarmAodhService.class, AlarmAodhServiceImpl.class);
		bind(ServicesService.class, ServicesServiceImpl.class);
		bind(BlockStorageServiceService.class, BlockStorageServiceServiceImpl.class);
		bind(MagnumService.class, MagnumServiceImpl.class);
		bind(WorkflowService.class, WorkflowServiceImpl.class);
		bind(WorkflowDefinitionService.class, WorkflowDefinitionServiceImpl.class);
		bind(DNSService.class, DNSServiceImpl.class);
		bind(com.huawei.openstack4j.api.dns.v2.ZoneService.class,
				com.huawei.openstack4j.openstack.dns.v2.internal.ZoneServiceImpl.class);
		bind(RecordsetService.class, RecordsetServiceImpl.class);
		bind(WorkflowService.class, WorkflowServiceImpl.class);
		bind(WorkflowDefinitionService.class, WorkflowDefinitionServiceImpl.class);
		bind(WorkbookDefinitionService.class, WorkbookDefinitionServiceImpl.class);
		bind(ActionDefinitionService.class, ActionDefinitionServiceImpl.class);

		// huawei openstack services binding
		// Volume Backup
		bind(AsyncVolumeBackupService.class, VBSVolumeBackupServiceImpl.class);
		bind(AsyncVolumeBackupJobService.class, VBSVolumeBackupJobServiceImpl.class);
		bind(BlockVolumeBackupPolicyService.class, VBSVolumeBackupPolicyService.class);

		// DNS
		bind(PTRService.class, PTRServiceImpl.class);

		// Cloud Eye
		bind(CloudEyeService.class, CloudEyeServiceImpl.class);
		bind(MetricService.class, CloudEyeMetricServiceImpl.class);
		bind(com.huawei.openstack4j.api.cloudeye.AlarmService.class, CloudEyeAlarmServiceImpl.class);
		bind(MetricDataService.class, CloudEyeMetricDataServiceImpl.class);
		bind(QuotaService.class, CloudEyeQuotaServiceImpl.class);

		// auto-scaling
		bind(AutoScalingService.class, AutoScalingServiceImpl.class);
		bind(AutoScalingGroupService.class, AutoScalingGroupServiceImpl.class);
		bind(AutoScalingConfigService.class, AutoScalingConfigServiceImpl.class);
		bind(AutoScalingGroupInstanceService.class, AutoScalingGroupInstanceServiceImpl.class);
		bind(AutoScalingPolicyService.class, AutoScalingPolicyServiceImpl.class);
		bind(AutoScalingActivityLogService.class, AutoScalingActivityLogServiceImpl.class);
		bind(AutoScalingQuotaService.class, AutoScalingQuotaServiceImpl.class);

		// load balance
		bind(ELBService.class, ELBServiceImpl.class);
		bind(ELBLoadBalancerService.class, ELBLoadBalancerServiceImpl.class);
		bind(ELBListenerService.class, ELBListenerServiceImpl.class);
		bind(ELBHealthCheckService.class, ELBHealthCheckServiceImpl.class);
		bind(ELBServerService.class, ELBServerServiceImpl.class);
		bind(ELBQuotaService.class, ELBQuotaServiceImpl.class);
		bind(ELBCertificateService.class, ELBCertificateSeviceImpl.class);
		bind(AsyncJobService.class, AsyncJobServiceImpl.class);
	

		// cloud trace
		bind(TraceService.class, TraceService.class);
		bind(TrackerService.class, TrackerService.class);
		bind(CloudTraceV1Service.class, CloudTraceV1Service.class);

		bind(com.huawei.openstack4j.openstack.cloud.trace.v2.internal.TraceService.class,
				com.huawei.openstack4j.openstack.cloud.trace.v2.internal.TraceService.class);
		bind(CloudTraceV2Service.class, CloudTraceV2Service.class);

		// anti-ddos
		bind(AntiDDoSServices.class, AntiDDoSServices.class);
		bind(AntiDDoSService.class, AntiDDoSService.class);
		bind(AntiDDoSWarnService.class, AntiDDoSWarnService.class);

		// simple message notification
		bind(NotificationService.class, NotificationService.class);
		bind(TopicService.class, TopicService.class);
		bind(SubscriptionService.class, SubscriptionService.class);
		bind(MessageTemplateService.class, MessageTemplateService.class);
		bind(MessageService.class, MessageService.class);
		bind(SmsService.class, SmsService.class);

		// distributed message
		bind(MessageQueueService.class, MessageQueueService.class);
		bind(QueueMessageService.class, QueueMessageService.class);
		bind(QueueService.class, QueueService.class);
		bind(ConsumerGroupService.class, ConsumerGroupService.class);
		bind(MessageQueueQuotaService.class, MessageQueueQuotaService.class);

		// maas
		bind(MaaSService.class, MaaSService.class);
		bind(VersionService.class, VersionService.class);
		bind(com.huawei.openstack4j.openstack.maas.internal.TaskService.class,
				com.huawei.openstack4j.openstack.maas.internal.TaskService.class);

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
