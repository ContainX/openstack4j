package org.openstack4j.openstack.compute.domain.ext;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.compute.ext.Hypervisor;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;

public class ExtHypervisor implements Hypervisor {

	private static final long serialVersionUID = 1L;

	private String id;

	@JsonProperty("current_workload")
	private int currentWorkload;
	@JsonProperty("disk_available_least")
	private int leastDiskAvail;
	@JsonProperty("free_disk_gb")
	private int freeDisk;
	@JsonProperty("free_ram_mb")
	private int freeRam;
	@JsonProperty("hypervisor_type")
	private String type;
	@JsonProperty("hypervisor_hostname")
	private String hostname;
	@JsonProperty("host_ip")
	private String hostIP;
	private int version;
	@JsonProperty("running_vms")
	private int running;
	@JsonProperty("vcpus")
	private int vcpu;
	@JsonProperty("vcpus_used")
	private int usedVcpu;
	@JsonProperty("local_gb")
	private int localDisk;
	@JsonProperty("local_gb_used")
	private int localDiskUsed;
	@JsonProperty("memory_mb")
	private int localMemory;
	@JsonProperty("memory_mb_used")
	private int localMemoryUsed;
	@JsonProperty("service")
	private HypervisorService service;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getCurrentWorkload() {
		return currentWorkload;
	}

	@Override
	public int getLeastDiskAvailable() {
		return leastDiskAvail;
	}

	@Override
	public int getFreeDisk() {
		return freeDisk;
	}

	@Override
	public int getFreeRam() {
		return freeRam;
	}

	@Override
	public String getHypervisorHostname() {
		return hostname;
	}

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public int getRunningVM() {
		return running;
	}

	@Override
	public int getVirtualCPU() {
		return vcpu;
	}

	@Override
	public int getVirtualUsedCPU() {
		return usedVcpu;
	}
	
	@Override
	public int getLocalDisk() {
		return localDisk;
	}

	@Override
	public int getLocalDiskUsed() {
		return localDiskUsed;
	}

	@Override
	public int getLocalMemory() {
		return localMemory;
	}

	@Override
	public int getLocalMemoryUsed() {
		return localMemoryUsed;
	}

	@Override
	public Service getService() {
		return service;
	}
	
	@Override
	public String getHostIP() {
		return hostIP;
	}

	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("hypervisor_hostname", hostname).add("version", version).add("type", type)
				     .add("host_ip", hostIP).add("running", running).add("freeDisk", freeDisk).add("freeRam", freeRam)
				     .add("vcpus", vcpu).add("usedVcpu", usedVcpu).add("localDisk", localDisk).add("localDiskUsed", localDiskUsed)
				     .add("localMemory", localMemory).add("localMemoryUsed", localMemoryUsed).add("currentWorkload",currentWorkload)
				     .add("leastDiskAvail", leastDiskAvail).add("running_vms", running).add("service", service)
				     .toString();
	}

	@JsonRootName("service")
	static class HypervisorService implements Service {

		private static final long serialVersionUID = 1L;
		private String host;
		private String id;
		
		@Override
		public String getHost() {
			return host;
		}

		@Override
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			return Objects.toStringHelper(this).omitNullValues().add("id", id).add("host", host).toString();
		}
	}
	
  public static class Hypervisors extends ListResult<ExtHypervisor> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("hypervisors")
  	List<ExtHypervisor> hypervisors;
  	
		@Override
		protected List<ExtHypervisor> value() {
			return hypervisors;
		}
  	
  }
}
