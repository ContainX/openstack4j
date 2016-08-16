package org.openstack4j.openstack.networking.domain;

import java.util.List;
import java.util.Set;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.Port;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Represents a Port that is used during a create operation which only encapsulates the allowed properties
 * 
 * @author Jeremy Unruh
 *
 */
@JsonRootName("port")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronPortCreate implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String name;

	@JsonProperty("admin_state_up")
	private boolean adminStateUp;

	@JsonProperty("fixed_ips")
	private Set<NeutronIP> fixedIps;
	
	@JsonProperty("mac_address")
	private String macAddress;

	@JsonProperty("network_id")
	private String networkId;

	@JsonProperty("tenant_id")
	private String tenantId;

	@JsonProperty("security_groups")
	private List<String> securityGroups;
	
    @JsonProperty("port_security_enabled")
    private boolean portSecurityEnabled; 
	
	public NeutronPortCreate() {
	}
	
	/**
	 * Creates a Port Create object which only encapsulates allowable fields from a port
	 * 
	 * @param port the port (source)
	 * @return the port create object
	 */
	@SuppressWarnings("unchecked")
    public static NeutronPortCreate fromPort(Port port) {
		NeutronPortCreate c = new NeutronPortCreate();
		c.name = port.getName();
		c.networkId = port.getNetworkId();
		c.adminStateUp = port.isAdminStateUp();
		c.macAddress = port.getMacAddress();
		c.tenantId = port.getTenantId();
		c.securityGroups = port.getSecurityGroups();
		c.fixedIps = (Set<NeutronIP>) port.getFixedIps();
		c.portSecurityEnabled=port.isPortSecurityEnabled();
		
		return c;
	}
	
}
