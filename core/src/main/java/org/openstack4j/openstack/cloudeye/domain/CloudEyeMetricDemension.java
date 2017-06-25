package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.openstack4j.model.cloudeye.MetricDimensions;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeMetricDemension implements MetricDimensions {
    private static final long serialVersionUID = -959253863059879414L;

    String name;
    String value;
}
