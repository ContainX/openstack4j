package org.openstack4j.openstack.networking.domain;

import java.util.ArrayList;
import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.State;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.NetworkBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * An OpenStack (Neutron) network
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("network")
public class NeutronNetwork implements Network {

	private static final long serialVersionUID = 1L;
	
	private State status;
	@JsonProperty
	private List<String> subnets;
	private List<NeutronSubnet> neutronSubnets;
	private String name;
	@JsonProperty("provider:physical_network")
	private String providerPhyNet;
	@JsonProperty("admin_state_up")
	private boolean adminStateUp;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("provider:network_type")
	private NetworkType networkType;
	@JsonProperty("router:external")
	private boolean routerExternal;
	private String id;
	private boolean shared;
	@JsonProperty("provider:segmentation_id")
	private String providerSegID;
	
	public static NetworkBuilder builder() {
		return new NetworkConcreteBuilder();
	}

	/**
	 * Wraps this Network into a Builder
	 * @return the network builder
	 */
	public NetworkBuilder toBuilder() {
		return new NetworkConcreteBuilder(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getStatus() {
		return status;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getSubnets() {
		return subnets;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Subnet> getNeutronSubnets() {
		if ( neutronSubnets == null && (subnets != null && subnets.size() > 0)) 
		{
			neutronSubnets = new ArrayList<NeutronSubnet>();
			for ( String subnetId : subnets) {
				NeutronSubnet sub = (NeutronSubnet)Apis.getNetworkingServices().subnet().get(subnetId);
				neutronSubnets.add(sub);
			}
		}
		return neutronSubnets;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProviderPhyNet() {
		return providerPhyNet;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAdminStateUp() {
		return adminStateUp;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public NetworkType getNetworkType() {
		return networkType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRouterExternal() {
		return routerExternal;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isShared() {
		return shared;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProviderSegID() {
		return providerSegID;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				    .add("name", name).add("status", status).add("subnets", subnets).add("provider:physical_network", providerPhyNet)
				    .add("adminStateUp", adminStateUp).add("tenantId", tenantId).add("provider:network_type", networkType).add("router:external", routerExternal)
				    .add("id", id).add("shared", shared).add("provider:segmentation_id", providerSegID)
				    .toString();
	}
	
	public static class Networks extends ListResult<NeutronNetwork> {
		
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("networks")
		private List<NeutronNetwork> networks;
		
		public List<NeutronNetwork> value() {
			return networks;
		}
	}
	
	public static class NetworkConcreteBuilder implements NetworkBuilder {

		private NeutronNetwork m;
		
		public NetworkConcreteBuilder() {
			this(new NeutronNetwork());
		}
		
		public NetworkConcreteBuilder(NeutronNetwork m) {
			this.m = m;
		}
		
		@Override
		public NetworkBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public NetworkBuilder adminStateUp(boolean adminStateUp) {
			m.adminStateUp = adminStateUp;
			return this;
		}

		@Override
		public NetworkBuilder networkType(NetworkType networkType) {
			m.networkType = networkType;
			return this;
		}

		@Override
		public NetworkBuilder physicalNetwork(String providerPhysicalNetwork) {
			m.providerPhyNet = providerPhysicalNetwork;
			return this;
		}

		@Override
		public NetworkBuilder segmentId(String providerSegmentationId) {
			m.providerSegID = providerSegmentationId;
			return this;
		}

		@Override
		public NetworkBuilder tenantId(String tenantId) {
			m.tenantId = tenantId;
			return this;
		}
		
		@Override
		public Network build() {
			return m;
		}

		@Override
		public NetworkBuilder from(Network in) {
			m = (NeutronNetwork) in;
			return this;
		}
		
	}
}
