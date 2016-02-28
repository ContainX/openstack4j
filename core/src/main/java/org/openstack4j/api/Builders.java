package org.openstack4j.api;

import org.openstack4j.model.common.builder.LinkBuilder;
import org.openstack4j.model.compute.builder.*;
import org.openstack4j.model.heat.SoftwareConfig;
import org.openstack4j.model.heat.StackCreate;
import org.openstack4j.model.heat.StackUpdate;
import org.openstack4j.model.heat.Template;
import org.openstack4j.model.heat.builder.SoftwareConfigBuilder;
import org.openstack4j.model.heat.builder.StackCreateBuilder;
import org.openstack4j.model.heat.builder.StackUpdateBuilder;
import org.openstack4j.model.heat.builder.TemplateBuilder;
import org.openstack4j.model.identity.builder.*;
import org.openstack4j.model.image.builder.ImageBuilder;
import org.openstack4j.model.manila.builder.*;
import org.openstack4j.model.network.builder.*;
import org.openstack4j.model.network.ext.builder.*;
import org.openstack4j.model.sahara.builder.*;
import org.openstack4j.model.storage.block.builder.BlockQuotaSetBuilder;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import org.openstack4j.model.telemetry.builder.AlarmBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.compute.domain.*;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroupRule;
import org.openstack4j.openstack.heat.domain.HeatSoftwareConfig;
import org.openstack4j.openstack.heat.domain.HeatStackCreate;
import org.openstack4j.openstack.heat.domain.HeatStackUpdate;
import org.openstack4j.openstack.heat.domain.HeatTemplate;
import org.openstack4j.openstack.identity.domain.*;
import org.openstack4j.openstack.image.domain.GlanceImage;
import org.openstack4j.openstack.manila.domain.*;
import org.openstack4j.openstack.networking.domain.*;
import org.openstack4j.openstack.networking.domain.ext.*;
import org.openstack4j.openstack.sahara.domain.*;
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
     * The builder to create a Endpoint.
     *
     * @return the endpoint builder
     */
    public static EndpointBuilder endpoint() {
        return KeystoneEndpoint.builder();
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
     * The builder to create a Service Endpoint.
     *
     * @return the service endpoint builder
     */
    public static ServiceEndpointBuilder serviceEndpoint() {
        return KeystoneServiceEndpoint.builder();
    }

    /**
     * The builder to create a Tenant.
     *
     * @return the tenant builder
     */
    public static TenantBuilder tenant() {
        return KeystoneTenant.builder();
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

    /**
     * The builder which creates manila security services
     *
     * @return the security service builder
     */
    public static SecurityServiceCreateBuilder securityService() {
        return ManilaSecurityServiceCreate.builder();
    }

    /**
     * The builder which creates manila share networks.
     *
     * @return the share network builder
     */
    public static ShareNetworkCreateBuilder shareNetwork() {
        return ManilaShareNetworkCreate.builder();
    }

    /**
     * The builder which creates manila shares.
     *
     * @return the share builder
     */
    public static ShareCreateBuilder share() {
        return ManilaShareCreate.builder();
    }

    /**
     * The builder which creates share types.
     *
     * @return the shae type builder
     */
    public static ShareTypeCreateBuilder shareType() {
        return ManilaShareTypeCreate.builder();
    }

    /**
     * The builder which creates manila share snapshots.
     *
     * @return the share builder
     */
    public static ShareSnapshotCreateBuilder shareSnapshot() {
        return ManilaShareSnapshotCreate.builder();
    }

    /**
     * The builder which creates manila share manages
     *
     * @return the share manage builder
     */
    public static ShareManageBuilder shareManage() {
        return ManilaShareManage.builder();
    }
}
