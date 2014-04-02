package org.openstack4j.api.types;

public enum ServiceType {

	IDENTITY("keystone"),
	COMPUTE("nova"),
	IMAGE("glance"),
	BLOCK_STORAGE("cinder"),
	NETWORK("neutron"),
	EC2("ec2"),
	UNKNOWN("NA")
	;
	
	private final String serviceName;
	ServiceType(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return this.serviceName;
	}
	
	public static ServiceType forName(String name) {
		for (ServiceType s : ServiceType.values())
		{
			if (s.getServiceName().equalsIgnoreCase(name))
			  return s;
		}
		return ServiceType.UNKNOWN;
	}
}
