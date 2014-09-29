package org.openstack4j.openstack.compute.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.node.BinaryNode;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.NetworkCreate;
import org.openstack4j.model.compute.Personality;
import org.openstack4j.model.compute.Server.DiskConfig;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;

import com.google.common.collect.Lists;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;

@JsonRootName("server")
public class NovaServerCreate implements ServerCreate {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String adminPass;
	private String imageRef;
	private String flavorRef;
	private String accessIPv4;
	private String accessIPv6;
	private Integer min;
	private Integer max;
	private DiskConfig diskConfig = DiskConfig.MANUAL;
	private Map<String, String> metadata = new HashMap<String, String>();
	@JsonProperty("user_data")
	private String userData;
	@JsonProperty("key_name")
	private String keyName;
	@JsonProperty("availability_zone")
	private String availabilityZone;
	@JsonProperty("config_drive")
	private boolean configDrive;
	
	@JsonProperty("security_groups")
	private List<SecurityGroup> securityGroups;
	
	@JsonProperty("networks")
	private List<NovaNetworkCreate> networks = Lists.newArrayList();
	
	private List<Personality> personality;
        
        @JsonProperty("block_device_mapping_v2")
        private List<BlockDeviceMappingCreate> blockDeviceMapping = Lists.newArrayList();
	
	public static ServerCreateBuilder builder() {
		return new ServerCreateConcreteBuilder();
	}
	
	@Override
	public ServerCreateBuilder toBuilder() {
		return new ServerCreateConcreteBuilder(this);
	}
	
	public String getName() {
		return name;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public String getImageRef() {
		return imageRef;
	}
	public String getFlavorRef() {
		return flavorRef;
	}
	
	@Override
	public String getAccessIPv4() {
		return accessIPv4;
	}
	
	@Override
	public String getAccessIPv6() {
		return accessIPv6;
	}
	
	@Override
	public Integer getMin() {
		return min;
	}
	@Override
	public Integer getMax() {
		return max;
	}
	@Override
	public DiskConfig getDiskConfig() {
		return diskConfig;
	}
	@Override
	public String getKeyName() {
		return keyName;
	}
	
	@Override
	public String getUserData() {
		return userData;
	}
	
	@Override
	public Map<String, String> getMetaData() {
		return metadata;
	}
	
	@Override
	public List<? extends SecurityGroup> getSecurityGroups() {
		return securityGroups;
	}
	
	@Override
	public String getAvailabilityZone() {
		return availabilityZone;
	}
	
	public boolean isConfigDrive() {
		return configDrive;
	}
	
	@Override
	public List<? extends NetworkCreate> getNetworks() {
		return networks;
	}
	
	public List<Personality> getPersonality() {
		return personality;
	}
	
	@Override
	public void addPersonality(String path, String contents) {
		if (personality == null)
			personality = Lists.newArrayList();
		personality.add(new Personality(path, contents));
	}
	
	@Override
	public void addSecurityGroup(String name) {
		if (securityGroups == null)
			securityGroups = Lists.newArrayList();
		securityGroups.add(new SecurityGroupInternal(name));
	}
	
	@Override
	public void addNetwork(String id, String fixedIP) {
		networks.add(new NovaNetworkCreate(id, fixedIP));
	}
	
	static class SecurityGroupInternal implements SecurityGroup {
		
		private static final long serialVersionUID = 1L;
		private String name;
		
		SecurityGroupInternal() { }
		SecurityGroupInternal(String name) { this.name = name; }
		
		@Override
		public String getName() {
			return name;
		}
		
	}
	
	public static class ServerCreateConcreteBuilder implements ServerCreateBuilder {

		NovaServerCreate m;
		
		ServerCreateConcreteBuilder() {
			this(new NovaServerCreate());
		}
		
		ServerCreateConcreteBuilder(NovaServerCreate m) {
			this.m = m;
		}
		
		public ServerCreateConcreteBuilder name(String name) {
			m.name = name;
			return this;
		}
		
		public ServerCreateConcreteBuilder flavor(String flavorId) {
			m.flavorRef = flavorId;
			return this;
		}
		
		public ServerCreateConcreteBuilder flavor(Flavor flavor) {
			m.flavorRef = flavor.getId();
			return this;
		}
		
		public ServerCreateConcreteBuilder image(String imageId) {
			m.imageRef = imageId;
			return this;
		}
		
		public ServerCreateConcreteBuilder image(Image image) {
			m.imageRef = image.getId();
			return this;
		}
		
		@Override
		public ServerCreateConcreteBuilder networks(List<String> idList) {
		  if (idList != null) {
		    for (String id : idList) {
		      m.addNetwork(id, null);
		    }
		  }
		  return this;
		}
		
		@Override
		public ServerCreateBuilder addSecurityGroup(String name) {
			if (name != null && !name.isEmpty())
				m.addSecurityGroup(name);
			return this;
		}
		
		@Override
		public ServerCreateBuilder addPersonality(String path, String contents) {
			if (path == null || contents == null) return this;
			
			if (m.personality == null)
				m.personality = Lists.newArrayList();
			m.personality.add(new Personality(path, new BinaryNode(contents.getBytes()).asText()));
			return this;
		}
		
		@Override
		public ServerCreate build() {
			return m;
		}

		@Override
		public ServerCreateConcreteBuilder from(ServerCreate in) {
			m = (NovaServerCreate)in;
			return this;
		}

		@Override
		public ServerCreateBuilder keypairName(String name) {
			m.keyName = name;
			return this;
		}

                @Override
                public ServerCreateBuilder blockDevice(BlockDeviceMappingCreate blockDevice) {
                        m.blockDeviceMapping.add(blockDevice);
                        return this;
                }
	}
}
