package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.HealthMonitorV2;
import org.openstack4j.model.network.ext.HealthMonitorV2Type;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.builder.HealthMonitorV2Builder;

import java.util.List;

/**
 * A lbaas v2 health monitor entity
 * @author emjburns
 */
@JsonRootName("health_monitor_v2")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronHealthMonitorV2 implements HealthMonitorV2{
    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    private HealthMonitorV2Type type;
    private Integer delay;
    private Integer timeout;

    /**
     * Number of allowed connection failures before changing the status of the member to INACTIVE. A valid value is from 1 to 10.
     */
    @JsonProperty("max_retries")
    private Integer maxRetries;

    /**
     * The HTTP method that the monitor uses for requests.
     */
    @JsonProperty("http_method")
    private String httpMethod ;

    /**
     * URL
     */
    @JsonProperty("url_path")
    private String urlPath ;

    /**
     * default 200
     */
    @JsonProperty("expected_codes")
    private String expectedCodes  ;

    /**
     * The administrative state of the health monitor, which is up (true) or down (false)
     */
    @JsonProperty("admin_state_up")
    private boolean adminStateUp ;

    private List<LbPoolV2> pools;

    @Override
    public String getId(){
        return id;
    }

    @Override
    public String getTenantId(){
        return tenantId;
    }

    @Override
    public HealthMonitorV2Type getType(){
        return type;
    }

    @Override
    public Integer getDelay(){
        return delay;
    }

    @Override
    public Integer getTimeout(){
        return timeout;
    }

    @Override
    public Integer getMaxRetries(){
        return maxRetries;
    }

    @Override
    public String getHttpMethod(){
        return httpMethod;
    }

    @Override
    public String getUrlPath(){
        return urlPath;
    }

    @Override
    public String getExpectedCodes(){
        return expectedCodes;
    }

    @Override
    public boolean isAdminStateUp(){
        return adminStateUp;
    }

    @Override
    public List<LbPoolV2> getPools(){
        return pools;
    }

    @Override
    public HealthMonitorV2Builder toBuilder(){
        //TODO implement builder
        return null;
    }

    public static HealthMonitorV2Builder builder(){
        //TODO: implement builder
        return null;
    }

    @Override
    public String toString(){
        return "NeutronHealthMonitorV2{" +
                "id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", type=" + type +
                ", delay=" + delay +
                ", timeout=" + timeout +
                ", maxRetries=" + maxRetries +
                ", httpMethod='" + httpMethod + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", expectedCodes='" + expectedCodes + '\'' +
                ", adminStateUp=" + adminStateUp +
                ", pools=" + pools +
                '}';
    }
}
