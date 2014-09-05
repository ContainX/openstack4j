package org.openstack4j.openstack.compute.domain.ext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.compute.ext.AvailabilityZoneInfo;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ExtAvailabilityZoneInfo implements AvailabilityZoneInfo {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("availabilityZoneInfo")
	List<ExtAvailabilityZone> availabilityZoneInfo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends AvailabilityZone> getAvailabilityZoneInfo() {
		return availabilityZoneInfo;
	}
	
	static class ExtAvailabilityZone implements AvailabilityZone {
		private static final long serialVersionUID = 1L;
		
		ExtZoneState zoneState;
		String zoneName;
		@JsonIgnore
		HashMap<String, HashMap<String, ExtNovaService>> hosts;
			
		/**
		 * {@inheritDoc}
		 */
		@Override
		public ZoneState getZoneState() {
			return zoneState;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getZoneName() {
			return zoneName;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public HashMap<String, HashMap<String, ? extends NovaService>> getHosts() {
			HashMap<String, HashMap<String, ? extends NovaService>> map = new HashMap<String, HashMap<String, ? extends NovaService>>();
			for (Entry<String, HashMap<String, ExtNovaService>> entry : hosts.entrySet()) {
				map.put(entry.getKey(), entry.getValue());
			}
			return map;
		}
	}
	
	@JsonRootName("zoneState")
	static class ExtZoneState implements ZoneState {
		private static final long serialVersionUID = 1L;
		
		boolean available;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean getAvailable() {
			return available;
		}
	}
	
	static class ExtNovaService implements NovaService {
		private static final long serialVersionUID = 1L;
		
		String available;
		@JsonProperty("active")
		String statusActive;
		@JsonProperty("updated_at")
		Date updateTime;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getAvailable() {
			return available;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getStatusActive() {
			return statusActive;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Date getUpdateTime() {
			return updateTime;
		}
	}
}
