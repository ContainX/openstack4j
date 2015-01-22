package org.openstack4j.api;

import org.openstack4j.model.common.builder.LinkBuilder;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import org.openstack4j.model.compute.builder.FlavorBuilder;
import org.openstack4j.model.compute.builder.FloatingIPBuilder;
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
import org.openstack4j.model.identity.builder.EndpointBuilder;
import org.openstack4j.model.identity.builder.RoleBuilder;
import org.openstack4j.model.identity.builder.ServiceBuilder;
import org.openstack4j.model.identity.builder.ServiceEndpointBuilder;
import org.openstack4j.model.identity.builder.TenantBuilder;
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
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.compute.domain.NovaBlockDeviceMappingCreate;
import org.openstack4j.openstack.compute.domain.NovaFlavor;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroupRule;
import org.openstack4j.openstack.compute.domain.NovaServerCreate;
import org.openstack4j.openstack.heat.domain.HeatSoftwareConfig;
import org.openstack4j.openstack.heat.domain.HeatStackCreate;
import org.openstack4j.openstack.heat.domain.HeatStackUpdate;
import org.openstack4j.openstack.heat.domain.HeatTemplate;
import org.openstack4j.openstack.identity.domain.KeystoneEndpoint;
import org.openstack4j.openstack.identity.domain.KeystoneRole;
import org.openstack4j.openstack.identity.domain.KeystoneService;
import org.openstack4j.openstack.identity.domain.KeystoneServiceEndpoint;
import org.openstack4j.openstack.identity.domain.KeystoneTenant;
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
import org.openstack4j.openstack.storage.block.domain.CinderVolume;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeSnapshot;

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
    
}
