package org.openstack4j.openstack.networking.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.NetExternalFixedIps;
import org.openstack4j.model.network.builder.NetExternalFixedIpsBuilder;

@JsonRootName("externalFixedIps")
public class ExternalFixedIps implements  NetExternalFixedIps{
    private static final long serialVersionUID = 1L;

    @JsonProperty("ip_address")
    private String ipAddress;

    @JsonProperty("subnet_id")
    private String subnetId;

    public ExternalFixedIps(String ipAddress,String subnetId){
        this.ipAddress = ipAddress;
        this.subnetId = subnetId;
    }

    public ExternalFixedIps(){

    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    @Override
    public NetExternalFixedIpsBuilder toBuilder() {
        return null;
    }



}
