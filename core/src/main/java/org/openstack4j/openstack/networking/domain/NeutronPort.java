package org.openstack4j.openstack.networking.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.ExtraDhcpOptCreate;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.State;
import org.openstack4j.model.network.builder.PortBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A Neutron Port
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("port")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronPort implements Port {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	@JsonProperty("admin_state_up")
	private boolean adminStateUp = true;

	@JsonProperty("device_id")
	private String deviceId;

	@JsonProperty("device_owner")
	private String deviceOwner;

	@JsonProperty("fixed_ips")
	private Set<NeutronIP> fixedIps;

	@JsonProperty("mac_address")
	private String macAddress;

	@JsonProperty("network_id")
	private String networkId;

	@JsonProperty("status")
	private State state;

	@JsonProperty("tenant_id")
	private String tenantId;

	@JsonProperty("security_groups")
	private List<String> securityGroups;
	
        @JsonProperty("extra_dhcp_opts")
	private List<NeutronExtraDhcpOptCreate> extraDhcpOptCreates = Lists.newArrayList();
        
	public static PortBuilder builder() {
		return new PortConcreteBuilder();
	}
	
	@Override
	public PortBuilder toBuilder() {
		return new PortConcreteBuilder(this);
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
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getState() {
		return state;
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
	public String getNetworkId() {
		return networkId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDeviceOwner() {
		return deviceOwner;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<? extends IP> getFixedIps() {
		return fixedIps;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getSecurityGroups() {
		return securityGroups;
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
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				    .add("id", id).add("name", name).add("adminStateUp", adminStateUp).add("deviceId", deviceId)
				    .add("deviceOwner", deviceOwner).add("fixedIps", fixedIps).add("macAddress", macAddress)
				    .add("networkId", networkId).add("tenantId", tenantId).add("securityGroups", securityGroups)
				    .toString();
	}
	
	public static class Ports extends ListResult<NeutronPort> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("ports")
		private List<NeutronPort> ports;
		
		@Override
		protected List<NeutronPort> value() {
			return ports;
		}
	}
	
	public static class PortConcreteBuilder extends ResourceBuilder<Port, PortConcreteBuilder> implements PortBuilder {

		private NeutronPort m;
		
		PortConcreteBuilder() { 
			this(new NeutronPort());
		}
		
		PortConcreteBuilder(NeutronPort port) {
			this.m = port;
		}
		@Override
		public PortBuilder networkId(String networkId) {
			m.networkId = networkId;
			return this;
		}

		@Override
		public PortBuilder deviceId(String deviceId) {
			m.deviceId = deviceId;
			return this;
		}

		@Override
		public PortBuilder deviceOwner(String deviceOwner) {
			m.deviceOwner = deviceOwner;
			return this;
		}

		@Override
		public PortBuilder macAddress(String macAddress) {
			m.macAddress = macAddress;
			return this;
		}

		@Override
		public PortBuilder fixedIp(String address, String subnetId) {
			if (m.fixedIps == null)
				m.fixedIps = Sets.newHashSet();
			
			m.fixedIps.add(new NeutronIP(address, subnetId));
			return this;
		}
		
    @Override
    public PortBuilder removeFixedIp(String address, String subnetId) {
      if (m.fixedIps == null)
        m.fixedIps = Sets.newHashSet();
      
      Iterator<NeutronIP> iter = m.fixedIps.iterator();
      
      while (iter.hasNext()) {
        NeutronIP fixedIP = iter.next();
        if (fixedIP.getSubnetId() != null && fixedIP.getSubnetId().equals(subnetId) && 
            fixedIP.getIpAddress() != null && fixedIP.getIpAddress().equals(address)) {
          iter.remove();
        }
      }
      
      return this;
    }

		@Override
		public PortBuilder adminState(boolean adminStateUp) {
			m.adminStateUp = adminStateUp;
			return this;
		}

		@Override
		public PortBuilder state(State state) {
			m.state = state;
			return this;
		}
		
		@Override
		public Port build() {
			return m;
		}

		@Override
		public PortBuilder from(Port in) {
			m = (NeutronPort) in;
			return this;
		}

		@Override
		protected Port reference() {
			return m;
		}

                @Override
                public PortBuilder extraDhcpOpt(ExtraDhcpOptCreate extraDhcpOptCreate) {
                        m.extraDhcpOptCreates.add((NeutronExtraDhcpOptCreate)extraDhcpOptCreate);
                        return this;
                }

		@Override
		public PortBuilder securityGroup(String groupName) {
			if(m.securityGroups==null){
				m.securityGroups = new ArrayList<String>();
			}
			m.securityGroups.add(groupName);
			return this;
		}
		
		
                
                
	}
}
