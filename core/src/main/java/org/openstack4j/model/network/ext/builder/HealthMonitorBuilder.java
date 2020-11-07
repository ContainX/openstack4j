package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.HealthMonitor;
import org.openstack4j.model.network.ext.HealthMonitorType;

/**
 * A builder to create a healthMonitor
 *
 * @author liujunpeng
 */
public interface HealthMonitorBuilder extends Builder<HealthMonitorBuilder, HealthMonitor> {

    /**
     * @param tenantId Owner of the VIP. Only an administrative user can specify a
     *                 tenant ID other than its own.
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder tenantId(String tenantId);

    /**
     * @param type The type of probe, which is PING, TCP, HTTP, or HTTPS, that is
     *             sent by the load balancer to verify the member state.
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder type(HealthMonitorType type);

    /**
     * @param delay The time, in seconds, between sending probes to members.
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder delay(Integer delay);

    /**
     * @param timeout Time in seconds to timeout each probe.
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder timeout(Integer timeout);

    /**
     * @param maxRetries Maximum consecutive health probe tries.
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder maxRetries(Integer maxRetries);

    /**
     * @param urlPath Path portion of URI that will be probed if type is HTTP(S).
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder urlPath(String urlPath);

    /**
     * @param expectedCodes Expected HTTP codes for a passing HTTP(S) monitor.
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder expectedCodes(String expectedCodes);

    /**
     * @param httpMethod GET/PUT/POST
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder httpMethod(String httpMethod);

    /**
     * @param adminStateUp The administrative state of the VIP. A valid value is true
     *                     (UP) or false (DOWN).
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder adminStateUp(boolean adminStateUp);
}
