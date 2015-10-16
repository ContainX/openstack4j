package org.openstack4j.api;

import org.openstack4j.model.common.builder.LinkBuilder;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import org.openstack4j.model.compute.builder.FlavorBuilder;
import org.openstack4j.model.compute.builder.FloatingIPBuilder;
import org.openstack4j.model.compute.builder.QuotaSetUpdateBuilder;
import org.openstack4j.model.compute.builder.SecurityGroupRuleBuilder;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;
import org.openstack4j.model.heat.SoftwareConfig;
import org.openstack4j.model.heat.StackCreate;
import org.openstack4j.model.heat.StackUpdate;
import org.openstack4j.model.heat.Template;
import org.openstack4j.model.heat.builder.SoftwareConfigBuilder;
import org.openstack4j.model.heat.builder.StackCreateBuilder;
import org.openstack4j.model.heat.builder.StackUpdateBuilder;
import org.openstack4j.model.heat.builder.TemplateBuilder;
import org.openstack4j.model.identity.builder.DomainBuilder;
import org.openstack4j.model.identity.builder.EndpointBuilder;
import org.openstack4j.model.identity.builder.GroupBuilder;
import org.openstack4j.model.identity.builder.PolicyBuilder;
import org.openstack4j.model.identity.builder.ProjectBuilder;
import org.openstack4j.model.identity.builder.RoleBuilder;
import org.openstack4j.model.identity.builder.ServiceBuilder;
import org.openstack4j.model.identity.builder.UserBuilder;
import org.openstack4j.model.image.builder.ImageBuilder;
import org.openstack4j.model.network.builder.ExtraDhcpOptBuilder;
import org.openstack4j.model.network.builder.NetFloatingIPBuilder;
import org.openstack4j.model.network.builder.NetQuotaBuilder;
import org.openstack4j.model.network.builder.NetSecurityGroupBuilder;
import org.openstack4j.model.network.builder.NetSecurityGroupRuleBuilder;
import org.openstack4j.model.network.builder.NetworkBuilder;
import org.openstack4j.model.network.builder.NetworkUpdateBuilder;
import org.openstack4j.model.network.builder.PortBuilder;
import org.openstack4j.model.network.builder.RouterBuilder;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.openstack4j.model.network.ext.builder.FirewallBuilder;
import org.openstack4j.model.network.ext.builder.FirewallPolicyBuilder;
import org.openstack4j.model.network.ext.builder.FirewallPolicyUpdateBuilder;
import org.openstack4j.model.network.ext.builder.FirewallRuleBuilder;
import org.openstack4j.model.network.ext.builder.FirewallRuleUpdateBuilder;
import org.openstack4j.model.network.ext.builder.FirewallUpdateBuilder;
import org.openstack4j.model.network.ext.builder.HealthMonitorAssociateBuilder;
import org.openstack4j.model.network.ext.builder.HealthMonitorBuilder;
import org.openstack4j.model.network.ext.builder.HealthMonitorUpdateBuilder;
import org.openstack4j.model.network.ext.builder.LbPoolBuilder;
import org.openstack4j.model.network.ext.builder.LbPoolUpdateBuilder;
import org.openstack4j.model.network.ext.builder.MemberBuilder;
import org.openstack4j.model.network.ext.builder.MemberUpdateBuilder;
import org.openstack4j.model.network.ext.builder.SessionPersistenceBuilder;
import org.openstack4j.model.network.ext.builder.VipBuilder;
import org.openstack4j.model.network.ext.builder.VipUpdateBuilder;
import org.openstack4j.model.sahara.builder.ClusterBuilder;
import org.openstack4j.model.sahara.builder.ClusterTemplateBuilder;
import org.openstack4j.model.sahara.builder.DataSourceBuilder;
import org.openstack4j.model.sahara.builder.JobBinaryBuilder;
import org.openstack4j.model.sahara.builder.JobBuilder;
import org.openstack4j.model.sahara.builder.JobConfigBuilder;
import org.openstack4j.model.sahara.builder.JobExecutionBuilder;
import org.openstack4j.model.sahara.builder.NodeGroupBuilder;
import org.openstack4j.model.sahara.builder.NodeGroupTemplateBuilder;
import org.openstack4j.model.sahara.builder.ServiceConfigBuilder;
import org.openstack4j.model.storage.block.builder.BlockQuotaSetBuilder;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import org.openstack4j.model.telemetry.builder.AlarmBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.compute.domain.NovaBlockDeviceMappingCreate;
import org.openstack4j.openstack.compute.domain.NovaFlavor;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP;
import org.openstack4j.openstack.compute.domain.NovaQuotaSetUpdate;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroupRule;
import org.openstack4j.openstack.compute.domain.NovaServerCreate;
import org.openstack4j.openstack.heat.domain.HeatSoftwareConfig;
import org.openstack4j.openstack.heat.domain.HeatStackCreate;
import org.openstack4j.openstack.heat.domain.HeatStackUpdate;
import org.openstack4j.openstack.heat.domain.HeatTemplate;
import org.openstack4j.openstack.identity.domain.KeystoneDomain;
import org.openstack4j.openstack.identity.domain.KeystoneEndpoint;
import org.openstack4j.openstack.identity.domain.KeystoneGroup;
import org.openstack4j.openstack.identity.domain.KeystonePolicy;
import org.openstack4j.openstack.identity.domain.KeystoneProject;
import org.openstack4j.openstack.identity.domain.KeystoneRole;
import org.openstack4j.openstack.identity.domain.KeystoneService;
import org.openstack4j.openstack.identity.domain.KeystoneUser;
import org.openstack4j.openstack.image.domain.GlanceImage;
import org.openstack4j.openstack.networking.domain.NeutronExtraDhcpOptCreate;
import org.openstack4j.openstack.networking.domain.NeutronFloatingIP;
import org.openstack4j.openstack.networking.domain.NeutronNetQuota;
import org.openstack4j.openstack.networking.domain.NeutronNetwork;
import org.openstack4j.openstack.networking.domain.NeutronNetworkUpdate;
import org.openstack4j.openstack.networking.domain.NeutronPort;
import org.openstack4j.openstack.networking.domain.NeutronRouter;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroup;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroupRule;
import org.openstack4j.openstack.networking.domain.NeutronSubnet;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewall;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicy;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicyUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallRuleUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitor;
import org.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorAssociate;
import org.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronLbPool;
import org.openstack4j.openstack.networking.domain.ext.NeutronLbPoolUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronMember;
import org.openstack4j.openstack.networking.domain.ext.NeutronMemberUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronSessionPersistence;
import org.openstack4j.openstack.networking.domain.ext.NeutronVip;
import org.openstack4j.openstack.networking.domain.ext.NeutronVipUpdate;
import org.openstack4j.openstack.sahara.domain.SaharaCluster;
import org.openstack4j.openstack.sahara.domain.SaharaClusterTemplate;
import org.openstack4j.openstack.sahara.domain.SaharaDataSource;
import org.openstack4j.openstack.sahara.domain.SaharaJob;
import org.openstack4j.openstack.sahara.domain.SaharaJobBinary;
import org.openstack4j.openstack.sahara.domain.SaharaJobConfig;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecution;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroup;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplate;
import org.openstack4j.openstack.sahara.domain.SaharaServiceConfig;
import org.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import org.openstack4j.openstack.storage.block.domain.CinderVolume;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeSnapshot;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm;

/**
 * A utility class to quickly access available Builders within the OpenStack API
 *
 * @author Jeremy Unruh
 */
public class Builders {

    /**
     * The builder to create a Link
     *
     * @return the link builder
     */
    public static LinkBuilder link() {
        return GenericLink.builder();
    }

    /**
     * The builder to create a Server
     *
     * @return the server create builder
     */
    public static ServerCreateBuilder server() {
        return NovaServerCreate.builder();
    }

    public static BlockDeviceMappingBuilder blockDeviceMapping() {
        return NovaBlockDeviceMappingCreate.builder();
    }

    public static ExtraDhcpOptBuilder extraDhcpOpt() {
        return NeutronExtraDhcpOptCreate.builder();
    }

    /**
     * The builder to create a Flavor.
     *
     * @return the flavor builder
     */
    public static FlavorBuilder flavor() {
        return NovaFlavor.builder();
    }

    /**
     * The builder to create a Domain.
     *
     * @return the domain builder
     */
    public static DomainBuilder domain() {
        return KeystoneDomain.builder();
    }

    /**
     * The builder to create a Endpoint.
     *
     * @return the endpoint builder
     */
    public static EndpointBuilder endpoint() {
        return KeystoneEndpoint.builder();
    }

    /**
     * The builder to create a Group.
     *
     * @return the group builder
     */
    public static GroupBuilder group() {
        return KeystoneGroup.builder();
    }

    /**
     * The builder to create a Policy.
     *
     * @return the policy builder
     */
    public static PolicyBuilder policy() {
        return KeystonePolicy.builder();
    }

    /**
     * The builder to create a Project.
     *
     * @return the project builder
     */
    public static ProjectBuilder project() {
        return KeystoneProject.builder();
    }

    /**
     * The builder to create a Role.
     *
     * @return the role builder
     */
    public static RoleBuilder role() {
        return KeystoneRole.builder();
    }

    /**
     * The builder to create a Service.
     *
     * @return the service builder
     */
    public static ServiceBuilder service() {
        return KeystoneService.builder();
    }

    /**
     * The builder to create a User.
     *
     * @return the user builder
     */
    public static UserBuilder user() {
        return KeystoneUser.builder();
    }

    /**
     * The builder to create a Network
     *
     * @return the network builder
     */
    public static NetworkBuilder network() {
        return NeutronNetwork.builder();
    }

    /**
     * The builder to create a Subnet
     *
     * @return the subnet builder
     */
    public static SubnetBuilder subnet() {
        return NeutronSubnet.builder();
    }

    /**
     * The builder to create a Port
     * @return the port builder
     */
    public static PortBuilder port() {
        return NeutronPort.builder();
    }

    /**
     * The builder to create a Router
     * @return the router builder
     */
    public static RouterBuilder router() {
        return NeutronRouter.builder();
    }

    /**
     * The builder to create a Glance Image
     * @return the image builder
     */
    public static ImageBuilder image() {
        return GlanceImage.builder();
    }

    /**
     * The builder to create a Block Volume
     * @return the volume builder
     */
    public static VolumeBuilder volume() {
        return CinderVolume.builder();
    }

    /**
     * The builder to create a Block Volume Snapshot
     * @return the snapshot builder
     */
    public static VolumeSnapshotBuilder volumeSnapshot() {
        return CinderVolumeSnapshot.builder();
    }

    /**
     * The builder to create a Compute/Nova Floating IP
     * @return the floating ip builder
     */
    public static FloatingIPBuilder floatingIP() {
        return NovaFloatingIP.builder();
    }

    /**
     * A Builder which creates a Security Group Rule
     * @return the security group rule builder
     */
    public static SecurityGroupRuleBuilder secGroupRule() {
        return SecurityGroupRule.builder();
    }

    /**
     * The builder to create a Neutron Security Group
     *
     * @return the security group builder
     */
    public static NetSecurityGroupBuilder securityGroup() {
        return NeutronSecurityGroup.builder();
    }

    /**
     * The builder to create a Neutron Security Group Rule
     *
     * @return the security group builder
     */
    public static NetSecurityGroupRuleBuilder securityGroupRule() {
        return NeutronSecurityGroupRule.builder();
    }

    /**
     * The builder to create a Neutron Floating IP Address
     *
     * @return the floating ip builder
     */
    public static NetFloatingIPBuilder netFloatingIP() {
        return NeutronFloatingIP.builder();
    }

    /**
     * The builder to create a {@link Template}
     * @return the TemplateBuilder
     */
    public static TemplateBuilder template(){
        return HeatTemplate.build();
    }

    /**
     * The builder to create a {@link StackCreate}
     * @return the StackCreate builder
     */
    public static StackCreateBuilder stack(){
        return HeatStackCreate.build();
    }

    /**
     * The builder to create a {@link SoftwareConfig}
     *
     * @return the software config builder
     */
    public static SoftwareConfigBuilder softwareConfig() {
        return new HeatSoftwareConfig.Builder();
    }

    /**
     * The builder to create a {@link StackUpdate}
     * @return the StackUpdate builder
     */
    public static StackUpdateBuilder stackUpdate(){
        return HeatStackUpdate.builder();
    }

    /**
     * The builder to create NetQuota entities
     * @return the NetQuota builder
     */
    public static NetQuotaBuilder netQuota() {
        return NeutronNetQuota.builder();
    }

    /**
     * The builder to update a network
     * @return the NetworkUpdateBuilder
     */
    public static NetworkUpdateBuilder networkUpdate() {
        return NeutronNetworkUpdate.builder();
    }

    /**
     * The builder to create a lb member
     * @return the Member Builder
     */
    public static MemberBuilder member() {
        return NeutronMember.builder();
    }

    /**
     * The builder to update a lb member
     * @return the MemberUpdate Builder
     */
    public static MemberUpdateBuilder memberUpdate() {
        return NeutronMemberUpdate.builder();
    }
    /**
     * The builder to create and update a sessionPersistence
     * @return SessionPersistenceBuilder
     */
    public static SessionPersistenceBuilder sessionPersistence(){
    	return NeutronSessionPersistence.builder();
    }
    /**
     * The builder to create a vip.
     * @return VipBuilder the vip builder
     */
    public static VipBuilder vip(){
    	return NeutronVip.builder();
    }
    /**
     * The builder to update a vip.
     * @return VipUpdateBuilder
     */
    public static VipUpdateBuilder vipUpdate(){
    	return NeutronVipUpdate.builder();
    }

    /**
     * The builder to create a healthMonitor
     * @return HealthMonitorBuilder
     */
    public static HealthMonitorBuilder healthMonitor(){
    	return NeutronHealthMonitor.builder();
    }
    /**
     * The builder to update a healthMonitor
     * @return HealthMonitorUpdateBuilder
     */
    public static HealthMonitorUpdateBuilder healthMonitorUpdate(){
    	return NeutronHealthMonitorUpdate.builder();
    }

    /**
     * The builder to create a firewall
     * @return FirewallBuilder
     */
    public static FirewallBuilder firewall() {
    	return NeutronFirewall.builder();
    }
    /**
     * The builder to update a healthMonitor
     * @return FirewallUpdateBuilder
     */
    public static FirewallUpdateBuilder firewallUpdate() {
    	return NeutronFirewallUpdate.builder();
    }

    /**
     * The builder to create a firewallRule
     * @return FirewallRuleBuilder
     */
    public static FirewallRuleBuilder firewallRule() {
    	return NeutronFirewallRule.builder();
    }
    /**
     * The builder to update a firewallRule
     * @return FirewallUpdateBuilder
     */
    public static FirewallRuleUpdateBuilder firewallRuleUpdate() {
    	return NeutronFirewallRuleUpdate.builder();
    }

    /**
     * The builder to create a firewallPolicy
     * @return FirewallPolicyBuilder
     */
    public static FirewallPolicyBuilder firewallPolicy() {
    	return NeutronFirewallPolicy.builder();
    }
    /**
     * The builder to update a firewallPolicy
     * @return FirewallPolicyUpdateBuilder
     */
    public static FirewallPolicyUpdateBuilder firewallPolicyUpdate() {
    	return NeutronFirewallPolicyUpdate.builder();
    }

    /**
     * The builder to create a lbPool
     * @return LbPoolBuilder
     */
    public static LbPoolBuilder lbPool(){
    	return NeutronLbPool.builder();
    }
    /**
     * The builder to update a lbPool
     * @return LbPoolUpdateBuilder
     */
    public static LbPoolUpdateBuilder lbPoolUpdate(){
    	return NeutronLbPoolUpdate.builder();
    }

    /**
     * The builder to create a lbPool
     * @return HealthMonitorAssociateBuilder
     */
    public static HealthMonitorAssociateBuilder lbPoolAssociateHealthMonitor(){
    	return NeutronHealthMonitorAssociate.builder();
    }

    /**
     * The builder to create a sahara cluster
     * @return the cluster builder
     */
    public static ClusterBuilder cluster() {
        return SaharaCluster.builder();
    }

    /**
     * The builder to create a sahara cluster template
     * @return the cluster template builder
     */
    public static ClusterTemplateBuilder clusterTemplate() {
        return SaharaClusterTemplate.builder();
    }

    /**
     * The builder to create a sahara node group
     * @return the node group builder
     */
    public static NodeGroupBuilder nodeGroup() {
        return SaharaNodeGroup.builder();
    }

    /**
     * The builder to create a sahara node group template
     * @return the node group template builder
     */
    public static NodeGroupTemplateBuilder nodeGroupTemplate() {
        return SaharaNodeGroupTemplate.builder();
    }

    /**
     * The builder to create a sahara service configuration
     * @return the service configuration builder
     */
    public static ServiceConfigBuilder serviceConfig() {
        return SaharaServiceConfig.builder();
    }

    /**
     * This builder which creates a QuotaSet for updates
     *
     * @return the QuotaSet update builder
     */
    public static QuotaSetUpdateBuilder quotaSet() {
        return NovaQuotaSetUpdate.builder();
    }

    /**
     * The builder to create an Alarm
     * @return the image builder
     */
    public static AlarmBuilder alarm() {
        return CeilometerAlarm.builder();
    }

    /**
     * The builder which creates a BlockQuotaSet
     *
     * @return the block quota-set builder
     */
    public static BlockQuotaSetBuilder blockQuotaSet() {
        return CinderBlockQuotaSet.builder();
    }

    /**
     * The builder which creates a sahara Data Source
     *
     * @return the data source builder
     */
    public static DataSourceBuilder dataSource() {
        return SaharaDataSource.builder();
    }

    /**
     * The builder which creates a sahara Job Binary
     *
     * @return the job binary builder
     */
    public static JobBinaryBuilder jobBinary() {
        return SaharaJobBinary.builder();
    }


    /**
     * The builder which creates a sahara Job
     *
     * @return the job builder
     */
    public static JobBuilder job() {
        return SaharaJob.builder();
    }

    /**
     * The builder which creates a job configuration for sahara job execution
     *
     * @return the job config builder
     */
    public static JobConfigBuilder jobConfig() {
        return SaharaJobConfig.builder();
    }

    /**
     * The builder which creates a sahara job execution
     *
     * @return the job execution builder
     */
    public static JobExecutionBuilder jobExecution() {
        return SaharaJobExecution.builder();
    }
}
