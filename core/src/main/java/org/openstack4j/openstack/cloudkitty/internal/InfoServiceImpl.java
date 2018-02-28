package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.cloudkitty.InfoService;
import org.openstack4j.model.cloudkitty.ServiceInfo;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittyServiceInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoServiceImpl extends CloudKittyBaseService implements InfoService {

    @Override
    public Map<String, String> config() {
        return get(HashMap.class, "/info/config").execute();
    }

    @Override
    public List<? extends ServiceInfo> services() {
        return get(CloudkittyServiceInfo.ServiceInfos.class, "/info/services").execute().getList();
    }

    @Override
    public ServiceInfo service(String service) {
        return get(CloudkittyServiceInfo.class, "/info/services/", service).execute();
    }
}
