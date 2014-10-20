package org.openstack4j.openstack.heat.internal;


import org.openstack4j.api.Apis;
import org.openstack4j.api.heat.EventsService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.heat.ResourcesService;
import org.openstack4j.api.heat.SoftwareConfigService;
import org.openstack4j.api.heat.StackService;
import org.openstack4j.api.heat.TemplateService;

/**
 * This class contains getters for all implementation of the available Heat services
 * 
 * @author Matthias Reisser
 */
public class HeatServiceImpl extends BaseHeatServices implements HeatService {

	@Override
	public StackService stacks() {
		return Apis.get(StackService.class);
	}

	@Override
	public TemplateService templates() {
		return Apis.get(TemplateService.class);
	}

	@Override
	public EventsService events() {
		return Apis.get(EventsService.class);
	}

	@Override
	public ResourcesService resources() {
		return Apis.get(ResourcesService.class);
	}

    @Override
    public SoftwareConfigService softwareConfig() {
        return Apis.get(SoftwareConfigService.class);
    }
	

}
