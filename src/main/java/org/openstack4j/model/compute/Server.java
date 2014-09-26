package org.openstack4j.model.compute;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.Link;

/**
 * A server is a virtual machine instance on a compute based system.  
 * 
 * @author Jeremy Unruh
 */
public interface Server extends ModelEntity {

	/**
	 * Servers contain a status attribute that can be used as an indication of the current server
	 * state. Servers with an ACTIVE status are available for use.
	 *
	 * @author Jeremy Unruh
	 */
	public enum Status {
		ACTIVE, BUILD, REBUILD, SUSPENDED, PAUSED, RESIZE, VERIFY_RESIZE, REVERT_RESIZE, PASSWORD, REBOOT, HARD_REBOOT, DELETED, UNKNOWN, ERROR, STOPPED, SHUTOFF, UNRECOGNIZED;

		@JsonCreator
		public static Status forValue(String value) {
			if (value != null)
			{
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return Status.UNRECOGNIZED;
		}
	}
	
	enum DiskConfig {
		MANUAL, AUTO;
		
		@JsonCreator
		public static DiskConfig forValue(String value) {
			if (value != null && value.equalsIgnoreCase("auto"))
				return DiskConfig.AUTO;
			return DiskConfig.MANUAL;
		}
	}

	/**
	 * @return the identifier for the server
	 */
	String getId();

	/**
	 * @return the descriptive name for the server
	 */
	String getName();

	/**
	 * @return the addresses assigned to the server
	 */
	Addresses getAddresses();

	/**
	 * @return external reference links
	 */
	List<? extends Link> getLinks();

	/**
	 * @return the image identifier
	 */
	String getImageId();
	
	/**
	 * @return the image used to boot the server with
	 */
	Image getImage();

	/**
	 * @return the flavor identifier
	 */
	String getFlavorId();
	
	/**
	 * @return the flavor to boot into
	 */
	Flavor getFlavor();

	/**
	 * @return the accessible IPV4 address (if applicable)
	 */
	String getAccessIPv4();

	/**
	 * @return the accessible IPV6 address (if applicable)
	 */
	String getAccessIPv6();

	/**
	 * @return the configDrive
	 */
	String getConfigDrive();

	/**
	 * @return the status
	 */
	Status getStatus();

	/**
	 * @return the progress
	 */
	int getProgress();

	/**
	 * @return the fault
	 */
	Fault getFault();

	/**
	 * @return the tenant id
	 */
	String getTenantId();

	/**
	 * @return the user id
	 */
	String getUserId();

	/**
	 * @return the key name
	 */
	String getKeyName();

	/**
	 * @return the host id
	 */
	String getHostId();

	/**
	 * @return the updated
	 */
	Date getUpdated();

	/**
	 * @return the created
	 */
	Date getCreated();

	/**
	 * @return the metadata
	 */
	Map<String, String> getMetadata();

	//List<SecurityGroup> getSecurityGroups();

	/**
	 * @return the task state
	 */
	String getTaskState();

	/**
	 * @return the power state
	 */
	String getPowerState();

	/**
	 * @return the vm state
	 */
	String getVmState();

	/**
	 * @return the host
	 */
	String getHost();

	/**
	 * @return the instance name
	 */
	String getInstanceName();

	/**
	 * @return the hypervisor hostname
	 */
	String getHypervisorHostname();

	/**
	 * Disk config attribute from the Disk Config Extension (alias "OS-DCF")..
	 *
	 * @return the current disk configuration
	 */
	DiskConfig getDiskConfig();

	/**
	 * @return the availability zone
	 */
	String getAvailabilityZone();

	/**
	 * @return the last time the server was launched
	 */
	Date getLaunchedAt();

	/**
	 * @return the last termination date
	 */
	Date getTerminatedAt();

	/**
	 * @return the OS extended volumes attached
	 */
	List<String> getOsExtendedVolumesAttached();

	/**
	 * @return the uuid for this server
	 */
	String getUuid();

	/**
	 * @return the administrative password to the VM
	 */
	String getAdminPass();

}
