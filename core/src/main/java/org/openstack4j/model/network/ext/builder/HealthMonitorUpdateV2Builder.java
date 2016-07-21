package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.HealthMonitorUpdateV2;

/**
 *  A builder to update a health monitor
 * @author ashleykasim
 *
 */
public interface HealthMonitorUpdateV2Builder extends Builder<HealthMonitorUpdateV2Builder,HealthMonitorUpdateV2> {
    /**
     * @param delay
     *            The time, in seconds, between sending probes to members.
     * @return HealthMonitorUpdateV2Builder
     */
    HealthMonitorUpdateV2Builder delay(Integer delay);

    /**
     *
     * @param urlPath
     *            Path portion of URI that will be probed if type is HTTP(S).
     * @return HealthMonitorUpdateBuilder
     */
    HealthMonitorUpdateV2Builder urlPath(String urlPath);

    /**
     *
     * @param expectedCodes
     *            Expected HTTP codes for a passing HTTP(S) monitor.
     * @return HealthMonitorUpdateV2Builder
     */
    HealthMonitorUpdateV2Builder expectedCodes(String expectedCodes);

    /**
     *
     * @param httpMethod
     *            GET/PUT/POST
     * @return HealthMonitorUpdateV2Builder
     */
    HealthMonitorUpdateV2Builder httpMethod(String httpMethod);

    /**
     *
     * @param maxRetries
     *            Maximum consecutive health probe tries.
     * @return HealthMonitorUpdateV2Builder
     */
    HealthMonitorUpdateV2Builder maxRetries(Integer maxRetries);

    /**
     *
     * @param adminStateUp
     *            The administrative state of the VIP. A valid value is true
     *            (UP) or false (DOWN).
     * @return HealthMonitorUpdateV2Builder
     */
    HealthMonitorUpdateV2Builder adminStateUp(boolean adminStateUp);

    /**
     *
     * @param timeout
     *            Time in seconds to timeout each probe.
     * @return HealthMonitorUpdateV2Builder
     */
    HealthMonitorUpdateV2Builder timeout(Integer timeout);
}
