package org.openstack4j.api.compute;

import java.util.List;
import java.util.Map;

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
	
	/**
     * List all host that the current tenant has access to
     * 
     * @return list of all hosts
     * @author Wang Ting/王婷
     */
    List<? extends HostResource> list();

    /**
     * Returns list of hosts filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     * @author Wang Ting/王婷
     */
    List<? extends HostResource> list(Map<String, String> filteringParams);

}
