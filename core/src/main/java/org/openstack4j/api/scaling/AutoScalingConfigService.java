package org.openstack4j.api.scaling;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingConfig;
import org.openstack4j.model.scaling.ScalingConfigCreate;
import org.openstack4j.openstack.scaling.options.ScalingConfigListOptions;

public interface AutoScalingConfigService extends RestService {
	
	public ScalingConfigCreate create(ScalingConfigCreate config);
	
	public List<? extends ScalingConfig> list(ScalingConfigListOptions options);
	
	public List<? extends ScalingConfig> list();
	
	public ScalingConfigCreate get(String configId);
	
	public ActionResponse delete(String configId);
	
	public ActionResponse delete(List<String> configIds);
}
