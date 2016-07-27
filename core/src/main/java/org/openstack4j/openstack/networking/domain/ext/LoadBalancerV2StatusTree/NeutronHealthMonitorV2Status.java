package org.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.HealthMonitorType;
import org.openstack4j.model.network.ext.status.HealthMonitorV2Status;

/**
 * An object to hold status of lbaas v2 healthmonitor
 * @author emjburns
 */
@JsonRootName("healthmonitor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NeutronHealthMonitorV2Status extends Status implements HealthMonitorV2Status {

    @JsonProperty("type")
    private HealthMonitorType type;

    public HealthMonitorType getType(){
        return type;
    }

    @Override
    public String toString(){
        return "heathMonitorStatus{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", provisioningStatus='" + provisioningStatus + '\'' +
                '}';
    }
}
