package org.openstack4j.api;

import org.openstack4j.model.common.builder.LinkBuilder;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import org.openstack4j.model.compute.builder.FlavorBuilder;
import org.openstack4j.model.compute.builder.FloatingIPBuilder;
import org.openstack4j.model.compute.builder.SecurityGroupRuleBuilder;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;
import org.openstack4j.model.identity.builder.EndpointBuilder;
import org.openstack4j.model.identity.builder.RoleBuilder;
import org.openstack4j.model.identity.builder.ServiceBuilder;
import org.openstack4j.model.identity.builder.ServiceEndpointBuilder;
import org.openstack4j.model.identity.builder.TenantBuilder;
import org.openstack4j.model.identity.builder.UserBuilder;
import org.openstack4j.model.image.builder.ImageBuilder;
import org.openstack4j.model.network.builder.NetFloatingIPBuilder;
import org.openstack4j.model.network.builder.NetSecurityGroupBuilder;
import org.openstack4j.model.network.builder.NetSecurityGroupRuleBuilder;
import org.openstack4j.model.network.builder.NetworkBuilder;
import org.openstack4j.model.network.builder.PortBuilder;
import org.openstack4j.model.network.builder.RouterBuilder;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.compute.domain.NovaBlockDeviceMappingCreate;
import org.openstack4j.openstack.compute.domain.NovaFlavor;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroupRule;
import org.openstack4j.openstack.compute.domain.NovaServerCreate;
import org.openstack4j.openstack.identity.domain.KeystoneEndpoint;
import org.openstack4j.openstack.identity.domain.KeystoneRole;
import org.openstack4j.openstack.identity.domain.KeystoneService;
import org.openstack4j.openstack.identity.domain.KeystoneServiceEndpoint;
import org.openstack4j.openstack.identity.domain.KeystoneTenant;
import org.openstack4j.openstack.identity.domain.KeystoneUser;
import org.openstack4j.openstack.image.domain.GlanceImage;
import org.openstack4j.openstack.networking.domain.NeutronFloatingIP;
import org.openstack4j.openstack.networking.domain.NeutronNetwork;
import org.openstack4j.openstack.networking.domain.NeutronPort;
import org.openstack4j.openstack.networking.domain.NeutronRouter;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroup;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroupRule;
import org.openstack4j.openstack.networking.domain.NeutronSubnet;
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
	
}
