package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.bareMetal.NodeValidate;
import org.openstack4j.model.bareMetal.Validate;

public class BareMetalNodeValidate implements NodeValidate {

    @JsonProperty("boot")
    private BareMetalValidate boot;

    @JsonProperty("console")
    private BareMetalValidate console;

    @JsonProperty("deploy")
    private BareMetalValidate deploy;

    @JsonProperty("inspect")
    private BareMetalValidate inspect;

    @JsonProperty("management")
    private BareMetalValidate management;

    @JsonProperty("network")
    private BareMetalValidate network;

    @JsonProperty("power")
    private BareMetalValidate power;

    @JsonProperty("raid")
    private BareMetalValidate raid;

    @JsonProperty("rescue")
    private BareMetalValidate rescue;

    @JsonProperty("storage")
    private BareMetalValidate storage;

    public BareMetalNodeValidate() {
    }


    @Override
    public Validate getBoot() {
        return boot;
    }

    @Override
    public Validate getConsole() {
        return console;
    }

    @Override
    public Validate getDeploy() {
        return deploy;
    }

    @Override
    public Validate getInspect() {
        return inspect;
    }

    @Override
    public Validate getManagement() {
        return management;
    }

    @Override
    public Validate getNetwork() {
        return network;
    }

    @Override
    public Validate getPower() {
        return power;
    }

    @Override
    public Validate getRaid() {
        return raid;
    }

    @Override
    public Validate getRescue() {
        return rescue;
    }

    @Override
    public Validate getStorage() {
        return storage;
    }
}
