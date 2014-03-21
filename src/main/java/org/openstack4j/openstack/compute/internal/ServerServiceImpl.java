package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.compute.ServerService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.RebootType;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;
import org.openstack4j.openstack.compute.domain.NovaServer;
import org.openstack4j.openstack.compute.domain.NovaServerCreate;
import org.openstack4j.openstack.compute.domain.NovaServer.Servers;

/**
 * Server Operation API implementation
 * 
 * @author Jeremy Unruh
 */
public class ServerServiceImpl extends BaseComputeServices implements ServerService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list() {
		return list(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list(boolean detail) {
		return get(Servers.class, uri("/servers" + ((detail) ? "/detail" : ""))).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Server get(String serverId) {
  	checkNotNull(serverId);
		return get(Server.class, uri("/servers/%s", serverId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Server boot(ServerCreate server) {
		checkNotNull(server);
		return post(NovaServer.class, uri("/servers")).entity(server).execute();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String serverId) {
		checkNotNull(serverId);
		delete(Void.class, uri("/servers/%s", serverId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse action(String serverId, Action action) {
  	checkNotNull(serverId);
  	
		switch (action) {
			case PAUSE: return invokeAction(serverId, "pause");
			case UNPAUSE: return invokeAction(serverId, "unpause");
			case LOCK: return invokeAction(serverId, "lock");
			case UNLOCK: return invokeAction(serverId, "unlock");
			case START: return invokeAction(serverId, "os-start");
			case STOP: return invokeAction(serverId, "os-stop");
			case RESUME: return invokeAction(serverId, "resume");
			case RESCUE: return invokeAction(serverId, "rescue");
			case UNRESCUE: return invokeAction(serverId, "unrescue");
			case SHELVE: return invokeAction(serverId, "shelve");
			case SHELVE_OFFLOAD: return invokeAction(serverId, "shelveOffload");
			case UNSHELVE: return invokeAction(serverId, "unshelve");
			default:
				return ActionResponse.actionFailed(String.format("Action %s was not found in the list of invokable actions", action));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String createSnapshot(String serverId, String snapshotName) {
  	checkNotNull(serverId);
  	checkNotNull(snapshotName);

		String body = String.format("{ \"name\": \"%s\", \"metadata\":{} }", snapshotName);
		HttpResponse response = executeActionWithResponse(serverId, "createImage", body);
		if (response.getStatus() == 202) {
			String location = response.unwrap().getHeaderString("location");
			if (location != null && location.contains("/"))
			{
				String[] s = location.split("/");
				return s[s.length - 1];
			}
				
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse reboot(String serverId, RebootType type) {
  	checkNotNull(serverId);

		String innerJson = String.format("{ \"type\": \"%s\" }", type.name());
		return invokeAction(serverId, "reboot", innerJson);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse resize(String serverId, String flavorId) {
		checkNotNull(serverId);
		checkNotNull(flavorId);
		return invokeAction(serverId, "resize", String.format("{ \"flavorRef\": \"%s\" }", flavorId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse confirmResize(String serverId) {
		checkNotNull(serverId);
		return invokeAction(serverId, "confirmResize");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse revertResize(String serverId) {
		checkNotNull(serverId);
		return invokeAction(serverId, "revertResize");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ? extends Number> diagnostics(String serverId) {
		return get(HashMap.class, uri("/servers/%s/diagnostics", serverId)).execute();
	}
	
	private ActionResponse invokeAction(String serverId, String action) {
		return invokeAction(serverId, action, null);
	}
	
	private ActionResponse invokeAction(String serverId, String action, String innerJson) {
		  HttpResponse response = executeActionWithResponse(serverId, action, innerJson);
		  if (response.getStatus() == 409)
		  {
		  	System.out.println("ERROR: " + response.unwrap().readEntity(String.class));
		  	return ActionResponse.actionFailed(String.format("Cannot '%s' while instance in in state of %s", action, action));
		  }
		return ActionResponse.actionSuccess();
	}
	
	private HttpResponse executeActionWithResponse(String serverId, String action, String innerJson) {
		return post(Void.class, uri("/servers/%s/action", serverId)).json(String.format("{ \"%s\": %s }", action, (innerJson != null) ? innerJson : "null")).executeWithResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServerCreateBuilder serverBuilder() {
		return NovaServerCreate.builder();
	}
}
