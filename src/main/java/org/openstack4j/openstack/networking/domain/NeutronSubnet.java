package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Pool;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * A Subnet is a network with Pools and network based settings
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("subnet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronSubnet implements Subnet {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	@JsonProperty("enable_dhcp")
	private boolean enableDHCP;
	@JsonProperty("network_id")
	private String networkId;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("dns_nameservers")
	private List<String> dnsNames;
	@JsonProperty("allocation_pools")
	private List<NeutronPool> pools;
	@JsonProperty("host_routes")
	private List<String> hostRoutes;
	@JsonProperty("ip_version")
	private IPVersionType ipVersion;
	@JsonProperty("gateway_ip")
	private String gateway;
	private String cidr;

	public static SubnetBuilder builder() {
		return new SubnetConcreteBuilder();
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
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDHCPEnabled() {
		return enableDHCP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNetworkId() {
		return networkId;
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
	public List<String> getDnsNames() {
		return dnsNames;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Pool> getAllocationPools() {
		return pools;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getHostRoutes() {
		return hostRoutes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPVersionType getIpVersion() {
		return ipVersion;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getGateway() {
		return gateway;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCidr() {
		return cidr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("id", id).add("name", name).add("enableDHCP", enableDHCP).add("network-id", networkId)
				.add("tenant_id", tenantId).add("dns_nameservers", dnsNames).add("allocation_pools", pools).add("host_routes", pools)
				.add("host_routes", hostRoutes).add("ip_version", ipVersion).add("gateway_ip", gateway).add("cidr", cidr)
				.toString();
	}


	public static class Subnets extends ListResult<NeutronSubnet> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("subnets")
		private List<NeutronSubnet> subnets;

		@Override
		protected List<NeutronSubnet> value() {
			return subnets;
		}
	}

	public static class SubnetConcreteBuilder implements SubnetBuilder {

		private NeutronSubnet m = new NeutronSubnet();
		
		@Override
		public SubnetBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public SubnetBuilder networkId(String networkId) {
			m.networkId = networkId;
			return this;
		}

		@Override
		public SubnetBuilder network(Network network) {
			m.networkId = network.getId();
			return this;
		}

		@Override
		public SubnetBuilder ipVersion(IPVersionType ipVersion) {
			m.ipVersion = ipVersion;
			return this;
		}

		@Override
		public SubnetBuilder cidr(String cidr) {
			m.cidr = cidr;
			return this;
		}

		@Override
		public SubnetBuilder addPool(String start, String end) {
			if (m.pools == null)
				m.pools = Lists.newArrayList();
			m.pools.add(new NeutronPool(start, end));
			return this;
		}

		@Override
		public SubnetBuilder tenantId(String tenantId) {
			m.tenantId = tenantId;
			return this;
		}

		@Override
		public SubnetBuilder tenant(Tenant tenant) {
			m.tenantId = tenant.getId();
			return this;
		}
		
		@Override
		public Subnet build() {
			return m;
		}

		@Override
		public SubnetBuilder from(Subnet in) {
			return this;
		}

	}
	
}
