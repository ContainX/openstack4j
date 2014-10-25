package org.openstack4j.api.types;

public enum ServiceType {

	IDENTITY("keystone", "identity"),
	COMPUTE("nova", "compute"),
	IMAGE("glance", "image"),
	BLOCK_STORAGE("cinder", "volume"),
	OBJECT_STORAGE("object-store", "object-store"),
	NETWORK("neutron", "network"),
	EC2("ec2", "ec2"),
	TELEMETRY("ceilometer", "metering"),
	ORCHESTRATION("heat", "orchestration"),
	UNKNOWN("NA", "NA")
	;
	
	private final String serviceName;
	private final String typeV3;
	
	ServiceType(String serviceName, String typeV3) {
		this.serviceName = serviceName;
		this.typeV3 = typeV3;
	}
	
	public String getServiceName() {
		return this.serviceName;
	}
	
	public String getTypeV3() {
		return this.typeV3;
	}
	
	public static ServiceType forName(String name) {
		for (ServiceType s : ServiceType.values())
		{
			if (s.getServiceName().equalsIgnoreCase(name))
			  return s;
			if (s.name().equalsIgnoreCase(name))
				return s;
			if (s.typeV3.equalsIgnoreCase(name))
				return s;
		}
		return ServiceType.UNKNOWN;
	}
}
