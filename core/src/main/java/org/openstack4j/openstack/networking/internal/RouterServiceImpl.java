package org.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.openstack4j.api.networking.RouterService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.RouterInterface;
import org.openstack4j.openstack.networking.domain.AddRouterInterfaceAction;
import org.openstack4j.openstack.networking.domain.NeutronRouter;
import org.openstack4j.openstack.networking.domain.NeutronRouter.Routers;
import org.openstack4j.openstack.networking.domain.NeutronRouterInterface;

/**
 * RouterService implementation that provides Neutron Router based Service Operations.
 *
 * @author Jeremy Unruh
 */
public class RouterServiceImpl extends BaseNetworkingServices implements RouterService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Router> list() {
		return get(Routers.class, uri("/routers")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Router get(String routerId) {
		checkNotNull(routerId);
		return get(NeutronRouter.class, uri("/routers/%s", routerId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String routerId) {
		checkNotNull(routerId);
		return deleteWithResponse(uri("/routers/%s", routerId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Router create(String name, boolean adminStateUp) {
		checkNotNull(name);
		return post(NeutronRouter.class, uri("/routers")).entity(NeutronRouter.builder().name(name).adminStateUp(adminStateUp).build()).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Router create(Router router) {
		checkNotNull(router);
		return post(NeutronRouter.class, uri("/routers")).entity(router).execute(); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Router update(Router router) {
		checkNotNull(router);
		checkNotNull(router.getId());
		return put(NeutronRouter.class, uri("/routers/%s", router.getId()))
				     .entity(NeutronRouter.builder().name(router.getName()).adminStateUp(router.isAdminStateUp()).externalGateway(router.getExternalGatewayInfo()).build())
				     .execute();
	}

	@Override
	public Router toggleAdminStateUp(String routerId, boolean adminStateUp) {
		checkNotNull(routerId);
		return put(NeutronRouter.class, uri("/routers/%s", routerId)).entity(NeutronRouter.builder().adminStateUp(adminStateUp).build()).execute();
	}

	@Override
	public RouterInterface attachInterface(String routerId, AttachInterfaceType type, String portOrSubnetId) {
	  checkNotNull(routerId);
	  checkNotNull(type);
	  checkNotNull(portOrSubnetId);
		return put(NeutronRouterInterface.class, uri("/routers/%s/add_router_interface", routerId))
		          .entity(AddRouterInterfaceAction.create(type, portOrSubnetId))
				  .execute();
	}

	@Override
	public RouterInterface detachInterface(String routerId, String subnetId, String portId) {
		checkNotNull(routerId);
		checkState(!(subnetId == null && portId == null), "Either a Subnet or Port identifier must be set");
		return put(NeutronRouterInterface.class, uri("/routers/%s/remove_router_interface", routerId)).entity(new NeutronRouterInterface(subnetId, portId)).execute();
	}

}
