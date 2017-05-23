package org.openstack4j.api.storage.ext;

import java.util.List;

import org.openstack4j.model.storage.block.ext.Service;

/**
 * API which supports the "os-services" extension.
 *
 * @author Taemin
 */
public interface BlockStorageServiceService {

	/**
	 * List services info
	 *
	 * NOTE: This is an extension and not all deployments support os-services
	 *
	 * @return a list of block storage services
	 */
	List<? extends Service> list();
}