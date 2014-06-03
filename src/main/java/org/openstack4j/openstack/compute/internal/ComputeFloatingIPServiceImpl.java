package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.compute.ComputeFloatingIPService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.FloatingIP;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP.NovaFloatingIPs;
import org.openstack4j.openstack.compute.domain.NovaFloatingIPPools;

/**
 * OpenStack Floating-IP API Implementation
 * 
 * @author Nathan Anderson
 */
public class ComputeFloatingIPServiceImpl extends BaseComputeServices implements ComputeFloatingIPService {

  /**
   * {@inheritDoc}
   */
	@Override
  public List<? extends FloatingIP> list() {
    return get(NovaFloatingIPs.class, uri("/os-floating-ips")).execute().getList();
  }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getPoolNames() {
		return get(NovaFloatingIPPools.class, uri("/os-floating-ip-pools")).execute().getList();
	}
	
	/**
   * {@inheritDoc}
   */
  @Override
  public FloatingIP allocateIP(String pool) {
    String uri = uri("/os-floating-ips");
    return post(NovaFloatingIP.class, uri).json(String.format("{ \"pool\": \"%s\" }", pool)).execute();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deallocateIP(String id) {
    checkNotNull(id);
    delete(Void.class, uri("/os-floating-ips/%s", id)).execute();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ActionResponse addFloatingIP(Server server, String fixedIpAddress, String ipAddress) {
  	if (fixedIpAddress == null)
  		return addFloatingIP(server, ipAddress);
  	
    checkNotNull(server);
    checkNotNull(fixedIpAddress);
    checkNotNull(ipAddress);
    
    String action = "addFloatingIp";
    String innerJson = String.format("{ \"fixed_address\":%s, \"address\": \"%s\" }", fixedIpAddress, ipAddress);
    
    String uri = uri("/servers/%s/action", server.getId());
    return invokeAction(uri, action, innerJson);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
	public ActionResponse addFloatingIP(Server server, String ipAddress) {
  	checkNotNull(server);
    checkNotNull(ipAddress);
    
    String action = "addFloatingIp";
    String innerJson = String.format("{ \"address\": \"%s\" }", ipAddress);
    
    String uri = uri("/servers/%s/action", server.getId());
    return invokeAction(uri, action, innerJson);
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public ActionResponse removeFloatingIP(Server server, String ipAddress) {
    checkNotNull(server);
    
    String action = "removeFloatingIp";
    String innerJson = String.format("{ \"address\": \"%s\" }", ipAddress);
    
    String uri = uri("/servers/%s/action", server.getId());
    return invokeAction(uri, action, innerJson);
    
  }
  
  private ActionResponse invokeAction(String uri, String action, String innerJson) {
    HttpResponse response = post(Void.class, uri).json(String.format("{ \"%s\": %s }", action, innerJson)).executeWithResponse();
    if (response.getStatus() == 409)
    {
      System.out.println("ERROR: " + response.unwrap().readEntity(String.class));
      return ActionResponse.actionFailed(String.format("Cannot '%s' while instance in in state of %s", action, action));
    }
    return ActionResponse.actionSuccess();
  }
}
