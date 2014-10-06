package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.Limits;
import org.openstack4j.model.compute.QuotaSet;
import org.openstack4j.model.compute.SimpleTenantUsage;

/**
 * OpenStack Quota-Set Operation API
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSetService extends RestService {

	/**
	 * Gets the QuotaSet for the given tenant ID
	 *
	 * @param tenantId the tenant id
	 * @return the quota set
	 */
	QuotaSet get(String tenantId);
	
	/**
	 * Gets the QuotaSet for the given tenant and user ID
	 * @param tenantId the identifier for the tenant
	 * @param userId the identifier of the user
	 * @return the quota set
	 */
	QuotaSet get(String tenantId, String userId);
	
	/**
	 * Accounts may be pre-configured with a set of thresholds (or limits) to manage capacity and prevent system abuse.  This call will
	 * return the current Rate and Absolute Limits.
	 * @return the system limits
	 */
	Limits limits();
	
	/**
	 * Gets tenant usage information for all tenants (os-simple-tenant-usage)
	 * 
	 * @return list of usage information for all tenants
	 */
	List<? extends SimpleTenantUsage> listTenantUsages();
	
	/**
	 * Gets the usage information for the specified tenant (os-simple-tenant-usage)
	 * 
	 * @param tenantId the tenant identifier
	 * @return Tenant Usage or null if not found
	 */
	SimpleTenantUsage getTenantUsage(String tenantId);
	
}
