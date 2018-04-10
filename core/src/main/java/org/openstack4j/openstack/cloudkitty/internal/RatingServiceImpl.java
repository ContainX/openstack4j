package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.cloudkitty.*;
import org.openstack4j.api.cloudkitty.ReportService;
import org.openstack4j.api.cloudkitty.StorageService;
import org.openstack4j.api.cloudkitty.hashmap.HashMapService;
import org.openstack4j.api.cloudkitty.pyscripts.PyScriptsService;
import org.openstack4j.model.cloudkitty.Resource;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudkitty.domain.CloudkittyResource;

import java.util.ArrayList;
import java.util.List;

public class RatingServiceImpl extends CloudKittyBaseService implements RatingService {

    @Override
    public InfoService info() {
        return Apis.get(InfoService.class);
    }

    @Override
    public CollectorService collector() {
        return Apis.get(CollectorService.class);
    }

    @Override
    public ReportService reporting() {
        return Apis.get(ReportService.class);
    }

    @Override
    public ModuleService module() {
        return Apis.get(ModuleService.class);
    }

    @Override
    public StorageService storage() {
        return Apis.get(StorageService.class);
    }

    @Override
    public HashMapService hashmap() {
        return Apis.get(HashMapService.class);
    }

    @Override
    public PyScriptsService pyscripts() {
        return Apis.get(PyScriptsService.class);
    }

    @Override
    public float quote(List<? extends Resource> resData) {
        List<CloudkittyResource> resources = new ArrayList<>();
        for (Resource res : resData) {
            resources.add((CloudkittyResource) res);
        }
        return post(Float.class, "/rating/quote")
                .entity(new CloudkittyResource.Resources(resources))
                .execute();
    }

    @Override
    public ActionResponse reload() {
        return get(ActionResponse.class, "/rating/reload_modules").execute();
    }
}
