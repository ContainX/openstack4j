package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.network.SecurityGroupRule;


/**
 * Provides Neutron-based Security Group Rule services.
 *
 * @author Nathan Anderson
 */
public interface SecurityGroupRuleService extends RestService {
  
  /**
   * List security group rules accessible by current Tenant.
   *
   * @return the list<? extends security group rules>
   */
  List<? extends SecurityGroupRule> list();
  
  /**
   * Gets the Security Group rule by id
   *
   * @param id the id
   * @return the security group rule
   */
  SecurityGroupRule get(String id);
  
  /**
   * Delete security group rule by id.
   *
   * @param id the id
   */
  void delete(String id);
  
  /**
   * Creates a security group rule.
   *
   * @param rule the rule
   * @return the security group rule
   */
  SecurityGroupRule create(SecurityGroupRule rule);
  
}
