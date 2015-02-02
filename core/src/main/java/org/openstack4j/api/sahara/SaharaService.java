package org.openstack4j.api.sahara;

import java.util.List;

import org.openstack4j.common.RestService;

/**
 * (Sahara) Data Processing Operations API
 * 
 * @author Ekasit Kijsipongse
 */
public interface SaharaService extends RestService {

	/**
	 * Cluster Service API
	 *
	 * @return the cluster service
	 */
	ClusterService clusters();
	
	/**
	 * Node Group Template Service API
	 *
	 * @return the node group template service
	 */
	NodeGroupTemplateService nodeGroupTemplates();

	/**
	 * Cluster Template Service API
	 *
	 * @return the cluster template service
	 */
	ClusterTemplateService clusterTemplates();
}
