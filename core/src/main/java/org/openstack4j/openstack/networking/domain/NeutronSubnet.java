package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.HostRoute;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Pool;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
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
	private List<NeutronHostRoute> hostRoutes;
	@JsonProperty("ip_version")
	private IPVersionType ipVersion;
	@JsonProperty("gateway_ip")
	private String gateway;
	private String cidr;

	public static SubnetBuilder builder() {
		return new SubnetConcreteBuilder();
	}
	
	@Override
	public SubnetBuilder toBuilder() {
		return new SubnetConcreteBuilder(this);
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
	@JsonIgnore
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
	@JsonIgnore
	@Override
	public List<? extends Pool> getAllocationPools() {
		return pools;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends HostRoute> getHostRoutes() {
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
				.add("tenant_id", tenantId).add("dns_nameservers", dnsNames).add("allocation_pools", pools)
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

	public static class SubnetConcreteBuilder extends ResourceBuilder<Subnet, SubnetConcreteBuilder> implements SubnetBuilder {

		private NeutronSubnet m;

		SubnetConcreteBuilder() {
		 this(new NeutronSubnet());
		}
		
		SubnetConcreteBuilder(NeutronSubnet m ) {
			this.m = m;
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
		public SubnetBuilder gateway(String gateway) {
			m.gateway = gateway;
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
		public SubnetBuilder enableDHCP(boolean enable) {
		  m.enableDHCP = enable;
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

		@Override
		protected Subnet reference() {
			return m;
		}

        @Override
        public SubnetBuilder addDNSNameServer(String host) {
            if (Strings.isNullOrEmpty(host))
                return this;
            
            if (m.dnsNames == null)
                m.dnsNames = Lists.newArrayList();
            
            m.dnsNames.add(host);
            return this;
        }

        @Override
        public SubnetBuilder addHostRoute(String destination, String nexthop) {
            Preconditions.checkArgument(nexthop != null && destination != null, "NextHop and Destination must have a value");
            if (m.hostRoutes == null)
                m.hostRoutes = Lists.newArrayList();
            
            m.hostRoutes.add(new NeutronHostRoute(destination, nexthop));
            return this;
        }

	}
	
}
