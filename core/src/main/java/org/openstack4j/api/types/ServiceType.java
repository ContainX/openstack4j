package org.openstack4j.api.types;

public enum ServiceType {

	IDENTITY("keystone", "identity"),
	APP_CATALOG("murano", "application-catalog"),
	COMPUTE("nova", "compute"),
	IMAGE("glance", "image"),
	BLOCK_STORAGE("cinder", "volume"),
	OBJECT_STORAGE("object-store", "object-store"),
	NETWORK("neutron", "network"),
	OCTAVIA("octavia", "load-balancer"),
	EC2("ec2", "ec2"),
	TELEMETRY("ceilometer", "metering"),
	TELEMETRY_AODH("aodh", "alarming"),
	ORCHESTRATION("heat", "orchestration"),
	CLUSTERING("senlin", "clustering"),
	SAHARA("sahara", "data_processing"),
	SHARE("manila", "share"),
	DATABASE("trove","database"),
	BARBICAN("barbican", "key-manager"),
	TACKER("tacker", "nfv-orchestration"),
	ARTIFACT("glare", "artifact"),
  	MAGNUM("magnum", "container"),
	DNS("designate", "dns"),
	WORKFLOW("mistral", "workflow"),
	UNKNOWN("NA", "NA")
	;

	private final String serviceName;
	private final String type;

	ServiceType(String serviceName, String type) {
		this.serviceName = serviceName;
		this.type = type;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public String getType() {
		return this.type;
	}

	public static ServiceType forName(String name) {
            if (name == null || name.isEmpty()) {
                return ServiceType.UNKNOWN;
            }

            for (ServiceType s : ServiceType.values()) {
                if (name.toLowerCase().startsWith(s.getServiceName().toLowerCase())) {
                    return s;
                }
                if (name.toLowerCase().startsWith(s.name().toLowerCase())) {
                    return s;
                }
                if (name.toLowerCase().startsWith(s.type.toLowerCase())) {
                    return s;
                }
            }
            return ServiceType.UNKNOWN;
        }
}
