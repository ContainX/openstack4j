package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.bareMetal.Validate;

public class BareMetalValidate implements Validate {

    @JsonProperty("result")
    private Boolean result;

    @JsonProperty("reason")
    private String reason;

    @Override
    public Boolean getResult() {
        return result;
    }

    @Override
    public String getReason() {
        return reason;
    }
}
