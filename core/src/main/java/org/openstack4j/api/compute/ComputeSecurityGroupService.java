package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.SecGroupExtension.Rule;

/**
 * Provides operations against the Security Group extension in OpenStack
 * 
 * Extension Mapping: (os-security-groups)
 * 
 * @author Jeremy Unruh
 */
public interface ComputeSecurityGroupService extends RestService {

	/**
	 * List all the Security Groups
	 * 
	 * @return the list of security groups
	 */
	List<? extends SecGroupExtension> list();
	
	/**
	 * Lists the Security Groups for the specified server
	 * @param serverId the server identifier
	 * @return the list of security groups
	 */
	List<? extends SecGroupExtension> listServerGroups(String serverId);
	
	/**
	 * Gets the specified Security Group
	 * @param securityGroupId the security group identifier
	 * @return the security group or Null if not found
	 */
	SecGroupExtension get(String securityGroupId);

	/**
	 * Creates a new Security Group
	 * @param name the name of the security group
	 * @param description the description of the security group
	 * @return the newly created security group
	 */
	SecGroupExtension create(String name, String description);

	/**
	 * Updates the Name and Description for a Security Group
	 * @param securityGroupId the security group identifier
	 * @param name the name of the security group
	 * @param description the description of the security group
	 * @return the newly created security group
	 */
	SecGroupExtension update(String securityGroupId, String name, String description);
	
	/**
	 * Deletes the specified Security Group
	 * @param securityGroupId the security group identifier
	 * @return the action response
	 */
	ActionResponse delete(String securityGroupId);
	
	/**
	 * Creates a new Security Group Rule
	 * @param rule the rule to create
	 * @return the newly created rule
	 */
	Rule createRule(Rule rule);
	
	/**
	 * Deletes a Security Group Rule
	 * @param ruleId the rule identifier
	 * @return the action response
	 */
	ActionResponse deleteRule(String ruleId);
	
}
