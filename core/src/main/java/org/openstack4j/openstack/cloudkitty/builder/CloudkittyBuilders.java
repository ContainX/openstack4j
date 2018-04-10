package org.openstack4j.openstack.cloudkitty.builder;

import org.openstack4j.model.cloudkitty.builder.*;
import org.openstack4j.openstack.cloudkitty.domain.*;

public class CloudkittyBuilders {

    public HashMapBuilders hashmap() {
        return new HashMapBuilders();
    }

    public PyScriptsBuilders pyscripts() {
        return new PyScriptsBuilders();
    }

    public ResourceBuilder resource() {
        return CloudkittyResource.builder();
    }

    public RatedResourceBuilder ratedResource() {
        return CloudkittyRatedResource.builder();
    }

    public CollectorInfosBuilder collectorInfos() {
        return CloudkittyCollectorInfos.builder();
    }

    public DataFrameBuilder dataFrame() {
        return CloudkittyDataFrame.builder();
    }

    public ModuleBuilder module() {
        return CloudkittyModule.builder();
    }

    public ServiceInfoBuilder serviceInfo() {
        return CloudkittyServiceInfo.builder();
    }

    public ServiceToCollectorMappingBuilder serviceToCollectorMapping() {
        return CloudkittyServiceToCollectorMapping.builder();
    }

    public SummaryBuilder summary() {
        return CloudkittySummary.builder();
    }
}
