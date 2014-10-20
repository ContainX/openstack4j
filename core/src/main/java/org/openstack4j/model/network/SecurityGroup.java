package org.openstack4j.model.network;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.NetSecurityGroupBuilder;

/**
 * The Interface SecurityGroup.
 *
 * @author Nathan Anderson
 */
public interface SecurityGroup extends Resource, Buildable<NetSecurityGroupBuilder>{
  
  /**
   * Gets the description.
   *
   * @return the description
   */
  String getDescription();
  
  /**
   * Gets the rules.
   *
   * @return the rules
   */
  List<? extends SecurityGroupRule> getRules();
  
}
