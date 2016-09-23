package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.HostResource;

/**
 * Nova OS Host Service
 * 
 * @author Qin An
 *
 */
public interface HostService extends RestService {

    /**
	 * Shows details for a specified host
	 *
	 * @param hostName
	 * @return the Resource of the Host specified
	 */
	public List<? extends HostResource> hostDescribe(String hostName);

}
